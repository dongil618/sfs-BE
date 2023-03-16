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
    private String thumbnailImageName;
    private String category;
    private Integer price;
    private String discountYN;
    private Integer discountedAmount;
    private String detailImageNameList;
    private String sizeList;
    private String colorList;
    private String instruction;
    private String sizeGuide;

    public GetProductResponseDto(Product product) {
        this.productName = product.getName();
        this.thumbnailImageName = product.getThumbnailImageName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.discountYN = product.getDiscountYN();
        this.discountedAmount = product.getDiscountedAmount();
        this.detailImageNameList = product.getDetailImageNameList();
        this.sizeList = product.getSizeList();
        this.colorList = product.getColorList();
        this.instruction = product.getInstruction();
        this.sizeGuide = product.getSizeGuide();

    }
}
