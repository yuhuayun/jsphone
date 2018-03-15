package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDLogin implements Serializable {

	/**
	 * 标识座席会话的座席索引
	 * 必须大于或等于 0
	 */
	private int agentIndex;
	private boolean enableUCF;
	private int ntSwitchId;
	private int statusReason;
	private int systemFeature;
}
