package com.example.sfs.util.api;

import com.example.sfs.api.PapagoApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PapagoApiTest {

    @Test
    @DisplayName("파파고 API로 한글 영어로 변경")
    public void changeEnToKorTest() {
        // given
        String enText = "Snap Button Two Tuck Pleats Trench Coat (Light Beige, Navy)";

        // when
        String koText = new PapagoApi().changeEnToKor(enText);

        // then
        Assertions.assertThat(koText).isEqualTo("스냅 버튼 투 턱 플리츠 트렌치 코트 (라이트 베이지, 네이비)");
    }

    @Test
    @DisplayName("파파고 API로 언어 감지")
    public void getLangCodeTest() {
        // given
        String enText = "Snap Button Two Tuck Pleats Trench Coat (Light Beige, Navy)";

        // when
        String langCode = new PapagoApi().getLangCode(enText);

        // then
        Assertions.assertThat(langCode).isEqualTo("en");
    }
}
