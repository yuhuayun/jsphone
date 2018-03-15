package com.sinosoft.aspect.softphone.soap.event;

/**
 * 坐席注册失败异常
 * @author wangjunhua
 * @since 1.0
 */
public class AgentRegisterFailedException extends Exception{

    public AgentRegisterFailedException(){
        super();
    }

    public AgentRegisterFailedException(String msg){
        super(msg);
    }

}
