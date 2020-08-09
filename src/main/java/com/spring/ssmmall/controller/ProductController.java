package com.spring.ssmmall.controller;

import com.github.pagehelper.PageInfo;
import com.spring.ssmmall.common.ApiRestResponse;
import com.spring.ssmmall.model.pojo.Product;
import com.spring.ssmmall.model.request.ProductListReq;
import com.spring.ssmmall.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProductController {
    @Resource
    ProductService productService;

    @PostMapping("/product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @PostMapping("/product/list")
    public ApiRestResponse list(ProductListReq productListReq) {
        PageInfo pageInfo = productService.list(productListReq);
        return ApiRestResponse.success(pageInfo);
    }
}
