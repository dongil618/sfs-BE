package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {

    /**
     * 출간일 : required
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String publishDay;

    /**
     * 출판사 : required
     */
    private Publisher publisher;

    /**
     * 글작가명 : required
     */
    private List<Author> authors;

    /**
     * 그림작가명
     */
    private List<Illustrator> illustrators;

    /**
     * 번역자명
     */
    private List<NaTranslator> translators;
}
