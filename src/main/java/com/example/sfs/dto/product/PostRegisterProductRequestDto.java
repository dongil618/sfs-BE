package com.example.sfs.dto.product;

import com.example.sfs.util.register.ProductRegisterStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterProductRequestDto {
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
    private ProductRegisterStrategy siteType; // 0 : 스마트스토어 1,2..?
}
