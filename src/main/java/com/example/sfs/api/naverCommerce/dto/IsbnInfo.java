package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IsbnInfo {

    /**
     * ISBN 13자리 ^[\d*]{13}$
     * '-' 없이 13자리 유효한 숫자를 입력
     * 예외 카테고리 중 도서_일반, 도서_해외, 도서_중고, 도서_E북, 도서_오디오북에 해당하는 경우 필수
     * 예외 카테고리 중 도서_잡지, 도서_정가제free에 해당하는 경우 필수 아님
     * 독립출판물인 경우 필수 아님
     * 라이브러리를 통해 ISBN 값의 유효성 체크
     */
    private String isbn13;

    /**
     * ISSN 8자리 ^[\d*]{7}[\d|X]{1}$
     * 예외 카테고리 중 도서_잡지에 해당하는 경우 입력
     * '-' 없이 8자리 유효한 숫자를 입력
     */
    private String issn;

    /**
     * 독립출판물 여부
     * 예외 카테고리 중 도서_일반, 도서_해외, 도서_중고, 도서_정가제free, 도서_E북, 도서_오디오북에 해당하는 경우 입력 가능
     */
    private Boolean independentPublicationYn;
}
