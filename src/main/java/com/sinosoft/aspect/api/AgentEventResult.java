package com.sinosoft.aspect.api;

import lombok.Data;

/**
 * 软电话事件
 * @author wangjunhua
 * @since 1.0
 *
 */
@Data
public class AgentEventResult {

    /**
     * 坐席ID
     */
    private String agentId;

    /**
     * 事件ID
     */
    private int eventId;

    /**
     * 事件描述信息
     */
    private String eventDescription;

    /**
     * 事件时间戳
     */
    private long eventTimestamp;

    /**
     * 事件对象
     */
    private Object payload;
}
