package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImmediateDiscountPolicy {

    /**
     * PC 할인 혜택
     */
    private DiscountMethod discountMethod;

    /**
     * 모바일 할인 혜택
     */
    private MobileDiscountMethod mobileDiscountMethod;
}
