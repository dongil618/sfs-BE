package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MultiPurchaseDiscountPolicy {

    /**
     * 복수 구매 할인 혜택
     */
    private DiscountMethod discountMethod;

    /**
     * 주문 금액(수량) 값 : required
     */
    private Integer orderValue;

    /**
     * 주문 금액(수량) 단위 : required
     * PERCENT(정율), WON(정액), YEN, COUNT
     * PERCENT, WON만 입력 가능
     */
    private String orderValueUnitType;
}
