package com.example.sfs.api.naverCommerce.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterSmartStoreProductResponseDto {
    Long originProductNo;
    Long smartstoreChannelProductNo;
    Long windowChanelProductNo;
}
