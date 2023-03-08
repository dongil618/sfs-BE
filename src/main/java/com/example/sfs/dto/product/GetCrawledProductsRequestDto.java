package com.example.sfs.dto.product;

import com.example.sfs.util.crawler.ProductCrawlerStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCrawledProductsRequestDto {
    private String url;
    private Integer pageNum;
    private ProductCrawlerStrategy siteType; // 0: cafe24, 1: imweb, 2: sixshop
}
