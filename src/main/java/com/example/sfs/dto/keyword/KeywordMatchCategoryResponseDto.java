package com.example.sfs.dto.keyword;

import com.example.sfs.dto.api.NaverKeywordDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KeywordMatchCategoryResponseDto {
    private String keyword; // 상품 키워드
    private String naverShoppingCategory; // 네이버 상품 카테고리
    private int monthlyPcQcCnt; // Pc 한달 검색량
    private int monthlyMobileQcCnt; // 모바일 한 달 검색량
    private float monthlyAvePcClkCnt; // Pc 한 달 광고 클릭량
    private float monthlyAveMobileClkCnt; // 모바일 한 달 광고 클릭량
    private float monthlyAvePcCtr; // Pc 한 달 광고 클릭률
    private float monthlyAveMobileCtr; // 모바일 한 달 광고 클릭률

    public KeywordMatchCategoryResponseDto(NaverKeywordDto naverKeywordDto, String keyword, String category) {
        this.keyword = keyword;
        this.naverShoppingCategory = category;
        this.monthlyPcQcCnt = Integer.parseInt(naverKeywordDto.getMonthlyPcQcCnt());
        this.monthlyMobileQcCnt = Integer.parseInt(naverKeywordDto.getMonthlyMobileQcCnt());
        this.monthlyAvePcClkCnt = Float.parseFloat(naverKeywordDto.getMonthlyAvePcClkCnt());
        this.monthlyAveMobileClkCnt = Float.parseFloat(naverKeywordDto.getMonthlyAveMobileClkCnt());
        this.monthlyAvePcCtr = Float.parseFloat(naverKeywordDto.getMonthlyAvePcCtr());
        this.monthlyAveMobileCtr = Float.parseFloat(naverKeywordDto.getMonthlyAveMobileCtr());
    }
}
