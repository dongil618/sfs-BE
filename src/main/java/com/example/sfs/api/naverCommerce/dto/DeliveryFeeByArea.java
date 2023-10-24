package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryFeeByArea {

    /**
     * 지역별 추가 배송비 권역 코드 : required
     * 묶음배송 가능 여부가 true인 경우 해당 값은 무시됨
     * AREA_2(내륙/제주 및 도서산간 지역으로 구분(2권역)), AREA_3(내륙/제주/제주 외 도서산간 지역으로 구분(3권역))
     */
    private String deliveryAreaType;

    /**
     * 2권역 추가 배송비
     * <= 100000
     * 2권역인 경우 "제주 및 도서산간" 지역 추가 배송비
     * 3권역인 경우 "제주" 지역 추가 배송비
     * 묶음배송 가능 여부가 true인 경우 해당 값은 무시됨
     */
    private Integer area2extraFee;

    /**
     * 3권역 추가 배송비
     * <= 100000
     * "제주 외 도서산간" 지역 추가 배송비
     * deliveryAreaType이 3권역인 경우 필수 묶음배송 가능 여부가 true인 경우 해당 값은 무시됨
     */
    private Integer area3extraFee;
}
