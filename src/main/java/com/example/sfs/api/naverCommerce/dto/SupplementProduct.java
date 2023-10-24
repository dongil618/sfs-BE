package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupplementProduct {

    /**
     * 추가 상품 ID
     */
    private Long id;

    /**
     * 추가 상품 그룹명 : required
     */
    private String groupName;

    /**
     * 추가 상품명 : required
     */
    private String name;

    /**
     * 추가 상품가
     * <= 999999990
     * 미입력 시 0원으로 입력
     */
    private Integer price;

    /**
     * 재고 수량
     * <= 99999999
     * 미입력 시 0개로 입력
     */
    private Integer stockQuantity;

    /**
     * 판매자 관리 코드
     */
    private String sellerManagementCode;

    /**
     * 사용 여부
     * 미입력 시 true로 입력
     */
    private boolean usable;
}
