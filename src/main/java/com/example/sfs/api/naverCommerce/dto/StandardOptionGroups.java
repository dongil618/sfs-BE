package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StandardOptionGroups {

    /**
     * 표준형 옵션 그룹 타입 : required
     */
    private String groupName;

    /**
     * 표준형 옵션 속성
     */
    private List<StandardOptionAttribute> standardOptionAttributes;
}
