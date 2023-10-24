package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OptionStandards {

    /**
     * 표준형 옵션 ID
     * 옵션 ID 입력 시 기존 옵션 수정
     */
    private Long id;

    /**
     * 표준형 옵션값 1 : required
     */
    private String optionName1;

    /**
     * 표준형 옵션값 2
     */
    private String optionName2;

    /**
     * 재고 수량
     * <= 99999999
     * 옵션 재고 수량 관리 사용 여부 설정 시에만 활용됨
     * 미입력 시 0개
     */
    private Integer stockQuantity;

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
