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
public class Wear extends ProvidedNoticeBase {

    /**
     * 제품 소재 : required
     */
    private String material;

    /**
     * 색상 : required
     */
    private String color;

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
     * 제조연월
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String packDate;

    /**
     * 제조연월 직접 입력
     * packDate를 입력하지 않은 경우에는 필수
     */
    private String packDateText;

    /**
     * 품질 보증 기준 : required
     */
    private String warrantyPolicy;

    /**
     * A/S 책임자와 전화번호 : required
     */
    private String afterServiceDirector;
}
