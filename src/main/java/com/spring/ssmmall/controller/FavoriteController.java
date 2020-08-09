package com.spring.ssmmall.controller;

import com.spring.ssmmall.common.ApiRestResponse;
import com.spring.ssmmall.common.Constant;
import com.spring.ssmmall.model.pojo.User;
import com.spring.ssmmall.model.vo.ProductVO;
import com.spring.ssmmall.service.FavoriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class FavoriteController {
    @Resource
    FavoriteService favoriteService;

    @PostMapping("/user/updateFavorite")
    public ApiRestResponse updateFavorite(@RequestParam("productId") Long productId, @RequestParam("favoriteState") Integer favoriteState, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        favoriteService.updateFavorite(currentUser, productId, favoriteState);
        return ApiRestResponse.success();
    }

    @PostMapping("/user/favoriteList")
    public ApiRestResponse listForUser(HttpSession session) {
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        List<ProductVO> products = favoriteService.ListForFavoriteState(currentUser);
        return ApiRestResponse.success(products);
    }

}
