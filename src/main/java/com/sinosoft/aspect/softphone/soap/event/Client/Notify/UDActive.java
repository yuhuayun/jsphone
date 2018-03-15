package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDActive implements Serializable{

	private String IPAddress;
	private int agentIndex;
	private int callID;
	private int mediaType;
	private int portNumber;
	private int statusReason;

}
