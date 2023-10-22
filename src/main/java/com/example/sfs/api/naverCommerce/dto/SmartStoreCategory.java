package com.example.sfs.api.naverCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SmartStoreCategory {
    private String wholeCategoryName;
    private String id;
    private String name;
    private boolean last;
}
