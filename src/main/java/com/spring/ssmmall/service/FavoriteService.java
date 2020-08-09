package com.spring.ssmmall.service;

import com.spring.ssmmall.model.pojo.User;
import com.spring.ssmmall.model.vo.ProductVO;

import java.util.List;

public interface FavoriteService {
    void updateFavorite(User user, Long productId, Integer favoriteState);

    List<ProductVO> ListForFavoriteState(User user);
}
