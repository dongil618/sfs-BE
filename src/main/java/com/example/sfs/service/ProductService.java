package com.example.sfs.service;

import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.dto.product.*;
import com.example.sfs.model.Product;
import com.example.sfs.repository.ProductRepository;
import com.example.sfs.util.CommonUtil;
import com.example.sfs.util.crawler.ProductCrawler;
import com.example.sfs.util.crawler.ProductCrawlerStrategy;
import com.example.sfs.util.crawler.ProductCrawlerStrategyFactory;
import com.example.sfs.util.register.ProductRegister;
import com.example.sfs.util.register.ProductRegisterStrategy;
import com.example.sfs.util.register.ProductRegisterStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCrawlerStrategyFactory productCrawlerStrategyFactory;
    private final ProductRegisterStrategyFactory productRegisterStrategyFactory;

    /**
     * url을 통해 상품 크롤링해서 가져오기
     * @param crawledProductsRequestDto
     * @return
     */
    public List<GetCrawledProductsResponseDto> getCrawledProducts(GetCrawledProductsRequestDto crawledProductsRequestDto) {
        String siteUrl = crawledProductsRequestDto.getSiteUrl();
        Integer pageNum = crawledProductsRequestDto.getPageNum();
        ProductCrawlerStrategy siteType = crawledProductsRequestDto.getSiteType();

        if(pageNum == null) {
            throw new IllegalArgumentException("페이지 수를 설정해주세요.");
        }
        if(siteType == null) {
            throw new IllegalArgumentException("웹 사이트 타입을 입력해주세요.");
        }

        ProductCrawler productCrawlerStrategy = productCrawlerStrategyFactory.findStrategy(siteType);
        List<ProductDto> productDtos = productCrawlerStrategy.getProductThumbInfos(siteUrl, pageNum);

        List<GetCrawledProductsResponseDto> crawledProductsResponseDtos = new ArrayList<>();
        for(ProductDto productDto : productDtos) {
            String productName = productDto.getName();
            String detailPageUrl = productDto.getDetailPageUrl();
            String thumbnailImageUrl = productDto.getThumbnailImageUrl();
            crawledProductsResponseDtos.add(new GetCrawledProductsResponseDto(productName, detailPageUrl, thumbnailImageUrl));
        }
        return crawledProductsResponseDtos;
    }

    // 상품 이미지 서버(로컬 and 운영)에 저장 => 이미지 명 만든 후 URL이 아닌 이미지 명만 DB에 저장!
    public Void saveCrawledProducts(List<PostCrawledProductsRequestDto> crawledProductsRequestDtos) throws IOException {
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

        // ProductDto -> Product 상품 이미지 저장하기
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtos) {
            CommonUtil.saveThumbnailImage(productDto);
            CommonUtil.saveDetailImageList(productDto);
            String thumbnailImageFileName = CommonUtil.getThumbnailImageFileName(productDto);
            List<String> detailImageFileNameList = CommonUtil.getDetailImageFileNameList(productDto);
            // url -> 이미지 저장 후 이미지 이름으로 세팅
            productDto.setThumbnailImageUrl(thumbnailImageFileName);
            productDto.setDetailImageUrlList(detailImageFileNameList);
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

    public Void registerProduct(Long productId, PostRegisterProductRequestDto postRegisterProductRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        /* todo : productId로 조회 후 request와 다른 부분은 DB업데이트 및 상품 등록 진행 */
        ProductRegisterStrategy siteType = postRegisterProductRequestDto.getSiteType();
        if(siteType == null) {
            throw new IllegalArgumentException("웹 사이트 타입을 입력해주세요.");
        }
        // Product update => JPA 업데이트 사용법 알아보기
        // 셀레니움으로 상품 등록하기
        ProductRegister productRegisterStrategy = productRegisterStrategyFactory.findStrategy(siteType);
        productRegisterStrategy.registerProduct(postRegisterProductRequestDto, req, res);
        return null;
    }
}
