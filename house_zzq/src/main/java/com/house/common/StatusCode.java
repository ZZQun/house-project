package com.house.common;

/**
 * Created by treasure.zhou on 2019/3/21.
 */
public class StatusCode {

    public static final int SUCCESS = 20000;    //成功
    public static final int ERROR = 20001;      //失败
    public static final int LOGINERROR = 20002; //用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足
    public static final int REMOREERROR = 20004;//远程调用失败
    public static final int REPERROE = 20005;   //重复操作
}
