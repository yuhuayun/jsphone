package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDNotifyDispositions implements Serializable {

	private int callbackf;
	private String code;
	private String description;
	private int dispid;
	private int dispplanid;
	private int exclusionf;
	private int salesf;

}
