package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDMonitorState implements Serializable {

	private int agentIndex;
	private String ipAddress;
	private int monitoringSeq;
	private int monitoringState;
	private int portNum;

}
