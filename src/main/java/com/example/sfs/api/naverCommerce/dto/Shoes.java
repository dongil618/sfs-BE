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
public class Shoes extends ProvidedNoticeBase {

    /**
     * 제품 소재 : required
     */
    private String material;

    /**
     * 색상 : required
     */
    private String color;

    /**
     * 치수(발길이) : required
     */
    private String size;

    /**
     * 치수(굽높이)
     * 해당 사항이 없으면 이 요소를 삭제하고 전송함
     */
    private String height;

    /**
     * 제조자(사) : required
     */
    private String manufacturer;

    /**
     * 취급 시 주의사항 : required
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
