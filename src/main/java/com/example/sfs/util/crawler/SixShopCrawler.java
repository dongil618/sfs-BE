package com.example.sfs.util.crawler;

import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.dto.product.PostCrawledProductsRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SixShopCrawler implements ProductCrawler {
    @Override
    public List<ProductDto> getProductDetailInfos(String url) {
        return null;
    }

    @Override
    public ProductDto getProductDetailInfo(PostCrawledProductsRequestDto postCrawledProductsRequestDto) {
        return null;
    }

    @Override
    public List<ProductDto> getProductThumbInfos(String siteUrl, Integer pageNum) {
        return null;
    }

    @Override
    public ProductCrawlerStrategy getProductCrawlerStrategy() {
        return ProductCrawlerStrategy.SixShopCrawler;
    }
}
