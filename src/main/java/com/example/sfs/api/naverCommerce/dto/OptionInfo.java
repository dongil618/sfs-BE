package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class OptionInfo {

    /**
     * 단독형 옵션 정렬 순서
     * 미입력 혹은 비허용 타입 입력 시 기본값인 등록순(CREATE)으로 저장됨
     * CREATE(등록순), ABC(가나다순)
     */
    private String simpleOptionSortType;

    /**
     * 단독형 옵션
     * 최대 3개까지 등록할 수 있음
     */
    private List<OptionSimple> optionSimple;

    /**
     * 직접 입력형 옵션
     * 표준형 옵션, 단독형 옵션, 조합형 옵션, 직접 입력형 옵션 중 최소 한 개는 입력해야 함
     */
    private List<OptionCustom> optionCustom;

    /**
     * 조합형 옵션 정렬 순서
     * 미입력 시 기본값인 등록순(CREATE)으로 저장됨
     * CREATE(등록순), ABC(가나다순), LOW_PRICE(낮은 가격순), HIGH_PRICE(높은 가격순)
     */
    private String optionCombinationSortType;

    /**
     * 조합형 옵션명
     * 조합형 옵션명 목록
     */
    private OptionCombinationGroupNames optionCombinationGroupNames;

    /**
     * 조합형 옵션
     * 최대 등록 가능한 옵션 개수는 조합형은 3개, 지점형은 4개
     * 장보기 상품의 경우 지점형 옵션은 반드시 등록해야 함
     * 표준형 옵션, 단독형 옵션, 조합형 옵션, 직접 입력형 옵션 중 최소 한 개는 입력해야 함
     * 표준형 옵션, 단독형 옵션, 조합형 옵션은 함께 사용할 수 없음
     */
    private List<OptionCombinations> optionCombinations;

    /**
     * 표준형 옵션 그룹
     * 표준형 옵션 그룹은 색상, 사이즈를 등록해야 함
     */
    private List<StandardOptionGroups> standardOptionGroups;

    /**
     * 표준형 옵션
     * 표준형 옵션, 단독형 옵션, 조합형 옵션, 직접 입력형 옵션 중 최소 한 개는 입력해야 함
     * 표준형 옵션, 단독형 옵션, 조합형 옵션은 함께 사용할 수 없음
     */
    private List<OptionStandards> optionStandards;

    /**
     * 옵션 재고 수량 관리 사용 여부
     * '옵션 재고 수량 관리 사용 여부'를 입력하지 않거나 false로 지정하면 수량이 9,999로 설정됨
     */
    private Boolean useStockManagement;

    /**
     * 옵션별 배송 속성 옵션값 목록
     * 옵션별 배송 속성인 경우 최소 한 개는 입력함
     * 첫 번째 옵션명에 해당하는 옵션값만 입력 가능
     * 미입력 시 기존값이 유지됨
     */
    private List<String> optionDeliveryAttributes;
}
