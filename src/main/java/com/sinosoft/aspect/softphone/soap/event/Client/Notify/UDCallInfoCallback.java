package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallInfoCallback implements Serializable {

	private int callBackFlag;
	private String callbackMemo;
	private int dialModeCode;
}
