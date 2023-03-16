package com.example.sfs.util.crawler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryCrawlerTest {

    @Autowired
    private CategoryCrawler categoryCrawler;

    @Test
    @DisplayName("네이버 쇼핑 검색 -> 상위 5개 카테고리 중 가장 많은 것 가져오기")
    public void getProductCategory() {
        //given
        String productName = "여자 오버핏 후드티";

        //when
        String productCategory = categoryCrawler.getProductCategory(productName);

        //then
        Assertions.assertThat(productCategory).isEqualTo("패션의류/여성의류/티셔츠");
    }
}
