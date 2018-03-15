package com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent;

import java.io.Serializable;

import lombok.Data;

@Data
public class Dispositions implements Serializable {

	private int callbackf;
	private String code;
	private String description;
	private int dispid;
	private int dispplanid;
	private int exclusionf;
	private int salesf;

}
