package com.example.sfs.api.naverCommerce.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmartStoreProductResponseDto extends InvalidResponseDto{

    OriginProduct originProduct;
    SmartStoreChannelProduct smartstoreChannelProduct;
    WindowChanelProduct windowChanelProduct;
}
