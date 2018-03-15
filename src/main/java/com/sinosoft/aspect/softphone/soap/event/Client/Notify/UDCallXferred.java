package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallXferred implements Serializable {

	/**
	 * 坐席索引
	 */
	private int agentIndex;

	/**
	 * 咨询呼叫索引
	 */
	private int consultCallID;

	/**
	 * 转接呼叫索引
	 */
	private int newCallID;

	/**
	 * 原始呼叫座席索引
	 */
	private int originalAgentIndex;

	/**
	 * 原始呼叫索引
	 */
	private int originalCallID;

	/**
	 * 坐席当前状态
	 */
	private int statusReason;

}
