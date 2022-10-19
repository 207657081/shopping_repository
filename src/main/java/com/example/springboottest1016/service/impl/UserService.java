package com.example.springboottest1016.service.impl;

import com.example.springboottest1016.common.Const;
import com.example.springboottest1016.common.ResponseCode;
import com.example.springboottest1016.dao.UserInfoMapper;
import com.example.springboottest1016.pojo.UserInfo;
import com.example.springboottest1016.service.IUserService;
import com.example.springboottest1016.utils.DateUtil;
import com.example.springboottest1016.utils.MD5Utils;
import com.example.springboottest1016.utils.ServerResponse;
import com.example.springboottest1016.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public ServerResponse loginLogic(String username, String password) {
        //1、用户名和密码的非空判断
        if (StringUtils.isBlank(username)) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EMPTY.getCode(),
                    ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(password)) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_NOT_EMPTY.getCode(),
                    ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }
        //2、查看用户名是否存在
        Integer count = userInfoMapper.findByUsername(username);
        if (count==0) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.USER_NOT_EXISTS.getCode(),
                    ResponseCode.USER_NOT_EXISTS.getMsg());
        }
        //3、根据用户名和密码进行查询
        UserInfo user = userInfoMapper.findByUsernameAndPassword(username, MD5Utils.getMD5Code(password));
        if (user==null) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_ERROR.getCode(),
                    ResponseCode.PASSWORD_ERROR.getMsg());
        }
        //4、返回结果
        return ServerResponse.createServerResponseBySucess(convert(user));
    }

    @Override
    public ServerResponse registerLogic(UserInfo user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EMPTY.getCode(),
                    ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_NOT_EMPTY.getCode(),
                    ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(user.getPhone())) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.PHONE_NOT_EMPTY.getCode(),
                    ResponseCode.PHONE_NOT_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(user.getEmail())) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.EMAIL_NOT_EMPTY.getCode(),
                    ResponseCode.EMAIL_NOT_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(user.getQuestion())) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.QUESTION_NOT_EMPTY.getCode(),
                    ResponseCode.QUESTION_NOT_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(user.getAnswer())) {
            return ServerResponse.createServerResponseByFail(
                    ResponseCode.ANSWER_NOT_EMPTY.getCode(),
                    ResponseCode.ANSWER_NOT_EMPTY.getMsg());
        }
        Integer byUsername = userInfoMapper.findByUsername(user.getUsername());
        if (byUsername>0) {
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_IS_EXISTS.getCode(), ResponseCode.USERNAME_IS_EXISTS.getMsg());
        }
        Integer byPhone = userInfoMapper.findByPhone(user.getPhone());
        if (byPhone>0) {
            return ServerResponse.createServerResponseByFail(ResponseCode.PHONE_IS_EXISTS.getCode(),
                    ResponseCode.PHONE_IS_EXISTS.getMsg());
        }
        Integer byEmail = userInfoMapper.findByEmail(user.getEmail());
        if (byEmail>0) {
            return ServerResponse.createServerResponseByFail(ResponseCode.EMAIL_IS_EXISTS.getCode(),
                    ResponseCode.EMAIL_IS_EXISTS.getMsg());
        }
        user.setRole(Const.NORMAL_USER);
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));
        int success = userInfoMapper.insert(user);
        if (success==0) {
            return ServerResponse.createServerResponseByFail(ResponseCode.RESITER_DEFEAT.getCode(), ResponseCode.RESITER_DEFEAT.getMsg());
        }
        return ServerResponse.createServerResponseBySucess();
    }

    //将UserInfo类转换成前端需要的OV类
    private UserVO convert(UserInfo user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setPhone(user.getPhone());
        userVO.setEmail(user.getEmail());
        userVO.setCreateTime(DateUtil.Date2String(user.getCreateTime()));
        userVO.setUpdateTime(DateUtil.Date2String(user.getUpdateTime()));
        return userVO;
    }
}
