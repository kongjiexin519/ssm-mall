package com.spring.ssmmall.model.dao;

import com.spring.ssmmall.model.pojo.FavoriteState;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteStateMapper {
    int deleteByPrimaryKey(Long rsId);

    int insert(FavoriteState record);

    int insertSelective(FavoriteState record);

    FavoriteState selectByPrimaryKey(Long rsId);

    int updateByPrimaryKeySelective(FavoriteState record);

    int updateByPrimaryKey(FavoriteState record);

    FavoriteState selectByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Long productId);

    List<FavoriteState> listForUser(Integer userId);
}