package com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent;

import java.io.Serializable;

import lombok.Data;

@Data
public class AgentXfer implements Serializable {

	private int agentindex;
	private String emailAddress;
	private int serviceid;
	private String userfname;
	private String userid;
	private String userlname;

}
