package com.example.sfs.api.naverCommerce.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionCombinationGroupNames {

    /**
     * 조합형 옵션명 1 : required
     */
    private String optionGroupName1;

    /**
     * 조합형 옵션명 2
     */
    private String optionGroupName2;

    /**
     * 조합형 옵션명 3
     */
    private String optionGroupName3;

    /**
     * 조합형 옵션명 4
     * "지점형 옵션"인 경우만 대상
     * "조합형 옵션"인 경우 무시됨
     */
    private String optionGroupName4;
}
