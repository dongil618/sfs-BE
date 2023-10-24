package com.example.sfs.api.naverCommerce.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    /**
     * 이미지 URL(Object) : required
     */
    private RepresentativeImage representativeImage;

    /**
     *
     */
    private List<OptionalImage> optionalImages;
}
