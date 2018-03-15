package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

/**
 * 坐席会议事件
 * @author Believe
 *
 */
@Data
public class UDConference implements Serializable {

	private int agentIndex;

	/**
	 * 会议的坐席索引
	 * 对于外部呼叫 = 0
	 */
	private int conferenceAgentIndex;

	/**
	 * 会议呼叫索引
	 */
	private int conferenceCallID;

	/**
	 * 咨询呼叫索引
	 */
	private int consultCallID;

	/**
	 * 原始呼叫索引
	 */
	private int originalCallID;

	/**
	 * 坐席当前状态 ： AgConference 会议状态
	 */
	private int statusReason;

}
