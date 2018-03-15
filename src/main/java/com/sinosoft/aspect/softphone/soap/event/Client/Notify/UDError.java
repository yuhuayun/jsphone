package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDError implements Serializable {

	private int agentIndex;
	private int callID;
	private int currentLicenseCount;
	private int eventCode;
	private int failCode;
	private String failReason;
	private int licensedCount;
	private String localizedFailReason;
	private int mbox;
	//服务ID
	private int serviceId;

}
