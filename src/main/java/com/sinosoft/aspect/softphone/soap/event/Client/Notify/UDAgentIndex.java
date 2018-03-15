package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDAgentIndex implements Serializable {

	/**
	 * 标识座席会话的座席索引
	 * 必须大于或等于 0
	 */
	private int agentIndex;

}
