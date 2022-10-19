package com.example.springboottest1016.common;

public enum ResponseCode {
    USERNAME_NOT_EMPTY(1, "用户名不能为空"),
    PASSWORD_NOT_EMPTY(2, "密码不能为空"),
    USER_NOT_EXISTS(3, "用户不存在"),
    PASSWORD_ERROR(4,"密码错误"),

    USERNAME_IS_EXISTS(5, "用户名已存在"),
    PHONE_IS_EXISTS(6,"手机号已被注册"),
    EMAIL_IS_EXISTS(7,"邮箱已被注册"),
    RESITER_DEFEAT(8,"注册失败"),
    EMAIL_NOT_EMPTY(9, "邮箱不能为空"),
    PHONE_NOT_EMPTY(10, "手机号不能为空"),
    QUESTION_NOT_EMPTY(11, "密保问题不能为空"),
    ANSWER_NOT_EMPTY(12, "密保答案不能为空"),

    ;
    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
