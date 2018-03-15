package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDNotReady implements Serializable {

	/**
	 * 坐席索引
	 */
	private int agentIndex;

	/**
	 * 坐席状态
	 */
	private int statusReason;

	/**
	 * 是否进入暂放状态
	 */
	private boolean toParkState;

}
