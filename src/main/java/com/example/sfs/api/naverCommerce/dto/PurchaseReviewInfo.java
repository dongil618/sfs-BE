package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReviewInfo {

    /**
     * 리뷰 노출 여부
     * 구매평 노출 설정 가능 카테고리일 경우(식품)에만 유효
     * 그 외에는 true로 설정됨
     * 미입력 시 true로 저장됨
     */
    private Boolean purchaseReviewExposure;

    /**
     * 리뷰 미노출 사유
     * 리뷰 노출 여부가 true일 경우 빈값으로 저장됨
     * 리뷰 노출 여부가 false일 경우 리뷰 미노출 사유를 입력해야 함
     */
    private String reviewUnExposeReason;
}
