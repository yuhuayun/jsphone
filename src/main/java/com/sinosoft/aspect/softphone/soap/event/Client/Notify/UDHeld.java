package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDHeld implements Serializable {

	private int agentIndex;
	private int callID;
	private int statusReason;

}
