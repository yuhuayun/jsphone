package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDLoginConf implements Serializable {

	private int agentIndex;
	private String agentLoginName;
	private int agentType;
	private int ntSwitchId;
	private int referenceID;

}
