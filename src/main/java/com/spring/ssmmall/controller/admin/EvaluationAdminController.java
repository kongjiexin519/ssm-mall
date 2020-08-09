package com.spring.ssmmall.controller.admin;

import com.spring.ssmmall.common.ApiRestResponse;
import com.spring.ssmmall.model.vo.EvaluationAdminVO;
import com.spring.ssmmall.service.EvaluationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EvaluationAdminController {
    @Resource
    EvaluationService evaluationService;

    @GetMapping("/admin/evaluation/list")
    public ApiRestResponse evaluationListForAdmin() {
        List<EvaluationAdminVO> evaluationAdminVOList = evaluationService.evaluationAdminVOList();
        return ApiRestResponse.success(evaluationAdminVOList);
    }

    @PostMapping("/admin/evaluation/disable")
    public ApiRestResponse disableReason(@RequestParam("evaluationId") Long evaluationId, @RequestParam("disableReason") String disableReason) {
        evaluationService.disable(evaluationId, disableReason);
        return ApiRestResponse.success();
    }
}
