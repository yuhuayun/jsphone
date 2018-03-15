package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import lombok.Data;

import java.io.Serializable;

/**
 * CenterCord 发送 SystemWait 以通知座席门户网关服务器尚未启动。所有登录请求都将被搁置。<br>
 * 一旦网关服务器启动， CenterCord 就会继续处理登录流程。
 * @author wangjunhua
 * @since 1.0.0
 */
@Data
public class UDSystemWait implements Serializable {

    /**
     * 标识座席会话的座席索引
     * 必须大于或等于 0
     */
    private int agentIndex;

    /**
     * 网关ID
     */
    private int switchID;
}
