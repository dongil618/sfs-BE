package com.example.sfs.controller;

import com.example.sfs.config.QueryStringArgResolver;
import com.example.sfs.dto.product.*;
import com.example.sfs.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    @GetMapping("/crawler/products")
    public ResponseEntity<List<GetCrawledProductsResponseDto>> getCrawledProducts(@QueryStringArgResolver GetCrawledProductsRequestDto crawledProductsRequestDto) {
        List<GetCrawledProductsResponseDto> crawledProductsResponseDtos = productService.getCrawledProducts(crawledProductsRequestDto);
        return new ResponseEntity(crawledProductsResponseDtos, HttpStatus.OK);
    }

    @PostMapping("/crawler/products")
    public ResponseEntity<Void> saveCrawledProducts(@RequestBody List<PostCrawledProductsRequestDto> postCrawledProductsRequestDtos) throws IOException {
        return new ResponseEntity(productService.saveCrawledProducts(postCrawledProductsRequestDtos), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<GetProductsResponseDto>> getProducts() {
        return new ResponseEntity(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<GetProductResponseDto>getProduct(@PathVariable Long productId) {
        return new ResponseEntity(productService.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping("/products/{productId}")
    public ResponseEntity<Void> registerProduct(
            @PathVariable Long productId,
            @RequestBody PostRegisterProductRequestDto postRegisterProductRequestDto,
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {
        if(postRegisterProductRequestDto.getCategory() == null) {
            throw new IllegalArgumentException("Category is null");
        }
        return new ResponseEntity(productService.registerProduct(productId, postRegisterProductRequestDto, req, res), HttpStatus.OK);
    }
}
