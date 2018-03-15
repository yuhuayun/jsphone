package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfo;

import lombok.Data;

@Data
public class UDPreview implements Serializable {

	private int callID;
	private UDCallInfo callInfo;
	private String originalServiceName;
	private int preemptive;
	private String serviceName;
	private int statusReason;

}
