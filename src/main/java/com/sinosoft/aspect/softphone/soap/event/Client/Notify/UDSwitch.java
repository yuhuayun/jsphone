package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 此事件用于通知座席他当前登录的网关服务器已关闭
 *
 * @author wangjunhua
 * @since 1.0.0
 */
@Data
public class UDSwitch implements Serializable {

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
