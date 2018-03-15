package com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent;

import java.io.Serializable;

import lombok.Data;

@Data
public class StatusReasons implements Serializable {

	private int agentstatusid;
	private String description;
	private int reasonid;

}
