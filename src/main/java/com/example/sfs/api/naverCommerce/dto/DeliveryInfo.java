package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryInfo {

    /**
     * 배송 방법 유형 코드 : required
     * DELIVERY(택배, 소포, 등기), DIRECT(직접배송(화물배달))
     */
    private String deliveryType;

    /**
     * 배송 속성 타입 코드 : required
     * 상품 등록/수정 시: NORMAL(일반 배송), TODAY(오늘출발), OPTION_TODAY(옵션별 오늘출발), HOPE(희망일배송), TODAY_ARRIVAL(당일배송(장보기 관련 기능)), DAWN_ARRIVAL(새벽배송(장보기 관련 기능), ARRIVAL_GUARANTEE(네이버 도착 보장))
     * 상품 일괄 수정 시: NORMAL(일반 배송), TODAY(오늘출발), HOPE(희망일배송)
     */
    private String deliveryAttributeType;

    /**
     * 택배사
     * DELIVERY(택배, 소포, 등기)일 때 필수 입력
     */
    private String deliveryCompany;

    /**
     * 묶음배송 가능 여부
     * 묶음배송 그룹 코드가 존재하는 경우 자동으로 true로 설정됨
     */
    private Boolean deliveryBundleGroupUsable;

    /**
     * 묶음배송 그룹 코드
     * 묶음배송 가능이 true이고 묶음배송 그룹 코드가 null이면 기본 그룹으로 저장됨
     */
    private Integer deliveryBundleGroupId;

    /**
     * 퀵서비스 배송 지역 코드
     * SEOUL(서울 전지역), GYEONGGI(경기 전지역), GOYANG(경기 고양), GOCHON(경기 고촌), GONJIAM(경기 곤지암),
     * GWACHEON(경기 과천), GWANGMYEONG(경기 광명), GYEONGGIGWANGJU(경기 광주), GYOMUN(경기 교문리), GURI(경기 구리),
     * GUSEONG(경기 구성), GUNPO(경기 군포), GIMPO(경기 김포), BUCHEON(경기 부천), BUNDANG(경기 분당),
     * SEONGNAM(경기 성남), SUWON(경기 수원), SUJI(경기 수지), SIHEUNG(경기 시흥), ANSAN(경기 안산),
     * ANYANG(경기 안양), YONGIN(경기 용인), UIWANG(경기 의왕), UIJEONGBU(경기 의정부), ICHEON(경기 이천),
     * ILSAN(경기 일산), JICHUK(경기 지축), PAJU(경기 파주), HANAM(경기 하남), GWANGJU(광주 전지역),
     * DAEGU(대구 전지역), DAEJEON(대전 전지역), BUSAN(부산 전지역), ULSAN(울산 전지역), INCHEON(인천 전지역)
     */
    private List<String> quickServiceAreas;

    /**
     * 방문 수령 주소록 ID
     * 방문 수령 주소 코드
     */
    private Integer visitAddressId;

    /**
     * 배송비 정보 : required
     */
    private DeliveryFee deliveryFee;

    /**
     * 클레임(반품/교환) 정보(Object) : required
     */
    private ClaimDeliveryInfo claimDeliveryInfo;

    /**
     * 별도 설치비 유무
     */
    private Boolean installationFee;

    /**
     * 주문 제작 상품 발송 예정일 타입 코드
     * ETC는 상품 수정에만 사용 가능
     * 이미 저장된 '주문 후 예상 발송 기간' 값이 존재하거나 '직접 입력형'인 경우 설정 가능
     * ETC(직접 입력형), TWO(선택형: 2일), THREE(선택형: 3일), FOUR(선택형: 4일), FIVE(선택형: 5일), SIX(선택형: 6일), SEVEN(선택형: 7일), EIGHT(선택형: 8일), NINE(선택형: 9일), TEN(선택형: 10일), ELEVEN(선택형: 11일), TWELVE(선택형: 12일), THIRTEEN(선택형: 13일 ), FOURTEEN(선택형: 14일)
     */
    private String expectedDeliveryPeriodType;

    /**
     * 발송 예정일 직접 입력 값
     */
    private String expectedDeliveryPeriodDirectInput;

    /**
     * 오늘출발 상품 재고 수량
     */
    private Integer todayStockQuantity;

    /**
     * 주문 확인 후 제작 상품 여부
     */
    private Boolean customProductAfterOrderYn;

    /**
     * 희망일배송 그룹 번호
     * 배송 속성 타입 코드가 희망일배송이고 희망일배송 그룹 번호가 Null이면 기본 그룹으로 저장됨
     */
    private Integer hopeDeliveryGroupId;
}
