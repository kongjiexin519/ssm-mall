package com.spring.ssmmall.model.dao;

import com.spring.ssmmall.model.pojo.Product;
import com.spring.ssmmall.model.query.ProductListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByName(String name);

    int batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    List<Product> selectListforAdmin();

    List<Product> selectList(@Param("query") ProductListQuery query);

    void updateEvaluation();
}