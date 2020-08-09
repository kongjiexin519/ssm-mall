package com.spring.ssmmall.service;

import com.alipay.api.AlipayApiException;
import com.github.pagehelper.PageInfo;
import com.spring.ssmmall.model.request.CreateOrderReq;
import com.spring.ssmmall.model.vo.OrderVO;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional(rollbackFor = Exception.class)
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    String qrcode(String orderNo);

    void pay(String orderNo) ;

    void deliver(String orderNo);

    void finish(String orderNo);
}
