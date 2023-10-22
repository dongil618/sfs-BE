package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeoInfo {

    /**
     * 페이지 타이틀
     * <= 100 characters
     */
    private String pageTitle;

    /**
     * 메타 정보
     * <= 160 characters
     */
    private String metaDescription;

    /**
     * 판매자 입력 태그
     * <= 4000 characters
     */
    private List<SellerTag> sellerTags;
}
