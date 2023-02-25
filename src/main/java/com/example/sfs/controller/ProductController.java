package com.example.sfs.controller;

import com.example.sfs.dto.product.*;
import com.example.sfs.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/crawler/products")
    public ResponseEntity<List<GetCrawledProductsResponseDto>> getCrawledProducts(@RequestBody GetCrawledProductsRequestDto crawledProductsRequestDto) {
        List<GetCrawledProductsResponseDto> crawledProductsResponseDtos = productService.getCrawledProducts(crawledProductsRequestDto);
        return new ResponseEntity(crawledProductsResponseDtos, HttpStatus.OK);
    }

    @PostMapping("/crawler/products")
    public ResponseEntity<Void> saveCrawledProducts(@RequestBody List<PostCrawledProductsRequestDto> postCrawledProductsRequestDtos){
        return new ResponseEntity<>(productService.saveCrawledProducts(postCrawledProductsRequestDtos), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<GetProductsResponseDto>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<GetProductResponseDto> getProducts(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }
}
