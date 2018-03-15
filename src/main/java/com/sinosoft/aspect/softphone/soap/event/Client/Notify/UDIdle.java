package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDIdle implements Serializable {

	private int agentIndex;
	private int statusReason;

}
