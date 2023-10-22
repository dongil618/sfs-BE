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
public class StandardOptionAttribute {

    /**
     * 속성 ID : required
     */
    private Integer attributeId;

    /**
     * 속성값 ID : required
     */
    private Integer attributeValueId;

    /**
     * 속성값 이름 : required
     */
    private String attributeValueName;

    /**
     * 표준형 옵션에서 사용할 이미지 URL 목록
     * 이미지 URL은 반드시 상품 이미지 다건 등록 API로 이미지를 업로드하고 반환받은 URL 값을 입력해야 함
     */
    private List<String> imageUrls;
}
