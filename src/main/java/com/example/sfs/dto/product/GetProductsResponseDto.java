package com.example.sfs.dto.product;

import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsResponseDto {
    private Long productId;
    private String productName;
    private String thumbnailImageUrl;
    private String detailPageUrl;
    public GetProductsResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getName();
        this.thumbnailImageUrl = product.getThumbnailImageUrl();
        this.detailPageUrl = product.getDetailPageUrl();
    }
}
