package com.example.sfs.util.register;

import com.example.sfs.dto.product.PostRegisterProductRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProductRegister {

    void registerProduct(PostRegisterProductRequestDto postRegisterProductRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception;
    ProductRegisterStrategy getProductRegisterStrategy();

}
