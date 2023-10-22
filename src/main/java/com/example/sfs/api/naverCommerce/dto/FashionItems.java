package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FashionItems extends ProvidedNoticeBase {

    /**
     * 종류 : required
     */
    private String type;

    /**
     * 소재 : required
     */
    private String material;

    /**
     * 치수 : required
     */
    private String size;

    /**
     * 제조자(사) : required
     */
    private String manufacturer;

    /**
     * 세탁 방법 및 취급 시 주의사항 : required
     */
    private String caution;

    /**
     * 품질 보증 기준 : required
     */
    private String warrantyPolicy;

    /**
     * A/S 책임자와 전화번호 : required
     */
    private String afterServiceDirector;
}
