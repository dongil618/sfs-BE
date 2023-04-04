package com.example.sfs.controller;

import com.example.sfs.config.QueryStringArgResolver;
import com.example.sfs.dto.keyword.KeywordMatchCategoryRequestDto;
import com.example.sfs.dto.keyword.KeywordMatchCategoryResponseDto;
import com.example.sfs.service.KeywordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/match-category")
    public ResponseEntity<List<KeywordMatchCategoryResponseDto>> getKeywordMatchCategory(@QueryStringArgResolver KeywordMatchCategoryRequestDto keywordMatchCategoryRequestDto) throws JsonProcessingException {
        List<KeywordMatchCategoryResponseDto> keywordMatchCategoryResponseDtos = keywordService.getKeywordMatchCategory(keywordMatchCategoryRequestDto);
        return new ResponseEntity(keywordMatchCategoryResponseDtos, HttpStatus.OK);
    }
}
