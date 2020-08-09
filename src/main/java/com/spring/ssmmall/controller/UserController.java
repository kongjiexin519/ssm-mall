package com.spring.ssmmall.controller;

import com.spring.ssmmall.common.ApiRestResponse;
import com.spring.ssmmall.common.Constant;
import com.spring.ssmmall.exception.MallExceptionEnum;
import com.spring.ssmmall.model.pojo.User;
import com.spring.ssmmall.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Resource
    public UserService userService;

    @PostMapping("/register")
    public ApiRestResponse register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("verifyCode") String verifyCode, HttpSession session) {
        if (StringUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if (StringUtils.isEmpty(verifyCode)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_VERIFY_CODE);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }

        String code = (String) session.getAttribute("kaptchaVerifyCode");
        if (StringUtils.isEmpty(code) || !code.equals(verifyCode)) {
            return ApiRestResponse.error(MallExceptionEnum.WRONG_VERIFY_CODE);
        }
        userService.register(username, password);
        return ApiRestResponse.success();
    }

    @PostMapping("/login")
    public ApiRestResponse login(@RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("verifyCode") String verifyCode, HttpSession session) {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if (StringUtils.isEmpty(verifyCode)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_VERIFY_CODE);
        }

        String code = (String) session.getAttribute("kaptchaVerifyCode");
        if (StringUtils.isEmpty(code) || !code.equals(verifyCode)) {
            return ApiRestResponse.error(MallExceptionEnum.WRONG_VERIFY_CODE);
        }
        User user = userService.login(userName, password);
        user.setPassword(null);
        session.setAttribute(Constant.MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    @PostMapping("/user/update")
    public ApiRestResponse updateUserInfo(HttpSession session, @RequestParam String signature) {
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateInformation(user);
        return ApiRestResponse.success();
    }

    @PostMapping("/user/logout")
    public ApiRestResponse logout(HttpSession session) {
        session.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }

    @PostMapping("/adminLogin")
    public ApiRestResponse adminLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        if (userService.checkAdminRole(user)) {
            user.setPassword(null);
            session.setAttribute(Constant.MALL_USER, user);
            return ApiRestResponse.success(user);
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

}
