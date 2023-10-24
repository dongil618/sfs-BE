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
public class Ecoupon {

    /**
     * E쿠폰 유효기간 구분 코드
     * FIXED(특정 기간), FLEXIBLE(자동 기간)
     */
    private String periodType;

    /**
     * E쿠폰 유효기간 시작일
     * E쿠폰 유효기간 구분 타입(PeriodType)이 '특정 기간'인 경우 필수
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String validStartDate;

    /**
     * E쿠폰 유효기간 종료일
     * E쿠폰 유효기간 구분 타입(PeriodType)이 '특정 기간'인 경우 필수
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String validEndDate;

    /**
     * E쿠폰 유효기간 내용(구매일로부터 00일)
     * E쿠폰 유효기간 구분 타입(PeriodType)이 '자동 기간'인 경우 필수
     */
    private Integer periodDays;

    /**
     * E쿠폰 발행처 내용 : required
     */
    private String publicInformationContents;

    /**
     * E쿠폰 발행처 내용 : required
     */
    private String contactInformationContents;

    /**
     * E쿠폰 사용 장소 구분 코드 : required
     * PLACE(장소), ADDRESS(주소), URL(URL)
     */
    private String usePlaceType;

    /**
     * 사용 장소 내용) : required
     * ECouponUsePlaceType.ADDRESS인 경우 주소록 일련번호를 입력
     */
    private String usePlaceContents;

    /**
     * 장바구니 구매 불가 여부
     * E쿠폰 장바구니 제한
     * 미입력 시 false로 설정됨
     * true: 즉시 구매만 가능, false: 즉시 구매, 장바구니 구매 가능
     */
    private Boolean restrictCart;

    /**
     * 사이트명
     */
    private String siteName;
}
