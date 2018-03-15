package com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

	private int agentindex;
	private int allowAgentToAgentIM;
	private int allowAgentToSupervisorIM;
	private int allowAskAnExpertInPark;
	private int allowInboundIM;
	private int allowNotReadyFlag;
	private int auditableAlertForAcceptCall;
	private int autoAccept;
	private int beginIdleTimeoutOnWrap;
	private String emailaddress;
	private int emailprotocolid;
	private int enableucf;
	private String ipaddress;
	private String ldapuserid;
	private int maxchatcalls;
	private int maxtotalcalls;
	private int notreadyIfReject;
	private int onDemandFlag;
	private int outgoingmask;
	private int park;
	private int parkdelay;
	private String password;
	private int requireRejectReason;
	private int routeaccessid;
	private String station;
	private String status;
	private int taskOfferedTimeout;
	private String userfname;
	private String userfullname;
	private String userid;
	private String userlname;
	private int usertypemask;
	private int warmtransfer;
	private int workgroupid;
	private int defaultASMServiceId;
	private int maxWrapTime;
    private int maxemailcalls;
    private int maximcalls;
    private int maxworkflowcalls;
    private int minWrapTime;
    private int multiLine;
    private int pgAllowed;
    private int transitionToParkForAspectSocial;
    private int wrapExceedAction;

}
