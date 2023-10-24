package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomBenefitPolicy {

    /**
     * 단위에 따른 값을 입력 : required
     * <= 10000000
     */
    private Integer value;

    /**
     * 단위 : required
     * PERCENT(정율), WON(정액), YEN, COUNT
     * PERCENT, WON만 입력 가능
     */
    private String unitType;
}
