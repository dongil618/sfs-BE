package com.example.sfs.model;

import com.example.sfs.dto.crawler.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String detailPageUrl;
    private String thumbnailImageUrl;
    private String category;
    private Integer price;
    private String discountYN;
    private Integer discountedAmount;
    @Column(length = 10000)
    private String detailImageUrlList;
    private String sizeList;
    private String colorList;
    private String instruction;
    private String sizeGuide;


    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.detailPageUrl = productDto.getDetailPageUrl();
        this.thumbnailImageUrl = productDto.getThumbnailImageUrl();
        this.category = productDto.getCategory();
        this.price = productDto.getPrice();
        this.discountYN = productDto.getDiscountYN();
        this.discountedAmount = productDto.getDiscountedAmount();
        this.detailImageUrlList = setListToStringComma(productDto.getDetailImageUrlList());
        this.sizeList = setListToStringComma(productDto.getSizeList());
        this.colorList = setListToStringComma(productDto.getColorList());
        this.instruction = productDto.getInstruction();
        this.sizeGuide = productDto.getSizeGuide();
    }

    public String setListToStringComma(List<String> stringList) {
        if(stringList != null) {
            return String.join(",", stringList);
        }
        return null;
    }
}
