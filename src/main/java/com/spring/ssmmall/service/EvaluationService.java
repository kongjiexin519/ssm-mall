package com.spring.ssmmall.service;

import com.spring.ssmmall.model.pojo.Evaluation;
import com.spring.ssmmall.model.vo.EvaluationAdminVO;
import com.spring.ssmmall.model.vo.EvaluationUserVO;

import java.util.List;

public interface EvaluationService {
    EvaluationUserVO evaluate(String orderNo, Integer userId, Integer productId, Integer score, String content);

    List<EvaluationUserVO> selectByProductId(Integer productId);

    List<EvaluationAdminVO> evaluationAdminVOList();

    void disable(Long evaluationId, String disableReason);

    Evaluation enjoy(Long evaluationId);
}
