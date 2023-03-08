package com.example.sfs.util.crawler;

import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.dto.product.PostCrawledProductsRequestDto;

import java.util.List;

public interface ProductCrawler {
    public List<ProductDto> getProductDetailInfos(String url);
    public ProductDto getProductDetailInfo(PostCrawledProductsRequestDto postCrawledProductsRequestDto);
    public List<ProductDto> getProductThumbInfos(String url, Integer pageNum);
    ProductCrawlerStrategy getProductCrawlerStrategy();
}
