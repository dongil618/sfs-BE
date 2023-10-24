package com.example.sfs.api.naverCommerce.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionCombinations {

    /**
     * 조합형 옵션 ID
     * 옵션 ID 입력 시 기존 옵션 수정
     */
    private Long id;

    /**
     * 조합형 옵션값 1 : required
     * combinationOptionNames의 옵션명 1에 해당하는 옵션값
     * 장보기 계정 요청, 즉 지점 옵션(BRANCH)인 경우, 해당 필드에 옵션명이 아닌 지점 ID를 입력함
     */
    private String optionName1;

    /**
     * 조합형 옵션값 2
     * combinationOptionNames의 옵션명 2에 해당하는 옵션값
     */
    private String optionName2;

    /**
     * 조합형 옵션값 3
     * combinationOptionNames의 옵션명 3에 해당하는 옵션값
     */
    private String optionName3;

    /**
     * 조합형 옵션값 4
     * combinationOptionNames의 옵션명 4에 해당하는 옵션값.
     * "지점형 옵션"인 경우만 대상
     * "조합형 옵션"인 경우 무시됨
     */
    private String optionName4;

    /**
     * 재고 수량
     * <= 99999999
     * 미입력 시 0개
     */
    private Integer stockQuantity;

    /**
     * 옵션가
     * <= 999999990
     * 미입력 시 0원
     */
    private Integer price;

    /**
     * 판매자 관리 코드
     */
    private String sellerManagerCode;

    /**
     * 사용 여부
     * Default: true
     * 미입력 시 사용 여부는 true로 설정됨
     */
    private Boolean usable;
}
