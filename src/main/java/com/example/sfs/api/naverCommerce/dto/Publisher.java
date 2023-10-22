package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    /**
     * 판매자센터에서 코드 조회 가능
     * 코드를 확인할 수 없으면 전송하지 않음
     */
    private String code;

    /**
     * 텍스트
     * 글작가명, 출판사는 필수값으로 입력해야 함
     * 코드가 NULL이 아닌 경우 해당 코드에 매핑된 이름으로 전송해야 함
     * 코드가 없으면, 코드 없이 이름만 전송함
     */
    private String text;
}
