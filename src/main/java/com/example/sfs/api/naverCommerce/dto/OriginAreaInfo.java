package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OriginAreaInfo {

    /**
     * 원산지 상세 지역 코드 : required
     */
    private String originAreaCode;

    /**
     * 수입사명
     * 수입산인 경우 필수
     */
    private String importer;

    /**
     * 모델명
     */
    private String content;

    /**
     * 복수 원산지 여부
     * 원산지가 다른 상품을 같이 등록하는지 여부
     * 미입력 시 false로 저장됨
     */
    private Boolean plural;
}
