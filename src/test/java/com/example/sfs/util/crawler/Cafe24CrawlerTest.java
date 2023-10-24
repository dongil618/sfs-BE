package com.example.sfs.util.crawler;

import com.example.sfs.dto.product.PostCrawledProductsRequestDto;
import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.api.PapagoApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cafe24CrawlerTest {

    private static final ObjectMapper ob = new ObjectMapper();

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

    @Test
    @DisplayName("네이버 쇼핑에서 카테고리 정보 가져오기")
    public void getCategoryInNaverShopping() throws IOException {
        // given
        String requestUrlPath = "https://search.shopping.naver.com/search/all?query=%EC%B9%B4%EA%B3%A0+%EB%AF%B8%EB%8B%88+%EC%8A%A4%EC%BB%A4%ED%8A%B8+%28%EB%B2%A0%EC%9D%B4%EC%A7%80%2C+%EC%88%AF%2C+%EB%B8%94%EB%9E%99%29";

        // when
        Map<String, Integer> productCategoryDict = new HashMap<>();

        Document doc = Jsoup.connect(requestUrlPath).get();
        Element scriptElement = doc.select("script[id=__NEXT_DATA__]").first();
        List<DataNode> dataNodeList = scriptElement.dataNodes();
        String jsonStr = String.valueOf(dataNodeList.get(0));

        Map<String, Object> dataMap = ob.readValue(jsonStr, Map.class);
        Map<String, Object> props = (Map<String, Object>) dataMap.get("props");
        Map<String, Object> pageProps = (Map<String, Object>) props.get("pageProps");
        Map<String, Object> initialState = (Map<String, Object>) pageProps.get("initialState");
        Map<String, Object> products = (Map<String, Object>) initialState.get("products");
        List<Map<String, Object>> productList = (List<Map<String, Object>>) products.get("list");

        List<String> productCategoryList = new ArrayList<>();
        int i = 0;
        for(Map<String, Object> product : productList) {
            if(i == 5) {
                break;
            }
            Map<String, Object> item = (Map<String, Object>) product.get("item");
            String productCategory = item.get("category1Name") + "$"
                                    + item.get("category2Name") + "$"
                                    + item.get("category3Name")
                                    + ((item.get("category4Name") != "") ? "$" + item.get("category4Name") : "");
            System.out.println(productCategory);
            productCategoryList.add(productCategory);
            productCategoryDict = getCountsToDict(productCategoryList);
            i++;
        }
        System.out.println(productCategoryDict);
    }

    public Map<String, Integer> getCountsToDict(List<String> stringList) {
        Map<String, Integer> counts = new HashMap<>();
        for(String string : stringList) {
            if (counts.containsKey(string)) {
                counts.put(string, counts.get(string) + 1);
            } else {
                counts.put(string, 1);
            }
        }
        return counts;
    }
}
