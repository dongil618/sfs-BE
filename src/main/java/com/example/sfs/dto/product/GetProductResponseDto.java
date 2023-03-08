package com.example.sfs.dto.product;

import com.example.sfs.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponseDto {
    private String productName;
    private String thumbnailImageUrl;
    private String category;
    private Integer price;
    private String discountYN;
    private Integer discountedAmount;
    private String detailImageUrlList;
    private String sizeList;
    private String colorList;
    private String instruction;
    private String sizeGuide;

    public GetProductResponseDto(Product product) {
        this.productName = product.getName();
        this.thumbnailImageUrl = product.getThumbnailImageUrl();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.discountYN = product.getDiscountYN();
        this.discountedAmount = product.getDiscountedAmount();
        this.sizeList = product.getSizeList();
        this.colorList = product.getColorList();
        this.instruction = product.getInstruction();
        this.sizeGuide = product.getSizeGuide();

    }
}
