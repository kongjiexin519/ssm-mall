package com.spring.ssmmall.service;

import com.github.pagehelper.PageInfo;
import com.spring.ssmmall.model.pojo.Product;
import com.spring.ssmmall.model.request.AddProductReq;
import com.spring.ssmmall.model.request.ProductListReq;

public interface ProductService {
    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);

    void updateEvaluation();
}
