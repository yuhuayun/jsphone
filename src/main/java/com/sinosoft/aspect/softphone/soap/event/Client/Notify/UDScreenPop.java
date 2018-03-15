package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfo;

import lombok.Data;

@Data
public class UDScreenPop implements Serializable {

	private int agentIndex;
	private int callID;
	private UDCallInfo callInfo;
	private String originalServiceName;
	private int pbxaodserviceid;
	private boolean playAudioAlert;
	private int preemptive;
	private boolean requiredRejectReason;
	private boolean requiredResponse;
	private String serviceName;
	private int statusReason;
	private int switchCallID;
	private int timeoutForAcceptCall;
	private int timeoutForAudioAlert;

}
