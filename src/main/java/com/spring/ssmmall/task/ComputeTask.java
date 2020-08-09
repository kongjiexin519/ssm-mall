package com.spring.ssmmall.task;

import com.spring.ssmmall.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComputeTask {
    @Resource
    ProductService productService;

    @Scheduled(cron = "0 * * * * ?")
    public void updateEvaluation() {
        productService.updateEvaluation();
        System.out.println("update evaluation");
    }
}
