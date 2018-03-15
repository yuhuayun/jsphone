package com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent;

import java.io.Serializable;

import lombok.Data;

@Data
public class Service implements Serializable {

	private int auditableAlertForAcceptCall;
	private int dispplanid;
	private int outgoingmask;
	private String servicec;
	private int serviceid;
	private int servicetypeid;

}
