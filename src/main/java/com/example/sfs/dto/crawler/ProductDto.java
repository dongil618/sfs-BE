package com.example.sfs.dto.crawler;

import com.example.sfs.dto.product.PostCrawledProductsRequestDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String detailPageUrl;
    private String thumbnailImageUrl;
    private String category;
    private Integer price;
    private String discountYN;
    private Integer discountedAmount;
    private List<String> detailImageUrlList;
    private List<String> sizeList;
    private List<String> colorList;
    private String instruction;
    private String sizeGuide;

    public ProductDto(String name, String detailPageUrl, String thumbnailImageUrl) {
        this.name = name;
        this.detailPageUrl = detailPageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public ProductDto(PostCrawledProductsRequestDto postCrawledProductsRequestDto) {
        this.name = postCrawledProductsRequestDto.getProductName();
        this.detailPageUrl = postCrawledProductsRequestDto.getDetailPageUrl();
        this.thumbnailImageUrl = postCrawledProductsRequestDto.getThumbnailImageUrl();
    }
}
