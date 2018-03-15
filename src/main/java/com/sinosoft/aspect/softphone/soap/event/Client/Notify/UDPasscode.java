package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDPasscode implements Serializable {

	private int agentIndex;
	private String code;

}
