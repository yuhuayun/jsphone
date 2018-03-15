package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDLoggingIn implements Serializable {

	/**
	 * 坐席编号
	 */
	private int agentIndex;

	/**
	 * 软电话状态
	 */
	private int statusReason;

}
