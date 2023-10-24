package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailAttribute {

    /**
     * 네이버쇼핑 검색 정보
     */
    private NaverShoppingSearchInfo naverShoppingSearchInfo;

    /**
     * A/S 정보 : required
     */
    private AfterServiceInfo afterServiceInfo;

    /**
     * 구매 수량 설정 정보
     */
    private PurchaseQuantityInfo purchaseQuantityInfo;

    /**
     * 원산지 정보
     */
    private OriginAreaInfo originAreaInfo;

    /**
     * 판매자 코드 정보
     */
    private SellerCodeInfo sellerCodeInfo;

    /**
     * 옵션 정보
     * 단독형 옵션, 조합형 옵션, 직접 입력형 옵션 중 최소 한 개는 입력해야 함
     * 단독형 옵션과 조합형 옵션은 함께 사용할 수 없음
     */
    private OptionInfo optionInfo;

    /**
     * 추가 상품
     */
    private SupplementProductInfo supplementProductInfo;

    /**
     * 구매평 정보
     * 리뷰 노출 설정 정보
     */
    private PurchaseReviewInfo purchaseReviewInfo;

    /**
     * ISBN 정보
     */
    private IsbnInfo isbnInfo;

    /**
     * 도서 정보
     * 도서 항목 부가 정보
     */
    private BookInfo bookInfo;

    /**
     * 이벤트 문구(홍보 문구 대체)
     */
    private String eventPhraseCont;

    /**
     * 제조일자
     * 인증 유형이 방송통신기자재 적합인증/적합등록/잠정인증인 경우 필수
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String manufactureDate;

    /**
     * 출시일자
     * 최초 1회만 입력 가능(수정/삭제 불가능)
     * 브랜드스토어 대상 한정.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String releaseDate;

    /**
     * 유효일자
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String validDate;

    /**
     * 부가가치세 타입 코드
     * 네이버 상품 API에서 부가가치세의 타입을 나타내기 위해 사용하는 코드
     * 미입력 시 과세 상품으로 등록됨
     * TAX(과세 상품), DUTYFREE(면세 상품), SMALL(영세 상품)
     */
    private String taxType;

    /**
     * 인증 정보 목록
     * '어린이제품 인증 대상' 카테고리 상품인 경우 필수
     */
    private List<ProductCertificationInfo> productCertificationInfos;

    /**
     * 인증 대상 제외 여부 정보
     */
    private CertificationTargetExcludeContent certificationTargetExcludeContent;

    /**
     * 판매자 특이 사항
     * SellerCommentUsable 값이 true일 때 입력v
     */
    private String sellerCommentContent;

    /**
     * 판매자 특이 사항 사용 여부
     */
    private Boolean sellerCommentUsable;

    /**
     * 미성년자 구매 가능 여부 : required
     * 성인 카테고리인 경우 불가능으로 입력해야 함
     */
    private Boolean minorPurchasable;

    /**
     * E쿠폰
     */
    private Ecoupon ecoupon;

    /**
     * 상품정보제공고시
     * 상품 요약 정보
     * 상품 등록 시 필수
     * 상품 수정 시에는 기존에 상품 요약 정보가 입력된 경우에만 생략할 수 있음. 이 경우 기존에 저장된 상품 요약 정보 값이 유지
     */
    private ProductInfoProvidedNotice productInfoProvidedNotice;

    /**
     * 상품 속성 목록
     */
    private List<ProductAttribute> productAttributes;

    /**
     * 문화비 소득공제 여부
     * 예외 카테고리 중 도서_일반, 도서_해외, 도서_중고, 도서_E북, 도서_오디오북, 문화비_소득공제에 해당하는 경우 입력할 수 있음
     * 미입력 시 false로 저장
     */
    private Boolean cultureCostIncomeDeductionYn;

    /**
     * 맞춤 제작 상품 여부
     */
    private Boolean customProductYn;

    /**
     * 자체 제작 상품 여부
     * 미입력 시 false로 저장
     */
    private Boolean itselfProductionProductYn;

    /**
     * 브랜드 인증 여부
     */
    private boolean brandCertificationYn;


    /**
     * SEO(Search engine optimization) 정보
     */
    private SeoInfo seoInfo;
}
