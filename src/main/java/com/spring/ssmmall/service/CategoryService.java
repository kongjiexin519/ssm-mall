package com.spring.ssmmall.service;

import com.github.pagehelper.PageInfo;
import com.spring.ssmmall.model.pojo.Category;
import com.spring.ssmmall.model.request.AddCategoryReq;
import com.spring.ssmmall.model.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    //    @Cacheable(value = "listCategoryForCustomer")
    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
