package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AfterServiceInfo {

    /**
     * A/S 전화번호 : required
     */
    private String afterServiceTelephoneNumber;

    /**
     * A/S 안내 : required
     */
    private String afterServiceGuideContent;
}
