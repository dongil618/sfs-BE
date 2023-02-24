package com.example.sfs.util.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ProductCrawlerStrategyFactory {
    private Map<ProductCrawlerStrategy, ProductCrawler> productCrawlerStrategies;

    @Autowired
    public ProductCrawlerStrategyFactory(Set<ProductCrawler> productCrawlerSet) {
        createStrategy(productCrawlerSet);
    }

    public ProductCrawler findStrategy(ProductCrawlerStrategy productCrawlerStrategy) {
        return productCrawlerStrategies.get(productCrawlerStrategy);
    }

    public void createStrategy(Set<ProductCrawler> productCrawlerSet) {
        productCrawlerStrategies = new HashMap<ProductCrawlerStrategy, ProductCrawler>();
        productCrawlerSet.forEach(
                productCrawler -> productCrawlerStrategies.put(productCrawler.getProductCrawlerStrategy(), productCrawler)
        );
    }

}
