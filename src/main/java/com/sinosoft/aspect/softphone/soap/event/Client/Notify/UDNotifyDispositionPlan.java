package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDNotifyDispositionPlan implements Serializable {

	private String desc;
	private int dispid;
	private int dispplanid;

}
