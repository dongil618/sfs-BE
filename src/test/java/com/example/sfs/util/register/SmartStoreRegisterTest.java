package com.example.sfs.util.register;

import com.example.sfs.api.naverCommerce.dto.DeliveryInfo;
import com.example.sfs.api.naverCommerce.dto.SmartStoreCategory;
import com.example.sfs.api.naverCommerce.dto.SmartStoreProductResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLPermission;
import java.util.Arrays;
import java.util.List;

public class SmartStoreRegisterTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
//    static WebDriver driver;
//
//    @Test
//    @DisplayName("스마트스토어 판매자센터 로그인하기")
//    public void loginSmartStoreTest() throws IOException {
//        SmartStoreRegister smartStoreRegister = new SmartStoreRegister();
//        smartStoreRegister.smartStoreLogin(driver);
//    }
//
//    @Test
//    @DisplayName("스마트스토어 판매자센터 상품 등록하기")
//    public void smartStoreRegister() throws IOException {
//
//        //given
//        PostRegisterProductRequestDto postRegisterProductRequestDto = new PostRegisterProductRequestDto(
//                "Chain Pointed Toe Flat Shoes (Ivory,_Gold,_Navy,_Black)",
//                "Chain_Pointed_Toe_Flat_Shoes_(Ivory,_Gold,_Navy,_Black)_thumbnail.gif",
//                "패션잡화/여성신발/단화/플랫",
//                46000,
//                "N",
//                null,
//                "Chain_Pointed_Toe_Flat_Shoes_(Ivory,_Gold,_Navy,_Black)_0.jpg$Chain_Pointed_Toe_Flat_Shoes_(Ivory,_Gold,_Navy,_Black)_1.jpg$Chain_Pointed_Toe_Flat_Shoes_(Ivory,_Gold,_Navy,_Black)_10.jpg",
//                "230,235,240,245,250",
//                null,
//                "체인이 포인트가 되는 플랫 슈즈 입니다. 단조로운 룩에도 체인 슈즈 하나만으로 포인트를 줄 수 있습니다. 컬러는 아이보리, 골드, 네이비, 블랙 네 가지가 준비되어 있습니다. 아이보리, 블랙 컬러는 배색 없이 한 컬러로 제작되었고, 골드와 네이비는 배색이 들어간 제품입니다. 골드 컬러는 아이보리 컬러에 앞코 부분만 골드 포인트가 들어가 있습니다. 네이비 컬러는 앞코는 블랙으로 제작되었습니다. 상세컷에서 확인해주세요 :)",
//                "MODEL SIZE 166cm, 44(XS)~55(S)",
//                ProductRegisterStrategy.SmartStoreRegister
//        );
//
//        //when
//        SmartStoreRegister smartStoreRegister = new SmartStoreRegister(new SeleniumUtil());
//        smartStoreRegister.registerProduct(postRegisterProductRequestDto);
//    }

    @Test
    @DisplayName("전체 카테고리 조회 후 카테고리 이름에 맞는 카테고리 id 가져오기")
    public void getCategoryIdByCategoryName() throws IOException {

        // given
        String targetCategoryName = "패션의류>여성의류>니트/스웨터";
        ClassPathResource resource = new ClassPathResource("smartstoreAllCategory.json");
        File file = new File(resource.getURI());

        // when
        List<SmartStoreCategory> smartStoreCategories = objectMapper.readValue(file, new TypeReference<List<SmartStoreCategory>>() {});
        String categoryId = null;
        for(SmartStoreCategory smartStoreCategory : smartStoreCategories) {
            if(targetCategoryName.equals(smartStoreCategory.getWholeCategoryName())) {
                categoryId = smartStoreCategory.getId();
            }
        }

        // then
        Assertions.assertThat(categoryId).isEqualTo("50000805");
    }

    @Test
    @DisplayName("basicProductInfo.json에서 deliveryInfo 가져오기")
    public void getDeliveryInfoBybasicProductInfoJson() throws IOException {

        // given
        ClassPathResource resource = new ClassPathResource("basicProductInfo.json");
        File file = new File(resource.getURI());

        // when
        SmartStoreProductResponseDto smartStoreProductResponseDto = objectMapper.readValue(file, new TypeReference<SmartStoreProductResponseDto>() {});
        DeliveryInfo deliveryInfo = smartStoreProductResponseDto.getOriginProduct().getDeliveryInfo();

        // then
        Assertions.assertThat(deliveryInfo.getDeliveryCompany()).isEqualTo("CJGLS");
    }

    @Test
    @DisplayName("image url 변경")
    public void changeImageUrl() {

        // given
        String uploadUrl = "http://shop1.phinf.naver.net/20231019_13/1697711965101DGMkN_JPEG/30446843063267279_1661873359.jpg?type=w860";

        // when
        String newUrl = "";
        try {
            URL url = new URL(uploadUrl);
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getPath());
            System.out.println(url.getQuery());
            newUrl = "https://shop-phinf.pstatic.net" + url.getPath() + "?" + url.getQuery();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // then
        Assertions.assertThat(newUrl).isEqualTo("https://shop-phinf.pstatic.net/20231019_13/1697711965101DGMkN_JPEG/30446843063267279_1661873359.jpg?type=w860");
    }
}
