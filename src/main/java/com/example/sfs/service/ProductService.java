package com.example.sfs.service;

import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.dto.product.*;
import com.example.sfs.model.Product;
import com.example.sfs.repository.ProductRepository;
import com.example.sfs.util.crawler.ProductCrawler;
import com.example.sfs.util.crawler.ProductCrawlerStrategy;
import com.example.sfs.util.crawler.ProductCrawlerStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCrawlerStrategyFactory productCrawlerStrategyFactory;

    /*
    url을 통해 상품 가져오기
     */
    public List<GetCrawledProductsResponseDto> getCrawledProducts(GetCrawledProductsRequestDto crawledProductsRequestDto) {
        String url = crawledProductsRequestDto.getUrl();
        Integer pageNum = crawledProductsRequestDto.getPageNum();
        ProductCrawlerStrategy siteType = crawledProductsRequestDto.getSiteType();

        if(pageNum == null) {
            throw new IllegalArgumentException("페이지 수를 설정해주세요.");
        }
        if(siteType == null) {
            throw new IllegalArgumentException("웹 사이트 타입을 입력해주세요.");
        }

        ProductCrawler productCrawlerStrategy = productCrawlerStrategyFactory.findStrategy(siteType);
        List<ProductDto> productDtos = productCrawlerStrategy.getProductThumbInfos(url, pageNum);

        List<GetCrawledProductsResponseDto> crawledProductsResponseDtos = new ArrayList<>();
        for(ProductDto productDto : productDtos) {
            String productName = productDto.getName();
            String detailPageUrl = productDto.getDetailPageUrl();
            String thumbnailImageUrl = productDto.getThumbnailImageUrl();
            crawledProductsResponseDtos.add(new GetCrawledProductsResponseDto(productName, detailPageUrl, thumbnailImageUrl));
        }
        return crawledProductsResponseDtos;
    }

    public Void saveCrawledProducts(List<PostCrawledProductsRequestDto> crawledProductsRequestDtos) {
        ProductCrawlerStrategy siteType = crawledProductsRequestDtos.get(0).getSiteType();

        if(crawledProductsRequestDtos == null) {
            throw new IllegalArgumentException("요청이 잘못되었습니다.");
        }
        ProductCrawler productCrawlerStrategy = productCrawlerStrategyFactory.findStrategy(siteType);
        List<ProductDto> productDtos = new ArrayList<>();
        for(PostCrawledProductsRequestDto crawledProductsRequestDto : crawledProductsRequestDtos) {
            ProductDto productDto = productCrawlerStrategy.getProductDetailInfo(crawledProductsRequestDto);
            productDtos.add(productDto);
        }
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtos) {
            products.add(new Product(productDto));
        }
        productRepository.saveAll(products);
        return null;
    }

    public List<GetProductsResponseDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<GetProductsResponseDto> getProductsResponseDtos = new ArrayList<>();
        for(Product product : products) {
            getProductsResponseDtos.add(new GetProductsResponseDto(product));
        }
        return getProductsResponseDtos;
    }

    public GetProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).get();
        if(product == null) {
            throw new IllegalArgumentException("상품 정보가 없습니다.");
        }
        return new GetProductResponseDto(product);
    }
}
