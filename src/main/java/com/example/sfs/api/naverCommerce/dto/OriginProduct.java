package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OriginProduct {

    /**
     * 상품 판매 상태 코드
     * 상품 API에서 상품의 판매 상태를 나타내기 위해 사용하는 코드
     * 상품 등록 시에는 SALE(판매 중)만 입력 가능
     * 상품 수정 시에는 SALE(판매 중), SUSPENSION(판매 중지)만 입력 가능
     * StockQuantity의 값이 0인 경우 상품 상태는 OUTOFSTOCK(품절)으로 저장됨
     * WAIT(판매 대기), SALE(판매 중), OUTOFSTOCK(품절), UNADMISSION(승인 대기), REJECTION(승인 거부), SUSPENSION(판매 중지), CLOSE(판매 종료), PROHIBITION(판매 금지)
     */
    private String statusType;

    /**
     * 상품 판매 유형 코드
     * 상품 API에서 상품의 판매 유형을 나타내기 위해 사용하는 코드
     * 미입력 시 NEW(새 상품)로 저장됨
     * NEW(새 상품), OLD(중고 상품)
     */
    private String saleType;

    /**
     * 상품 등록 시에는 필수
     * 표준형 옵션 카테고리 상품 수정 요청의 경우 CategoryId 변경 요청은 무시됨
     */
    private String leafCategoryId;

    /**
     * 상품명 : required
     */
    private String name;

    /**
     * 상품 상세 정보 : required
     */
    private String detailContent;

    /**
     * 상품 이미지 : required
     * 상품 이미지로 대표 이미지(1000x1000픽셀 권장, 필수) + 추가 이미지 목록(최대 9장, 선택사항)
     * 이미지이미지 URL은 반드시 상품 이미지 다건 등록 API로 이미지를 업로드하고 반환받은 URL 값을 입력해야 함
     */
    private ProductImage images;

    /**
     * 판매 시작 일시
     * 매 시각 00분으로만 설정 가능
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]", timezone = "Asia/Seoul")
    private LocalDateTime saleStartDate;

    /**
     * 판매 종료 일시
     * 매 시각 59분으로만 설정 가능
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime saleEndDate;

    /**
     * 상품 판매 가격 : required
     * <= 999999990
     */
    private Integer salePrice;

    /**
     * 재고 수량
     * <= 99999999
     * 상품 등록 시 필수
     * 상품 수정 시 재고 수량을 입력하지 않으면 스마트스토어 데이터베이스에 저장된 현재 재고 값이 변하지 않음
     *  수정 시 재고 수량이 0으로 입력되면 StatusType으로 전달된 항목은 무시되며 상품 상태는 OUTOFSTOCK(품절)으로 저장됨
     */
    private Integer stockQuantity;

    /**
     * 배송정보(Object)
     * 배송 방식 및 배송비 등을 설정 가능
     * 입력하지 않으면 배송 없는 상품으로 등록됨
     * 보기 상품의 경우에는 배송 정보를 필수로 입력해야 함
     */
    private DeliveryInfo deliveryInfo;

    /**
     * 물류사 정보(Array of objects)
     * 풀필먼트 이용 상품은 입력 필수
     * 네이버 물류 연합군의 풀필먼트 서비스를 이용하고 있는 경우 상품에 물류사/물류센터 정보를 입력
     * 입력하지 않으면 상품, 주문 등 풀필먼트에 필요한 데이터가 물류사와 연동되지 않음
     */
    private List<ProductLogistic> productLogistics;

    /**
     * 원상품 상세 속성 : required
     */
    private DetailAttribute detailAttribute;

    /**
     * 상품 고객 혜택 정보
     */
    private CustomerBenefit customerBenefit;
}
