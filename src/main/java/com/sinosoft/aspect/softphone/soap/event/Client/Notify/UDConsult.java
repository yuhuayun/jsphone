package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

/**
 * 咨询事件对象
 * @author Believe
 *
 */
@Data
public class UDConsult implements Serializable {

	/**
	 * 座席索引
	 */
	private int agentIndex;

	/**
	 * 咨询呼叫索引
	 */
	private int consultCallID;

	/**
	 * 被咨询座席索引
	 */
	private int consultedAgentIndex;

	/**
	 * 原始呼叫索引
	 */
	private int originalCallID;

	/**
	 * 坐席状态 ： AgConsulting
	 */
	private int statusReason;

}
