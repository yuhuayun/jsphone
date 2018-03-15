package com.sinosoft.aspect.softphone.soap.event;

/**
 * 登陆失败异常
 * @author wangjunhua
 * @since 1.0
 */
public class LoginFailedException extends Exception{

    public LoginFailedException(){
        super();
    }

    public LoginFailedException(String msg){
        super(msg);
    }

}
