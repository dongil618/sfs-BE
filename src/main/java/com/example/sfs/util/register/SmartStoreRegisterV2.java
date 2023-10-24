package com.example.sfs.util.register;

import com.example.sfs.api.naverCommerce.NaverCommerceApi;
import com.example.sfs.api.naverCommerce.dto.*;
import com.example.sfs.dto.product.PostRegisterProductRequestDto;
import com.example.sfs.util.CommonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmartStoreRegisterV2 implements ProductRegister {

    private final NaverCommerceApi naverCommerceApi;
    private static final CommonUtil commonUtil = new CommonUtil();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String COOKIE_NAME = "NC-TOKEN";
    private static final int MAXIMUM_UPLOAD_SIZE = 10000000; // byte단위, smartstore upload api
    private static final int MAXIMUM_UPLOAD_COUNT = 10; // 10장 smartstore upload api

    @Override
    public void registerProduct(PostRegisterProductRequestDto postRegisterProductRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {

        // naverCommerceApi에 필요한 accessToken 가져오기
        String accessToken = commonUtil.getCookie(req, this.COOKIE_NAME);
        if(StringUtils.isEmpty(accessToken)) {
            OAuthTokenResponseDto token = naverCommerceApi.getOAuthToken(res);
            accessToken = token.getAccessToken();
        }

        // 썸네일 이미지 다건 저장 후 representativeImage
        String thumbNailImagePath = CommonUtil.getThumbnailImageFilePath(postRegisterProductRequestDto);
        File thumbNailImageFile = commonUtil.getImageFile(thumbNailImagePath);
        SmartStoreImageResponseDto imageResponseDto = naverCommerceApi.uploadSmartStoreImages(accessToken, thumbNailImageFile);
        String representativeImageUrl = imageResponseDto.getImages().get(0).getUrl();
        RepresentativeImage representativeImage = RepresentativeImage.builder()
                                                    .url(representativeImageUrl)
                                                    .build();

        ProductImage productImage = ProductImage.builder()
                .representativeImage(representativeImage)
                .build();

        // 디테일 이미지 다건 저장 후 detailContent
        List<String> detailImageFilePathList = CommonUtil.getDetailImageFilePathList(postRegisterProductRequestDto);
        List<File> detailImageFileList = commonUtil.getImageFiles(detailImageFilePathList);

        // 나중에 lamda?로 코드를 짧게 리팩토링 해보면 좋을듯
        // 업로드 이미지 파일 최대 10개인 로직 추가하기
        List<List<File>> splitDetailImageFileList = new ArrayList<>();
        int targetFileSize = 0;
        int targetFileCount = 0;
        List<File> tempFileList = new ArrayList<>();
        int index = 1;
        for(File detailImageFile : detailImageFileList) {
            targetFileSize += detailImageFile.length();
            targetFileCount++;
            tempFileList.add(detailImageFile);
            if(targetFileSize > MAXIMUM_UPLOAD_SIZE || targetFileCount == MAXIMUM_UPLOAD_COUNT) {
                splitDetailImageFileList.add(tempFileList);
                tempFileList = new ArrayList<>();
                targetFileSize = 0;
                targetFileCount = 0;
            }
            if(index == detailImageFileList.size()) {
                splitDetailImageFileList.add(tempFileList);
                break;
            }
            index++;
        }

        List<Image> detailImages = new ArrayList<>();
        for(List<File> splitDetailImageFiles : splitDetailImageFileList) {
            SmartStoreImageResponseDto detailImageResponseDto = naverCommerceApi.uploadSmartStoreImages(accessToken, splitDetailImageFiles);
            List<Image> detailImageList = detailImageResponseDto.getImages();
            for(Image detailImage : detailImageList) {
                detailImages.add(detailImage);
            }
        }

        String detailContent = this.getDetailContent(postRegisterProductRequestDto, detailImages);

        // 리프 카테고리 아이디 가져오기
        String leafCategoryId = null;
        ClassPathResource resource = new ClassPathResource("smartstoreAllCategory.json");
        File file = new File(resource.getURI());
        List<SmartStoreCategory> smartStoreCategories = objectMapper.readValue(file, new TypeReference<List<SmartStoreCategory>>() {});
        for(SmartStoreCategory smartStoreCategory : smartStoreCategories) {
            String targetCategoryName = postRegisterProductRequestDto.getCategory();
            targetCategoryName = targetCategoryName.replace("$", ">");
            if(targetCategoryName.equals(smartStoreCategory.getWholeCategoryName())) {
                leafCategoryId = smartStoreCategory.getId();
            }
        }

        // deliveryInfo 가져오기
        resource = new ClassPathResource("basicProductInfo.json");
        file = new File(resource.getURI());
        SmartStoreProductResponseDto smartStoreProductResponseDto = objectMapper.readValue(file, new TypeReference<SmartStoreProductResponseDto>() {});
        DeliveryInfo deliveryInfo = smartStoreProductResponseDto.getOriginProduct().getDeliveryInfo();

        // detailAttribute 일부분 가져오기 + 옵션(optionInfo) 설정 + 상품 제공 공지(productInfoProvidedNotice) 설정 + seo(seoInfo) 설정
        DetailAttribute basicDetailAttribute = smartStoreProductResponseDto.getOriginProduct().getDetailAttribute();

        // 나중에 이것도 DB에서 관리되도록
        List<String> sizeList = Arrays.asList(postRegisterProductRequestDto.getSizeList().split(","));
        List<String> colorList = Arrays.asList(postRegisterProductRequestDto.getColorList().split(","));

        OptionCombinationGroupNames optionCombinationGroupNames = OptionCombinationGroupNames.builder()
                                                                        .optionGroupName1("size")
                                                                        .optionGroupName2("color").build();

        List<OptionCombinations> optionCombinationsList = this.getOptionCombinationsList(sizeList, colorList);

        OptionInfo optionInfo = OptionInfo.builder()
                .simpleOptionSortType("CREATE")
                .optionCombinationSortType("CREATE")
                .optionCombinationGroupNames(optionCombinationGroupNames)
                .optionCombinations(optionCombinationsList)
                .useStockManagement(true)
                .build();

        DetailAttribute detailAttribute = DetailAttribute.builder()
                .afterServiceInfo(basicDetailAttribute.getAfterServiceInfo())
                .originAreaInfo(basicDetailAttribute.getOriginAreaInfo())
                .optionInfo(optionInfo)
                .purchaseReviewInfo(basicDetailAttribute.getPurchaseReviewInfo())
                .taxType(basicDetailAttribute.getTaxType())
                .certificationTargetExcludeContent(basicDetailAttribute.getCertificationTargetExcludeContent())
                .sellerCommentUsable(basicDetailAttribute.getSellerCommentUsable())
                .minorPurchasable(basicDetailAttribute.getMinorPurchasable())
                .productInfoProvidedNotice(basicDetailAttribute.getProductInfoProvidedNotice())
                .itselfProductionProductYn(basicDetailAttribute.getItselfProductionProductYn())
                .build();

        // 남은 originProduct 설정
        OriginProduct originProduct = OriginProduct.builder()
                .statusType("SALE")
                .saleType("NEW")
                .leafCategoryId(leafCategoryId)
                .name(postRegisterProductRequestDto.getProductName())
                .detailContent(detailContent)
                .images(productImage)
                .salePrice(postRegisterProductRequestDto.getPrice() + 1000) // 임의로 1000원 올렸는데 나중에는 DB로 관리할 것
                .stockQuantity(0)
                .deliveryInfo(deliveryInfo)
                .detailAttribute(detailAttribute)
                .build();

        // smartstoreChannelProduct 설정
        SmartStoreChannelProduct smartStoreChannelProduct = smartStoreProductResponseDto.getSmartstoreChannelProduct();

        // registerSmartStoreProduct requestBody 만들기
        SmartStoreProductResponseDto newSmartStoreProduct = SmartStoreProductResponseDto.builder()
                .originProduct(originProduct)
                .smartstoreChannelProduct(smartStoreChannelProduct)
                .build();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String requestBody = objectMapper.writeValueAsString(newSmartStoreProduct);
        log.info("requestBody={}", requestBody);
        naverCommerceApi.registerSmartStoreProduct(accessToken, requestBody);
    }

    @Override
    public ProductRegisterStrategy getProductRegisterStrategy() {
        return ProductRegisterStrategy.SmartStoreRegister;
    }

    private String getDetailContent(PostRegisterProductRequestDto postRegisterProductRequestDto, List<Image> detailImages) {

        String result = "";
        String comment = postRegisterProductRequestDto.getInstruction();
        String sizeGuide = postRegisterProductRequestDto.getSizeGuide();

        // comment
        result += this.addAlignPTag("c o m m e n t");
        result += this.addLine(1);
        result += this.addAlignPTag(comment);
        result += this.addLine(1);
        result += this.addDividingLine();
        result += this.addAlignPTag("/size");
        result += this.addLine(1);
        result += this.addAlignPTag(sizeGuide);
        result += this.addLine(1);
        result += this.addDividingLine();
        result += this.addLine(1);
        result += this.addAlignPTag("인스타그램에서 피셀르의 최신 정보를 빠르게 받아보세요:)");
        result += this.addLine(2);
        for(Image image : detailImages) {
            result += this.addImage(image);
            result += this.addLine(2);
        }

        return result;
    }

    private String addAlignPTag(String text) {
        return "<p align=\"center\" style=\"font-size:11px\"><span>" + text + "</span></p>";
    }

    private String addLine(int times) {
        return StringUtils.repeat("<br>", times);
    }

    private String addDividingLine() {
        return "<hr align=\"center\" style=\"width:200px\">";
    }

    private String addImage(Image image) {

        String newImageUrl = "";
        try {
            URL imageUrl = new URL(image.getUrl());
            newImageUrl = "https://shop-phinf.pstatic.net" + imageUrl.getPath() + "?type=w860";
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return "<img src=\"" + newImageUrl + "\" alt=\"\">";
    }

    private List<OptionCombinations> getOptionCombinationsList(List<String> sizeList, List<String> colorList) {

        List<OptionCombinations> optionCombinations = sizeList.stream()
                .flatMap(i -> colorList.stream()
                        .map(j -> OptionCombinations.builder()
                                    .optionName1(i)
                                    .optionName2(j)
                                    .price(0)
                                    .stockQuantity(999)
                                    .usable(true)
                                    .build())
                        ).collect(Collectors.toList());

        return optionCombinations;
    }
}
