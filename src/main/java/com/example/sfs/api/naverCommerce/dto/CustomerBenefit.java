package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBenefit {

    /**
     * 판매자 즉시 할인 정책
     */
    private ImmediateDiscountPolicy immediateDiscountPolicy;

    /**
     * 상품 구매 포인트 정책
     */
    private PurchasePointPolicy purchasePointPolicy;

    /**
     * 구매평 포인트 정책
     */
    private ReviewPointPolicy reviewPointPolicy;

    /**
     * 무이자 할부 정책
     */
    private FreeInterestPolicy freeInterestPolicy;

    /**
     * 사은품 정책
     */
    private GiftPolicy giftPolicy;


    /**
     * 복수 구매 할인 정책
     */
    private MultiPurchaseDiscountPolicy multiPurchaseDiscountPolicy;
}
