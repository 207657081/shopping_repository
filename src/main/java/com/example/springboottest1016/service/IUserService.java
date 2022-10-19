package com.example.springboottest1016.service;

import com.example.springboottest1016.pojo.UserInfo;
import com.example.springboottest1016.utils.ServerResponse;

public interface IUserService {
    public ServerResponse loginLogic(String username,String password);
    public ServerResponse registerLogic(UserInfo user);
}
