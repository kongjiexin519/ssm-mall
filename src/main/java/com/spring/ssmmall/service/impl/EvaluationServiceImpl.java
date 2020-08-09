package com.spring.ssmmall.service.impl;

import com.spring.ssmmall.common.Constant;
import com.spring.ssmmall.exception.MallException;
import com.spring.ssmmall.exception.MallExceptionEnum;
import com.spring.ssmmall.model.dao.EvaluationMapper;
import com.spring.ssmmall.model.dao.OrderMapper;
import com.spring.ssmmall.model.dao.ProductMapper;
import com.spring.ssmmall.model.dao.UserMapper;
import com.spring.ssmmall.model.pojo.Evaluation;
import com.spring.ssmmall.model.pojo.Order;
import com.spring.ssmmall.model.pojo.Product;
import com.spring.ssmmall.model.pojo.User;
import com.spring.ssmmall.model.vo.EvaluationAdminVO;
import com.spring.ssmmall.model.vo.EvaluationUserVO;
import com.spring.ssmmall.model.vo.ProductVO;
import com.spring.ssmmall.service.EvaluationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("evaluationService")
@Transactional
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    EvaluationMapper evaluationMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    OrderMapper orderMapper;

    @Override
    public EvaluationUserVO evaluate(String orderNo, Integer userId, Integer productId, Integer score, String content) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null && order.getOrderStatus() != Constant.OrderStatusEnum.FINISHED.getCode()) {
            throw new MallException(MallExceptionEnum.WRONG_ORDER_STATUS);
        }

        if (userId != order.getUserId()) {
            throw new MallException(MallExceptionEnum.NOT_YOUR_ORDER);
        }

        Evaluation evaluation = evaluationMapper.selectByNewUserIdAndProductId(userId, productId.longValue());
        if (evaluation != null) {
            Date date = new Date();
            long diff = date.getTime() - evaluation.getCreateTime().getTime();
            long minute = diff / (1000 * 60);
            System.out.println("minute: " + minute);
            System.out.println("expire time: " + Constant.EVALUATION_EXPIRE_TIME);
            if (minute < Constant.EVALUATION_EXPIRE_TIME) {
                throw new MallException(MallExceptionEnum.EXPIRE_TIME);
            }
        }
        Evaluation newEvaluation = new Evaluation();
        newEvaluation.setContent(content);
        newEvaluation.setScore(score);
        newEvaluation.setCreateTime(new Date());
        newEvaluation.setProductId(productId.longValue());
        newEvaluation.setMemberId(userId.longValue());
        newEvaluation.setEnjoy(0);
        newEvaluation.setState("enable");
        newEvaluation.setOrderNo(orderNo);
        evaluationMapper.insertSelective(newEvaluation);
        EvaluationUserVO evaluationUserVO = new EvaluationUserVO();
        BeanUtils.copyProperties(newEvaluation, evaluationUserVO);
        return evaluationUserVO;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<EvaluationUserVO> selectByProductId(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        List<Evaluation> evaluationList = evaluationMapper.selectByProductId(productId.longValue());
        List<EvaluationUserVO> evaluationUserVOS = new ArrayList<>();
        for (Evaluation evaluation : evaluationList) {
            User user = userMapper.selectByPrimaryKey(evaluation.getMemberId().intValue());
            EvaluationUserVO evaluationUserVO = new EvaluationUserVO();
            BeanUtils.copyProperties(evaluation, evaluationUserVO);
            user.setPassword(null);
            evaluationUserVO.setUser(user);
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            evaluationUserVO.setProductVO(productVO);
            evaluationUserVOS.add(evaluationUserVO);
        }
        return evaluationUserVOS;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<EvaluationAdminVO> evaluationAdminVOList() {
        List<Evaluation> evaluationList = evaluationMapper.selectAll();
        List<EvaluationAdminVO> evaluationAdminVOList = new ArrayList<>();
        for (Evaluation evaluation : evaluationList) {
            User user = userMapper.selectByPrimaryKey(evaluation.getMemberId().intValue());
            Product product = productMapper.selectByPrimaryKey(evaluation.getProductId().intValue());
            EvaluationAdminVO evaluationAdminVO = new EvaluationAdminVO();
            BeanUtils.copyProperties(evaluation, evaluationAdminVO);
            user.setPassword(null);
            evaluationAdminVO.setUser(user);
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            evaluationAdminVO.setProductVO(productVO);
            evaluationAdminVOList.add(evaluationAdminVO);
        }
        return evaluationAdminVOList;
    }

    @Override
    public void disable(Long evaluationId, String disableReason) {
        Evaluation evaluation = evaluationMapper.selectByPrimaryKey(evaluationId);
        evaluation.setState("disable");
        evaluation.setDisableReason(disableReason);
        evaluation.setDisableTime(new Date());
        evaluationMapper.updateByPrimaryKeySelective(evaluation);
    }

    @Override
    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation = evaluationMapper.selectByPrimaryKey(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationMapper.updateByPrimaryKeySelective(evaluation);
        return evaluation;
    }
}
