package com.spring.ssmmall.service;

import com.spring.ssmmall.exception.MallException;
import com.spring.ssmmall.model.pojo.User;

public interface UserService {
    void register(String username, String password) throws MallException;

    User login(String userName, String password) throws MallException;

    void updateInformation(User user);

    boolean checkAdminRole(User user);

}
