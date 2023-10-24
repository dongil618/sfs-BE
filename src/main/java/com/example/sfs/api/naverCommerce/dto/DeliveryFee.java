package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryFee {

    /**
     * 배송비 타입
     * 배송비 타입을 입력하지 않으면 FREE(무료)로 설정됨
     * FREE(무료), CONDITIONAL_FREE(조건부 무료), PAID(유료), UNIT_QUANTITY_PAID(수량별), RANGE_QUANTITY_PAID(구간별)
     */
    private String deliveryFeeType;

    /**
     * 기본 배송비
     * <= 100000
     */
    private Integer baseFee;

    /**
     * 무료 조건 금액
     * <= 999999990
     * 배송비 유형이 '조건부 무료'일 경우 입력
     */
    private Integer freeConditionalAmount;

    /**
     * 기본 배송비 반복 부과 수량
     * 반복 수량. 배송비 유형이 '수량별 부과 - 반복 구간'일 경우 입력
     */
    private Integer repeatQuantity;

    /**
     * 배송비 조건 2구간 수량
     * 2구간 최소 수량
     * 배송비 유형이 '수량별 부과 - 구간 직접 설정'일 경우 입력
     */
    private Integer secondBaseQuantity;

    /**
     * 배송비 조건 2구간 수량 초과 시 추가 배송비
     * 2구간 추가 배송비
     * 배송비 유형이 '수량별 부과 - 구간 직접 설정'일 경우 입력
     */
    private Integer secondExtraFee;

    /**
     * 배송비 조건 3구간 수량
     * 3구간 최소 수량
     * 배송비 유형이 '수량별 부과 - 구간 직접 설정'일 경우 입력
     */
    private Integer thirdBaseQuantity;

    /**
     * 배송비 조건 3구간 초과 시 추가 배송비
     * 3구간 추가 배송비
     * 배송비 유형이 '수량별 부과 - 구간 직접 설정'일 경우 입력
     */
    private Integer thirdExtraFee;

    /**
     * 배송비 결제 방식 코드
     * COLLECT(착불), PREPAID(선결제), COLLECT_OR_PREPAID(착불 또는 선결제)
     */
    private String deliveryFeePayType;

    /**
     * 지역별 추가 배송비
     */
    private DeliveryFeeByArea deliveryFeeByArea;

    /**
     * 지역별 차등 배송비 정보
     */
    private String differentialFeeByArea;
}
