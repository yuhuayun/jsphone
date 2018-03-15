package com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent;

import java.io.Serializable;

import lombok.Data;

@Data
public class ServiceXfer implements Serializable {

	private String emailAddress;
	private String servicec;
	private int serviceid;
	private int servicetypeid;
	private String userid;
	private int xferlisttype;

}
