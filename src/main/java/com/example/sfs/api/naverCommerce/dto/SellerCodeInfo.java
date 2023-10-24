package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellerCodeInfo {

    /**
     * 판매자 관리 코드
     */
    private String sellerManagementCode;

    /**
     * 판매자 바코드
     */
    private String sellerBarcode;

    /**
     * 판매자 내부 코드 1
     */
    private String sellerCustomCode1;

    /**
     * 판매자 내부 코드 2
     */
    private String sellerCustomCode2;
}
