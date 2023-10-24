package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OptionSimple {

    /**
     * 옵션 일련번호
     * 옵션 ID 입력 시 기존 옵션 수정
     */
    private Long id;

    /**
     * 옵션명 : required
     */
    private String groupName;

    /**
     * 옵션값
     * "단독형 옵션"인 경우 입력함
     * "직접 입력형 옵션"인 경우 무시됨
     */
    private String name;

    /**
     * 사용 여부
     * Default: true
     * 미입력 시 사용 여부는 true로 설정됨
     */
    private Boolean usable;
}
