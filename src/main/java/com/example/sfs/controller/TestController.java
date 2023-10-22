package com.example.sfs.controller;

import com.example.sfs.api.naverCommerce.NaverCommerceApi;
import com.example.sfs.api.naverCommerce.dto.OAuthTokenResponseDto;
import com.example.sfs.api.naverCommerce.dto.SmartStoreImageResponseDto;
import com.example.sfs.api.naverCommerce.dto.SmartStoreProductResponseDto;
import com.example.sfs.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    private final NaverCommerceApi naverCommerceApi;
    private static final CommonUtil commonUtil = new CommonUtil();
    private String COOKIE_NAME = "NC-TOKEN";

    @GetMapping(value = "/token")
    public ResponseEntity<Void> getOAuthToken(HttpServletResponse response) {
        OAuthTokenResponseDto oAuthTokenResponseDto = naverCommerceApi.getOAuthToken(response);
        log.info("oAuthTokenResponseDto={}", oAuthTokenResponseDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value = "/smartStoreProduct/{channelProductNo}")
    public ResponseEntity<SmartStoreProductResponseDto> getSmartStoreProductTest(
            HttpServletRequest req,
            @PathVariable String channelProductNo
    ) {
        String accessToken = commonUtil.getCookie(req, this.COOKIE_NAME);
        SmartStoreProductResponseDto smartStoreProduct = naverCommerceApi.getSmartStoreProduct(accessToken, channelProductNo);
        log.info("smartStoreProduct={}", smartStoreProduct);
        return new ResponseEntity<SmartStoreProductResponseDto>(smartStoreProduct, HttpStatus.OK);
    }

    @PostMapping(value = "/smartStoreProduct/{channelProductNo}")
    public ResponseEntity<SmartStoreProductResponseDto> registerSmartStoreProductTest(
            HttpServletRequest req,
            @PathVariable String channelProductNo
    ) {
        String accessToken = commonUtil.getCookie(req, this.COOKIE_NAME);
        SmartStoreProductResponseDto smartStoreProduct = naverCommerceApi.getSmartStoreProduct(accessToken, channelProductNo);
        log.info("smartStoreProduct={}", smartStoreProduct);
        return new ResponseEntity<SmartStoreProductResponseDto>(smartStoreProduct, HttpStatus.OK);
    }

    @GetMapping(value = "/smartStoreProduct/image/upload")
    public ResponseEntity<SmartStoreImageResponseDto> uploadSmartStoreImageTest(
            HttpServletRequest req
    ) {
        String accessToken = commonUtil.getCookie(req, this.COOKIE_NAME);

        List<String> imagePaths = new ArrayList<>();
        imagePaths.add("/Users/do_ng_iill/images/PT_Overfit_Sweatshirt_(Melange,_Grey)/PT_Overfit_Sweatshirt_(Melange,_Grey)_0.jpg");
        imagePaths.add("/Users/do_ng_iill/images/PT_Overfit_Sweatshirt_(Melange,_Grey)/PT_Overfit_Sweatshirt_(Melange,_Grey)_1.jpg");

        List<File> images = commonUtil.getImageFiles(imagePaths);
        SmartStoreImageResponseDto smartStoreImageResponseDto = naverCommerceApi.uploadSmartStoreImages(accessToken, images);
        log.info("smartStoreImages={}", smartStoreImageResponseDto);
        return new ResponseEntity<SmartStoreImageResponseDto>(smartStoreImageResponseDto, HttpStatus.OK);
    }
}
