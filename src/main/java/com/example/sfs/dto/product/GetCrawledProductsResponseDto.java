package com.example.sfs.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCrawledProductsResponseDto {
    private String productName;
    private String detailPageUrl;
    private String thumbnailImageUrl;
}
