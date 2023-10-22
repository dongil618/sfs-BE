package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CertificationTargetExcludeContent {

    /**
     * 어린이제품 인증 대상 제외 여부
     * 어린이제품 인증 대상 카테고리 상품인 경우 필수
     * 미입력 시 false로 저장됨
     */
    private Boolean childCertifiedProductExclusionYn;

    /**
     * KC 면제 대상 타입 코드
     * 안전기준준수, 구매대행, 병행수입인 경우 필수 입력
     * SAFE_CRITERION(안전기준준수대상(안전기준준수대상 예외 카테고리가 아닌 경우에도 설정 가능, 식품 카테고리 외)), OVERSEAS(구매대행), PARALLEL_IMPORT(병행수입)
     */
    private String kcExemptionType;

    /**
     * KC 상품 인증 대상 제외 타입
     * 'KC 인증 대상' 카테고리 상품인 경우 필수
     * 미입력 시 FALSE로 저장됨
     * TRUE(KC 인증 대상 아님), FALSE(KC 인증 대상), KC_EXEMPTION_OBJECT(안전기준준수, 구매대행, 병행수입인 경우 필수 입력)
     */
    private String kcCertifiedProductExclusionYn;

    /**
     * 친환경 인증 대상 제외 여부
     * '친환경 인증 대상' 카테고리 상품인 경우 필수
     * 미입력 시 false로 저장됨
     */
    private Boolean greenCertifiedProductExclusionYn;
}
