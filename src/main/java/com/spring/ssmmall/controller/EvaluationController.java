package com.spring.ssmmall.controller;

import com.spring.ssmmall.common.ApiRestResponse;
import com.spring.ssmmall.common.Constant;
import com.spring.ssmmall.model.pojo.Evaluation;
import com.spring.ssmmall.model.pojo.User;
import com.spring.ssmmall.model.vo.EvaluationUserVO;
import com.spring.ssmmall.service.EvaluationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EvaluationController {
    @Resource
    EvaluationService evaluationService;

    @GetMapping("/product/evaluation/{id}")
    public ApiRestResponse evalutionListForUser(@PathVariable("id") Long id) {
        List<EvaluationUserVO> evaluationUserVOS = evaluationService.selectByProductId(id.intValue());
        return ApiRestResponse.success(evaluationUserVOS);
    }

    @PostMapping("/evaluation/add")
    public ApiRestResponse evaluation(@RequestParam("orderNo") String orderNo, @RequestParam("productId") Long productId, @RequestParam("score") Integer score, @RequestParam("content") String content, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        EvaluationUserVO evaluation = evaluationService.evaluate(orderNo, currentUser.getId(), productId.intValue(), score, content);
        return ApiRestResponse.success(evaluation);
    }

    @PostMapping("/evaluation/enjoy")
    public ApiRestResponse enjoy(@RequestParam("evaluationId") Long evaluationId) {
        Evaluation evaluation = evaluationService.enjoy(evaluationId);
        return ApiRestResponse.success(evaluation);
    }
}
