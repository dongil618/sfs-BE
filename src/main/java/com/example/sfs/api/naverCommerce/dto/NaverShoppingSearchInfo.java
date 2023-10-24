package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverShoppingSearchInfo {

    /**
     * 모델명 ID
     */
    private Integer modelId;

    /**
     * 제조사명
     */
    private String manufacturerName;

    /**
     * 브랜드명
     */
    private String brandName;

    /**
     * 모델명
     */
    private String modelName;
}
