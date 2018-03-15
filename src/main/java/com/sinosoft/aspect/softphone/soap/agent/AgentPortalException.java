package com.sinosoft.aspect.softphone.soap.agent;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Vector;

@Slf4j
public class AgentPortalException extends RuntimeException{

	private String message;
	protected Vector faultSubCode;
	protected String faultString;
	protected String faultActor;
	protected Vector faultDetails;
	protected String faultNode;
	protected ArrayList faultHeaders;
	
	public AgentPortalException(String message){
		super(message);
		this.message = message;
		setFaultString(message);
	}
	
	public AgentPortalException(Exception message){
	}
	
	public void setFaultString(String str) {
		if (str != null)
			faultString = str;
		else
			faultString = "";
	}

	public String getFaultString() {
		return faultString;
	}

	public void setFaultReason(String str) {
		setFaultString(str);
	}

	public String getFaultReason() {
		return getFaultString();
	}

	public void setFaultActor(String actor) {
		faultActor = actor;
	}

	public String getFaultActor() {
		return faultActor;
	}

	public String getFaultRole() {
		return getFaultActor();
	}

	public void setFaultRole(String role) {
		setFaultActor(role);
	}

	public String getFaultNode() {
		return faultNode;
	}

	public void setFaultNode(String node) {
		faultNode = node;
	}
}
