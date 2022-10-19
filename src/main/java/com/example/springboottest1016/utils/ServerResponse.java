package com.example.springboottest1016.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {
    private int state;
    private T date;
    private String msg;

    private ServerResponse() { }
    //state=0成功
    private ServerResponse(int state) {
        this.state = state;
    }
    private ServerResponse(int state, T date) {
        this.state = state;
        this.date = date;
    }
    private ServerResponse(int state, T date, String msg) {
        this.state = state;
        this.date = date;
        this.msg = msg;
    }
//定义泛型方法,返回成功
    public static <T> ServerResponse createServerResponseBySucess() {
        return new ServerResponse(0);
    }
    public static <T> ServerResponse createServerResponseBySucess(T data) {
        return new ServerResponse(0,data);
    }
    public static <T> ServerResponse createServerResponseBySucess(T data,String msg) {
        return new ServerResponse(0,data,msg);
    }
//定义泛型方法，返回失败
    public static <T> ServerResponse createServerResponseByFail(int state) {
        return new ServerResponse(state);}
    public static <T> ServerResponse createServerResponseByFail(int state,String msg) {
        return new ServerResponse(state,null,msg);}
    public int getState() {
        return state;
    }
//定义一个方法判断接口是否成功
    @JsonIgnore
    public boolean isSuccess() {
        return this.state == 0;
    }

    public void setState(int state) {
        this.state = state;
    }
    public T getDate() {
        return date;
    }
    public void setDate(T date) {
        this.date = date;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
