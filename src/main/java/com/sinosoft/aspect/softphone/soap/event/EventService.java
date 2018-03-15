package com.sinosoft.aspect.softphone.soap.event;

import java.io.IOException;

import com.sinosoft.aspect.softphone.soap.event.Client.Notify.AgentEvent;


/**
 * Unified IP 事件子系统设计为每个门户有一个发行者和一个使用者。 <br>
 * 发行者为座席 Web 服务，使用者为事件 Web 服务。<br>
 * 事件 Web 服务使用所有消息并根据用户的预订将它们放在存储桶中。<br>
 * 然后使用事件 Web 服务的 SOAP API 对这些存储桶按存储桶进行轮询。<br>
 * 发送的消息包括座席和警告消息。<br>
 * 通过向特定座席或所有座席的服务注册事件来完成座席事件的订阅。<br>
 * 不能订阅警告事件，所有系统警告都将被使用。

 * @author wangjunhua
 * @since 1.0
 *
 */
public interface EventService {

	/**
	 * 请求下一个座席事件。此方法将返回座席事件，或者如果在经过超时期限后仍没有事件，将返回null。<br>
	 *
	 * 在超时期限内，如果没有事件，此方法将阻塞。
	 * @param registrationId  事件服务注册的订阅代号
	 * @return 事件
	 * @throws IOException 网络异常
	 *
	 * @see AgentEvent
	 */
	AgentEvent getNextAgentEvent(int registrationId) throws IOException;

	/**
	 * 注册特定座席的事件。此方法将返回唯一的订阅代号。
	 *
	 * @param agentName 坐席名称
	 * @return 注册事件编号
	 * @throws IOException 网络异常
	 */
	int registerAgentEventSubscriber(String agentName) throws IOException;

	/**
	 * 删除指定座席的事件订阅。
	 * @param registrationId 事件服务注册的订阅代号
	 * @throws IOException 网络异常
	 */
	void unregisterAgentEventSubscriber(int registrationId) throws IOException;

	/**
	 * 注册所有座席的事件。此方法将返回唯一的订阅代号。
	 *
	 * @return 订阅代号
	 * @throws IOException 网络异常
	 */
	int registerAllAgentEventSubscriber() throws IOException;

	/**
	 * 此方法与 getNextAgentEvent 方法非常相似，只是它通过确认先前接收到的事件来保证消息的发送。<br>
	 *
	 * 如果未确认某事件，则应在下次调用该方法时再次返回该事件。<br>
	 *
	 * @param registrationId 注册的订阅代号
	 * @param sequenceNumber 事件编号
	 * @return
	 * @throws IOException 网络异常
	 *
	 * @see AgentEvent
	 */
	AgentEvent getNextAgentEventAck(int registrationId, long sequenceNumber) throws IOException;
}
