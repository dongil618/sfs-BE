package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {

    /**
     * 속성 ID
     */
    private Integer attributeSeq;

    /**
     * 속성값 ID : required
     */
    private Integer attributeValueSeq;

    /**
     * 속성 실제 값
     * 범위형인 경우 입력함
     * 범위형처럼 속성의 특정 값을 지정할 수 없을 때 사용함
     */
    private String attributeRealValue;

    /**
     * 속성 실제 값 단위 코드
     * 범위형인 경우 입력함
     */
    private String attributeRealValueUnitCode;
}
