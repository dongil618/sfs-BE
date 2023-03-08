package com.example.sfs.util.register;

import com.example.sfs.dto.product.PostRegisterProductRequestDto;

public interface ProductRegister {

    void registerProduct(PostRegisterProductRequestDto postRegisterProductRequestDto);
    ProductRegisterStrategy getProductRegisterStrategy();

}
