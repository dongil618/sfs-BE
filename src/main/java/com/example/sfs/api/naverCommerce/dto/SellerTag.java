package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SellerTag {

    /**
     * 태그 ID
     * 직접 입력의 경우에는 ID가 존재하지 않음
     */
    private Integer code;

    /**
     * 태그명 : required
     */
    private String text;
}
