package com.example.sfs.util.crawler;

import com.example.sfs.dto.product.PostCrawledProductsRequestDto;
import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.util.api.PapagoApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


public class Cafe24CrawlerTest {
    @Test
    @DisplayName("카페24 여러개 상품 상세페이지에서 상세 정보 한 번에 가져오기")
    public void getProductDetailInfosTest() {
        // given
        String url = "https://ficelle.co.kr/category/new-in/24/";

        // when
        List<ProductDto> productDtos = new Cafe24Crawler(new CategoryCrawler(), new PapagoApi()).getProductDetailInfos(url);

        // then
        Assertions.assertThat(productDtos.size()).isEqualTo(30);
    }

    @Test
    @DisplayName("상세페이지 URL로 한 개의 상품 상세페이지에서 상세 정보 가져오기")
    public void getProductDetailInfoTest() {
        // given
        String productName = "Chain Pointed Toe Flat Shoes (Ivory, Gold, Navy, Black)";
        String detailPageUrl = "https://www.ficelle.co.kr/product/chain-pointed-toe-flat-shoes-ivory-gold-navy-black/471/category/25/display/1/";
        String thumbnailImageUrl = "https://www.ficelle.co.kr/web/product/big/202302/d5fd37d38d962606808798ad8b2c011f.gif";
        ProductCrawlerStrategy siteType = ProductCrawlerStrategy.Cafe24Crawler;

        PostCrawledProductsRequestDto postCrawledProductsRequestDto = new PostCrawledProductsRequestDto(productName, detailPageUrl, thumbnailImageUrl, siteType);

        // when
        ProductDto productDto = new Cafe24Crawler(new CategoryCrawler(), new PapagoApi()).getProductDetailInfo(postCrawledProductsRequestDto);

        // then
        Assertions.assertThat(productDto.getPrice()).isEqualTo(46000);
    }

    @Test
    @DisplayName("카페24 상품의 썸네일 정보인 이름, 상세페이지 경로, 메인 이미지 값 가져오기 테스트")
    public void getProductThumbInfosTest() {
        // given
        String url = "https://www.ficelle.co.kr/category/shoes-bag-acc/34/";

        // when
        List<ProductDto> productDtos = new Cafe24Crawler(new CategoryCrawler(), new PapagoApi()).getProductThumbInfos(url, 1);

        // then
        Assertions.assertThat(productDtos.size()).isEqualTo(30);
    }
}
