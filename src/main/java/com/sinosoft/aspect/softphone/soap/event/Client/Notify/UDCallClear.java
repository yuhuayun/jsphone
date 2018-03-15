package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallClear implements Serializable {

	private int agentIndex;
	private int callID;

	/**
	 * 指定座席在此呼叫以后的状态
	 * AgWrap
	 * AgIdle
	 * AgActive
	 * AgHeld
	 *
	 */
	private int statusReason;
}
