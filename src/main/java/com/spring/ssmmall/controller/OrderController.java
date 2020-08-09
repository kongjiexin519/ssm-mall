package com.spring.ssmmall.controller;

import com.github.pagehelper.PageInfo;
import com.spring.ssmmall.common.ApiRestResponse;
import com.spring.ssmmall.model.request.CreateOrderReq;
import com.spring.ssmmall.model.vo.OrderVO;
import com.spring.ssmmall.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    OrderService orderService;

    @PostMapping("/order/create")
    public ApiRestResponse create(@RequestBody CreateOrderReq createOrderReq) {
        String orderNo = orderService.create(createOrderReq);
        return ApiRestResponse.success(orderNo);
    }

    @GetMapping("/order/detail")
    public ApiRestResponse detail(@RequestParam String orderNo) {
        OrderVO detail = orderService.detail(orderNo);
        return ApiRestResponse.success(detail);
    }

    @GetMapping("/order/list")
    public ApiRestResponse listForCustomer(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForCustomer(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @PostMapping("/order/cancel")
    public ApiRestResponse cancel(@RequestParam String orderNo) {
        orderService.cancel(orderNo);
        return ApiRestResponse.success();
    }

    @GetMapping("/order/qrcode")
    public ApiRestResponse qrcode(@RequestParam String orderNo) {
        String qrcode = orderService.qrcode(orderNo);
        return ApiRestResponse.success(qrcode);
    }

    @GetMapping("/pay")
    public ApiRestResponse pay(@RequestParam String orderNo) {
        orderService.pay(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("/order/finish")
    public ApiRestResponse finish(@RequestParam String orderNo) {
        orderService.finish(orderNo);
        return ApiRestResponse.success();
    }

}
