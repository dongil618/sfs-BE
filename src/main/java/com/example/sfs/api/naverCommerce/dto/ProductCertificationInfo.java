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
public class ProductCertificationInfo {

    /**
     * 인증 유형 ID : required
     */
    private Integer certificationInfoId;

    /**
     * 인증 정보 종류 코드
     * 인증 정보 종류 필드에 설정 가능한 코드
     * 미입력 시 ETC로 저장됨
     * KC_CERTIFICATION(KC 인증), CHILD_CERTIFICATION(어린이제품 인증), GREEN_PRODUCTS(친환경 인증), OVERSEAS(구매대행(구매대행 선택 시 인증 정보 필수 등록)), PARALLEL_IMPORT(병행수입(병행수입 선택 시 인증 정보 필수 등록)), ETC(기타 인증)
     */
    private String certificationKindType;

    /**
     * 인증 기관명 : required
     * 어린이제품/생활용품/전기용품 공급자적합성 유형인 경우 비필수
     */
    private String name;

    /**
     * 인증번호 : required
     * 어린이제품/생활용품/전기용품 공급자적합성 유형인 경우 비필수
     */
    private String certificationNumber;

    /**
     * 인증마크 사용 여부
     * 미입력 시 false로 저장됨
     */
    private Boolean certificationMark;

    /**
     * 인증 상호명
     * 인증 유형이 방송통신기자재 적합인증/적합등록/잠정인증, 어린이제품 안전인증/안전확인인 경우 필수
     */
    private String companyName;

    /**
     * 인증일자
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String certificationDate;
}
