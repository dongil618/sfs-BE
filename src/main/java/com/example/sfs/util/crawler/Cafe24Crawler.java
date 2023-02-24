package com.example.sfs.util.crawler;

import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.dto.product.PostCrawledProductsRequestDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class Cafe24Crawler implements ProductCrawler {

    private final CategoryCrawler categoryCrawler;
    @Override
    public List<ProductDto> getProductDetailInfos(String url) {
        Integer pageNum = 1;
        List<ProductDto> productDtos = getProductThumbInfos(url, pageNum);
        String baseUrl = url.split("/")[0] + "//" + url.split("/")[2];

        for(ProductDto productDto: productDtos) {
            setProductDto(baseUrl, productDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto getProductDetailInfo(PostCrawledProductsRequestDto postCrawledProductsRequestDto) {
        String detailPageUrl = postCrawledProductsRequestDto.getDetailPageUrl();
        String baseUrl = detailPageUrl.split("/")[0] + "//" + detailPageUrl.split("/")[2];

        ProductDto productDto = new ProductDto(postCrawledProductsRequestDto);
        setProductDto(baseUrl, productDto);
        return productDto;
    }

    private void setProductDto(String baseUrl, ProductDto productDto) {
        try {
            Document doc = Jsoup.connect(productDto.getDetailPageUrl()).get();
            setProductOption(doc, productDto);
            setProductPrice(doc, productDto);
            setProductDetailImage(doc, productDto, baseUrl);
            setProductCategory(productDto);
            setProductInstruction(doc, productDto);
            setProductSizeGuide(doc, productDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductDto> getProductThumbInfos(String url, Integer pageNum) {
        List<ProductDto> productDtos = new ArrayList<>();
        String baseUrl = url.split("/")[0] + "//" + url.split("/")[2];
        String productsUrl = url + "?page=";

        int pageCnt = 1;
        while(pageCnt <= pageNum) {
            String productsUrlPage = productsUrl + Integer.toString(pageCnt);
            try {
                Document doc = Jsoup.connect(productsUrlPage).get();
                Elements thumbnailTags = doc.select(".thumbnail");
                if (thumbnailTags.isEmpty() == true) {
                    break;
                }
                for(Element thumbnailTag : thumbnailTags) {
                    String name = thumbnailTag.getElementsByTag("img").attr("alt");
                    String detailPageUrl = baseUrl + thumbnailTag.getElementsByTag("a").attr("href");
                    String thumbnailImageUrl = "https:" + thumbnailTag.getElementsByTag("img").attr("src");
                    ProductDto productDto = new ProductDto(name, detailPageUrl, thumbnailImageUrl);
                    productDtos.add(productDto);
                }
                pageCnt += 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return productDtos;
    }

    @Override
    public ProductCrawlerStrategy getProductCrawlerStrategy() {
        return ProductCrawlerStrategy.Cafe24Crawler;
    }

    private void setProductOption(Document doc, ProductDto productDto) {
        Elements optionDivs = doc.select("tbody[class=xans-element- xans-product xans-product-option xans-record-]");

        for(Element optionDiv : optionDivs) {
            String optionName = optionDiv.getElementsByTag("ul").attr("option_title");
            Elements optionLiTags = optionDiv.select("li");

            List<String> optionList = new ArrayList<>();
            optionLiTags.stream().forEach(
                    optionLiTag -> optionList.add(optionLiTag.getElementsByTag("li").attr("option_value"))
            );

            String[] sizeArray = {"사이즈", "Size", "SIZE", "size"};
            List<String> sizeList = new ArrayList<>(Arrays.asList(sizeArray));
            if(sizeList.contains(optionName)){
                productDto.setSizeList(optionList);
            }

            String[] colorArray = {"컬러", "Color", "Color", "color"};
            List<String> colorList = new ArrayList<>(Arrays.asList(colorArray));
            if(colorList.contains(optionName)){
                productDto.setSizeList(optionList);
            }
        }
    }

    private void setProductPrice(Document doc, ProductDto productDto) {
        // 정가
        String productPrice = doc.getElementById("span_product_price_text").text();
        Integer price = changeProductPriceStringToInteger(productPrice);
        productDto.setPrice(price);
        productDto.setDiscountYN("N");

        // 할인 상품일 때
        Element discountedPriceEle = doc.getElementById("span_product_price_sale");
        if(discountedPriceEle != null) {
            String discountedPriceText = discountedPriceEle.text();
            // discountedPriceText 추출된 값 "43,700원 ( 2,300원 할인)"
            String sDiscountedAmount = discountedPriceText.split(" ")[2];
            Integer discountedAmount = changeProductPriceStringToInteger(sDiscountedAmount);
            productDto.setDiscountedAmount(Integer.valueOf(discountedAmount));
            productDto.setDiscountYN("Y");
        }
    }
    private void setProductDetailImage(Document doc, ProductDto productDto, String baseUrl) {
        Element productDetailDivs = doc.getElementById("prdDetail");
        Elements productDetailImgTags = productDetailDivs.getElementsByTag("img");

        List<String> detailImageUrlList = new ArrayList<>();

        productDetailImgTags.stream().forEach(
                productDetailImgTag -> detailImageUrlList.add(baseUrl + productDetailImgTag.attr("ec-data-src"))
        );

        productDto.setDetailImageUrlList(detailImageUrlList);
    }
    private void setProductCategory(ProductDto productDto) {
        String productName = productDto.getName();
        String productCategory = categoryCrawler.getProductCategory(productName);
        productDto.setCategory(productCategory);
    }

    private void setProductInstruction(Document doc, ProductDto productDto) {
        String instruction = doc.getElementById("view1").text();
        productDto.setInstruction(instruction);
    }

    private void setProductSizeGuide(Document doc, ProductDto productDto) {
        String instruction = doc.getElementById("view2").text();
        productDto.setSizeGuide(instruction);
    }

    private Integer changeProductPriceStringToInteger(String productPrice) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("([0-9])+");	// 검색할 문자열 패턴 : 숫자
        Matcher m = p.matcher(productPrice);			// 문자열 설정
        while (m.find()) {
            sb.append(m.group());
        }

        return Integer.valueOf(String.valueOf(sb));
    }
}
