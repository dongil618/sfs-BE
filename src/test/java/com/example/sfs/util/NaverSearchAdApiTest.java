package com.example.sfs.util;

import com.example.sfs.dto.api.NaverKeywordDto;
import com.example.sfs.util.api.NaverSearchAdApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NaverSearchAdApiTest {
    @Test
    @DisplayName("네이버 광고 API 테스트")
    public void getKeyword() throws IOException {
        // given
        String keyword = "여자 단가라 니트";

        // when
        NaverKeywordDto naverKeywordDto = new NaverSearchAdApi().getRelKeyword(keyword);

        // then
        Assertions.assertThat(naverKeywordDto.getRelKeyword()).isEqualTo("여자단가라니트");
    }
}
