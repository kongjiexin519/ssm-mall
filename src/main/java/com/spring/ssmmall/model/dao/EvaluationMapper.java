package com.spring.ssmmall.model.dao;

import com.spring.ssmmall.model.pojo.Evaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Long evaluationId);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Long evaluationId);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);

    Evaluation selectByNewUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Long productId);

    List<Evaluation> selectByUserIdAndProductIdAndOrderNo(@Param("userId") Integer userId, @Param("productId") Long productId, @Param("orderNo") Long orderNo);

    List<Evaluation> selectByProductId(Long productId);

    List<Evaluation> selectAll();
}