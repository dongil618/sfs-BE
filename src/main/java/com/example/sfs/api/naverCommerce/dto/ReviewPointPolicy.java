package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPointPolicy {

    /**
     * 텍스트 리뷰 포인트
     * 텍스트 리뷰 작성 시 적립되는 네이버페이 포인트
     */
    private Integer textReviewPoint;

    /**
     * 포토/동영상 리뷰 포인트
     * 포토/동영상 리뷰 작성 시 적립되는 네이버페이 포인트
     */
    private Integer photoVideoReviewPoint;

    /**
     * 한 달 사용 텍스트 리뷰 포인트
     * 한 달 사용 텍스트 리뷰 작성 시 적립되는 네이버페이 포인트
     */
    private Integer afterUseTextReviewPoint;

    /**
     * 한 달 사용 포토 동영상 리뷰 포인트
     * 한 달 사용 포토/동영상 리뷰 작성 시 적립되는 네이버페이 포인트
     */
    private Integer afterUsePhotoVideoReviewPoint;

    /**
     * 알림받기 동의/소식알림(톡톡친구) 회원 리뷰 작성 포인트
     * 알림받기 동의/톡톡친구 회원이 상품 리뷰, 한 달 사용 리뷰 작성 시 추가 적립되는 네이버페이 포인트
     */
    private Integer storeMemberReviewPoint;

    /**
     * 적립 시작일
     * 네이버페이 포인트 유효기간
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDate;

    /**
     * 적립 종료일
     * 네이버페이 포인트 유효기간 종료일
     * 시작일을 입력한 경우 필수
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDate;
}
