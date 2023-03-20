package com.example.sfs.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PapagoResult {
    String srcLangType;
    String tarLangType;
    String translatedText;
}
