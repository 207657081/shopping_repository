package com.example.springboottest1016.controller;

import com.example.springboottest1016.common.Const;
import com.example.springboottest1016.pojo.UserInfo;
import com.example.springboottest1016.service.IUserService;
import com.example.springboottest1016.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/")
public class UserController {

    @Autowired
    IUserService userService;

    //登录： login.do?username=xxx&password=xxx
    @RequestMapping(value = "user/login")
    public ServerResponse login(String username, String password, HttpSession session){
        ServerResponse serverResponse = userService.loginLogic(username, password);
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,serverResponse.getDate());
        }
        return serverResponse;
    }

    @RequestMapping(value = "user/register")
    public ServerResponse register(UserInfo user) {
        return userService.registerLogic(user);
    }
}
