package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallInfoOther implements Serializable {

	private int callCategory;
	private int callID;
	private int callType;
	private int eventCode;
	private int originalServiceID;
	private int serviceID;
    private int timeZoneID;
}
