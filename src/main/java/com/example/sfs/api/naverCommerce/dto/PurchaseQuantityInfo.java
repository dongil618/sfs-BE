package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseQuantityInfo {

    /**
     * 최소 구매 수량
     * <= 10000
     */
    private Integer minPurchaseQuantity;

    /**
     * 인 최대 구매 수량
     * <= 10000
     */
    private Integer maxPurchaseQuantityPerId;

    /**
     * 1회 최대 구매 수량
     * <= 10000
     */
    private Integer maxPurchaseQuantityPerOrder;
}
