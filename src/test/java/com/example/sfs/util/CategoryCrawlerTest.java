package com.example.sfs.util;

import com.example.sfs.util.crawler.CategoryCrawler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryCrawlerTest {

    @Test
    @DisplayName("네이버 쇼핑 검색 -> 상위 5개 카테고리 중 가장 많은 것 가져오기")
    public void getProductCategory() {
        //given
        String productName = "여자 오버핏 후드티";

        //when
        String productCategory = new CategoryCrawler().getProductCategory(productName);

        //then
        Assertions.assertThat(productCategory).isEqualTo("패션의류/여성의류/티셔츠");
    }
}
