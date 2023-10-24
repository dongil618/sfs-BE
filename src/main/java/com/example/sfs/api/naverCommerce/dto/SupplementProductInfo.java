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
public class SupplementProductInfo {

    /**
     * 추가 상품 정렬 구분 코드
     * 미입력 시 기본값인 등록순(CREATE)으로 저장됨
     * CREATE(등록순), ABC(가나다순), LOW_PRICE(낮은 가격순), HIGH_PRICE(높은 가격순)
     */
    private String sortType;

    /**
     * 추가 상품
     */
    private List<SupplementProduct> supplementProducts;
}
