package com.example.sfs.service;

import com.example.sfs.dto.api.NaverKeywordDto;
import com.example.sfs.dto.keyword.KeywordMatchCategoryRequestDto;
import com.example.sfs.dto.keyword.KeywordMatchCategoryResponseDto;
import com.example.sfs.api.NaverSearchAdApi;
import com.example.sfs.util.crawler.CategoryCrawler;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final NaverSearchAdApi naverSearchAdApi;
    private final CategoryCrawler categoryCrawler;

    public List<KeywordMatchCategoryResponseDto> getKeywordMatchCategory(KeywordMatchCategoryRequestDto keywordMatchCategoryRequestDto) throws JsonProcessingException {
        String keywords = keywordMatchCategoryRequestDto.getKeywords();
        List<String> keywordList = Arrays.asList(keywords.split(","));

        // 키워드가 1개일 때 처리
        if(keywordList.size() == 1) {
            throw new IllegalArgumentException("There are no keywords to match.");
        }

        // 각각의 naver API 호출 및 카테고리 가져오기
        List<KeywordMatchCategoryResponseDto> keywordMatchCategoryResponseDtos = new ArrayList<>();
        for(String keyword : keywordList) {
            keyword = URLDecoder.decode(keyword);
            keyword = keyword.trim();
            NaverKeywordDto naverKeywordDto = naverSearchAdApi.getRelKeyword(keyword);
            String category = categoryCrawler.getProductCategory(keyword, "/");

            KeywordMatchCategoryResponseDto keywordMatchCategoryResponseDto = new KeywordMatchCategoryResponseDto(naverKeywordDto, keyword, category);
            keywordMatchCategoryResponseDtos.add(keywordMatchCategoryResponseDto);
        }

        return keywordMatchCategoryResponseDtos;
    }
}
