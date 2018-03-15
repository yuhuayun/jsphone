package com.sinosoft.aspect.softphone.soap.event.Client.Notify;


import lombok.Data;

/**
 * 当有事件发表时，将发送 AgentEvent 事件对象，其中包含全部座席事件及他们的有效载荷。
 * @author wangjunhua
 * @since 1.0.0
 *
 */
@Data
public class AgentEvent {

	/**
	 * LDAP 用户代号
	 * 必须为具有 1 到 25 个字符的非空字符串
	 */
	private String agentId;

	/**
	 * 标识座席会话的座席索引
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 事件描述
	 */
	private String eventDescription;

	/**
	 * 事件编号
	 */
	private int eventId;

	/**
	 * 事件时间
	 */
	private long eventTimestamp;

	/**
	 * 事件对象
	 */
	private Object payload;

}
