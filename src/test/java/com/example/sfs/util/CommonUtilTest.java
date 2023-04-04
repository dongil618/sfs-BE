package com.example.sfs.util;

import com.example.sfs.config.CommonConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CommonUtilTest {

    @Test
    @DisplayName("이미지 폴더 경로 출력해서 확인하기")
    public void getImageFilePath() {
        System.out.println(CommonConfig.BASE_IMG_FILE_PATH);
    }

    @Test
    @DisplayName("폴더 생성, 폴더 삭제 테스트")
    public void createFolderAndDeleteFolderTest() {
        // given
        String imagePath = CommonConfig.BASE_IMG_FILE_PATH;

        // when
        CommonUtil.createFolder(imagePath);

        // then
        Assertions.assertThat(CommonUtil.existFolder(imagePath)).isTrue();

        // when
        CommonUtil.deleteFolder(imagePath);

        // then
        Assertions.assertThat(CommonUtil.existFolder(imagePath)).isFalse();
    }

    @Test
    @DisplayName("이미지 URL을 가지고 서버에 이미지 저장")
    public void saveImageTest() throws IOException {
        // given
        String productName = "Wide Denim Pants Middle Blue (S,M,L)";
        String imageUrl = "https://www.ficelle.co.kr/web/product/big/202303/224750c66745947cfb56659a88615d6b.gif";
        String extension = CommonUtil.getExtension(imageUrl);
        String destPath = CommonConfig.BASE_IMG_FILE_PATH + "/" + CommonUtil.changeSpaceToUnderBar(productName) + "/" + CommonUtil.changeSpaceToUnderBar(productName) + "_thumbnail" + "." + extension;

        // when
        CommonUtil.saveImage(imageUrl, destPath);

        // then
        Assertions.assertThat(CommonUtil.existFile(destPath)).isTrue();
    }

    @Test
    @DisplayName("키워드의 조합 구하기")
    public void getKeywordCombinationTest() {
        // given
        List<String> keywordList = Arrays.asList(new String[]{"페미닌", "리본", "스트랩", "스틸레토", "슬링백", "7cm", "아이보리", "블랙"});

        // when
        List<String> keywordCombinations = CommonUtil.getKeywordCombination(keywordList, 2);

        // then
        Assertions.assertThat(keywordCombinations.size()).isEqualTo(28);
    }

    @Test
    @DisplayName("10보다 작은 부등호 \"< 10\" 를 \"0\"으로 변경")
    public void changeSignToZeroTest() {
        // given
        String sign = "< 10";

        // when
        String result = CommonUtil.changeSignToZero(sign);

        // then
        Assertions.assertThat(result).isEqualTo("0");
    }
}
