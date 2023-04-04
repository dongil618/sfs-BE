package com.example.sfs.dto.api;

import com.example.sfs.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverKeywordDto {
    private String relKeyword;
    private String monthlyPcQcCnt;   // Pc 한 달 검색량
    private String monthlyMobileQcCnt; // Mobile 한 달 검색량
    private String monthlyAvePcClkCnt; // Pc 한 달 키워드 당 광고 클릭 수
    private String monthlyAveMobileClkCnt; // Mobile 한 달 키워드 당 광고 클릭 수
    private String monthlyAvePcCtr; // Pc 한 달 키워드 클릭률
    private String monthlyAveMobileCtr; // 한 달 키워드 클릭률
    private String plAvgDepth;
    private String compIdx;

    public NaverKeywordDto(NaverKeywordDto naverKeywordDto) {
        this.relKeyword = naverKeywordDto.getRelKeyword();
        this.monthlyPcQcCnt = CommonUtil.changeSignToZero(naverKeywordDto.getMonthlyPcQcCnt());
        this.monthlyMobileQcCnt = CommonUtil.changeSignToZero(naverKeywordDto.getMonthlyMobileQcCnt());
        this.monthlyAvePcClkCnt = CommonUtil.changeSignToZero(naverKeywordDto.getMonthlyAvePcClkCnt());
        this.monthlyAveMobileClkCnt = CommonUtil.changeSignToZero(naverKeywordDto.getMonthlyAveMobileClkCnt());
        this.monthlyAvePcCtr = CommonUtil.changeSignToZero(naverKeywordDto.getMonthlyAvePcCtr());
        this.monthlyAveMobileCtr = CommonUtil.changeSignToZero(naverKeywordDto.getMonthlyAveMobileCtr());
        this.plAvgDepth = naverKeywordDto.getPlAvgDepth();
        this.compIdx = naverKeywordDto.getCompIdx();
    }
}
