package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProvidedNoticeBase {

    /**
     * 제품하자/오배송에 따른 청약철회 조항 : required
     * 제품하자ㆍ오배송 등에 따른 청약철회 등의 경우 청약철회 등의 기한 및 통신판매업자가 부담하는 반품 비용 등에 관한 정보
     * 미입력 시 상품상세 참조로 입력됨
     *  - 0 (전자상거래등에서의소비자보호에관한법률 등에 의한 제품의 하자 또는 오배송 등으로 인한 청약철회의 경우에는 상품 수령 후 3개월 이내, 그 사실을 안 날 또는 알 수 있었던 날로부터 30일 이내에 청약철회를 할 수 있으며, 반품 비용은 통신판매업자가 부담합니다.)
     *  - 1 (상품상세 참조)
     */
    private String returnCostReason;

    /**
     * 제품하자가 아닌 소비자의 단순변심에 따른 청약철회가 불가능한 경우 그 구체적 사유와 근거 : required
     * 미입력 시 상품상세 참조로 입력
     *  - 0 (전자상거래 등에서의 소비자보호에 관한 법률 등에 의한 청약철회 제한 사유에 해당하는 경우 및 기타 객관적으로 이에 준하는 것으로 인정되는 경우 청약철회가 제한될 수 있습니다.)
     *  - 1 (상품상세 참조)
     */
    private String noRefundReason;

    /**
     * 재화 등의 교환ㆍ반품ㆍ보증 조건 및 품질 보증 기준 : required
     * 미입력 시 상품상세 참조로 입력
     *  - 0 (소비자분쟁해결기준(공정거래위원회 고시) 및 관계법령에 따릅니다.)
     *  - 1 (상품상세 참조)
     */
    private String qualityAssuranceStandard;

    /**
     * 대금을 환불받기 위한 방법과 환불이 지연될 경우 지연배상금을 지급받을 수 있다는 사실 및 배상금 지급의 구체적인 조건·절차) : required
     * 미입력 시 상품상세 참조로 입력
     *  - 0 (주문취소 및 대금의 환불은 네이버페이 마이페이지에서 신청할 수 있으며, 전자상거래 등에서의 소비자보호에 관한 법률에 따라 소비자의 청약철회 후 판매자가 재화 등을 반환 받은 날로부터 3영업일 이내에 지급받은 대금의 환급을 정당한 사유 없이 지연하는 때에는 소비자는 지연기간에 대해서 연 15%의 지연배상금을 판매자에게 청구할 수 있습니다.)
     *  - 1 (상품상세 참조)
     */
    private String compensationProcedure;

    /**
     * 소비자피해보상의 처리, 재화 등에 대한 불만 처리 및 소비자와 사업자 사이의 분쟁 처리에 관한 사항 : required
     * 미입력 시 상품상세 참조로 입력
     * 0 (소비자분쟁해결기준(공정거래위원회 고시) 및 관계법령에 따릅니다.)
     * 1 (상품상세 참조)
     */
    private String troubleShootingContents;

//    /**
//     * 발행자 : required
//     */
//    private String issuer;
//
//    /**
//     * 유효기간 시작일
//     */
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//    private String periodStartDate;
//
//    /**
//     * 유효기간 종료일
//     */
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//    private String periodEndDate;
//
//    /**
//     * 유효기간(구매일로부터 00일)
//     * periodStartDate, periodEndDate를 입력하지 않은 경우 필수
//     */
//    private Integer periodDays;
//
//    /**
//     * 이용 조건 : required
//     */
//    private String termsOfUse;
//
//    /**
//     * 이용 가능 매장(장소)
//     * useStorePlace, useStoreAddressId, useStoreUrl 셋 중 하나는 필수
//     */
//    private String useStorePlace;
//
//    /**
//     * 이용 가능 매장(판매자 주소 ID)
//     * useStorePlace, useStoreAddressId, useStoreUrl 셋 중 하나는 필수
//     */
//    private Integer useStoreAddressId;
//
//    /**
//     * 이용 가능 매장(URL)
//     * useStorePlace, useStoreAddressId, useStoreUrl 셋 중 하나는 필수
//     */
//    private String useStoreUrl;
//
//    /**
//     * 잔액 환급 조건 : required
//     */
//    private String refundPolicy;
//
//    /**
//     * 소비자 상담 관련 전화번호 : required
//     */
//    private String customerServicePhoneNumber;
}
