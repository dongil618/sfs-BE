package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 윈도우 채널 상품
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WindowChanelProduct {
    /**
     * 채널 상품 전용 상품명
     * 채널 상품 전용 상품명을 사용할 경우 입력
     * 미입력 시 원상품명으로 적용
     */
    private String channelProductName;

    /**
     * 콘텐츠 게시글 일련번호
     * 공지사항
     */
    private Integer bbsSeq;

    /**
     * 알림받기 동의 회원 전용 상품 여부
     * 미입력 시 false로 저장
     */
    private boolean storeKeepExclusiveProduct;

    /**
     * required
     * 네이버쇼핑 등록 여부
     * 네이버 쇼핑 광고주가 아닌 경우에는 false로 저장
     */
    private boolean naverShoppingRegistration;

    /**
     * required
     * 윈도 채널 상품 채널번호
     * 전시할 윈도 채널 선택
     */
    private Integer channelNo;

    /**
     * 베스트 여부(윈도 채널 전용)
     */
    private boolean best;

    /**
     * 전시 상태 코드(스마트스토어 채널 전용)
     * WAIT(전시 대기), ON(전시 중), SUSPENSION(전시 중지)
     */
    private String channelProductDisplayStatusType;
}
