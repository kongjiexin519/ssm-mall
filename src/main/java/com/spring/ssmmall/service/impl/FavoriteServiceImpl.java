package com.spring.ssmmall.service.impl;

import com.spring.ssmmall.common.Constant;
import com.spring.ssmmall.exception.MallException;
import com.spring.ssmmall.exception.MallExceptionEnum;
import com.spring.ssmmall.model.dao.FavoriteStateMapper;
import com.spring.ssmmall.model.dao.ProductMapper;
import com.spring.ssmmall.model.pojo.FavoriteState;
import com.spring.ssmmall.model.pojo.Product;
import com.spring.ssmmall.model.pojo.User;
import com.spring.ssmmall.model.vo.ProductVO;
import com.spring.ssmmall.service.FavoriteService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("favoriteService")
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
    @Resource
    ProductMapper productMapper;
    @Resource
    FavoriteStateMapper favoriteStateMapper;

    @Override
    public void updateFavorite(User user, Long productId, Integer favoriteState) {
        Product product = productMapper.selectByPrimaryKey(productId.intValue());
        if (product.getStatus() == Constant.SaleStatus.NOT_SALE) {
            throw new MallException(MallExceptionEnum.NOT_SELL);
        }

        FavoriteState oldState = favoriteStateMapper.selectByUserIdAndProductId(user.getId(), productId);
        if (oldState == null) {
            FavoriteState state = new FavoriteState();
            state.setUserId(Long.valueOf(user.getId()));
            state.setProductId(productId);
            state.setFavoriteState(favoriteState);
            state.setCreateTime(new Date());
            favoriteStateMapper.insertSelective(state);
        } else {
            oldState.setFavoriteState(favoriteState);
            favoriteStateMapper.updateByPrimaryKeySelective(oldState);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ProductVO> ListForFavoriteState(User user) {
        List<FavoriteState> favoriteStates = favoriteStateMapper.listForUser(user.getId());
        List<ProductVO> productVOS = new ArrayList<>();
        for (FavoriteState favoriteState : favoriteStates) {
            Product product = productMapper.selectByPrimaryKey(favoriteState.getProductId().intValue());
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVOS.add(productVO);
        }
        return productVOS;
    }
}
