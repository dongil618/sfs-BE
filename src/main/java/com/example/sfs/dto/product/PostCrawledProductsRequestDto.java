package com.example.sfs.dto.product;

import com.example.sfs.util.crawler.ProductCrawlerStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCrawledProductsRequestDto {
    private String productName;
    private String detailPageUrl;
    private String thumbnailImageUrl;
    private ProductCrawlerStrategy siteType;
}
