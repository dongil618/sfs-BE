package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClaimDeliveryInfo {

    /**
     * 반품 택배사 우선순위 타입
     * PRIMARY SECONDARY_1 SECONDARY_2 SECONDARY_3 SECONDARY_4 SECONDARY_5 SECONDARY_6 SECONDARY_7 SECONDARY_8 SECONDARY_9
     * 미입력 시 '기본 반품 택배사(PRIMARY)'로 설정됨
     */
    private String returnDeliveryCompanyPriorityType;

    /**
     * 반품 배송비 : required
     * <= 1000000
     */
    private Integer returnDeliveryFee;

    /**
     * 교환 배송비 : required
     * <= 1000000
     */
    private Integer exchangeDeliveryFee;

    /**
     * 출고지 주소록 번호
     */
    private Integer shippingAddressId;

    /**
     * 반품/교환지 주소록 번호
     */
    private Integer returnAddressId;

    /**
     * 반품안심케어 설정
     */
    private Boolean freeReturnInsuranceYn;
}
