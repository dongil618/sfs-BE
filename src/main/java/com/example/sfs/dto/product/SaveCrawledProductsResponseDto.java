package com.example.sfs.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveCrawledProductsResponseDto {
    private Long productId;
    private String productName;
    private String thumbnailImageUrl;
}
