package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDNotifyService implements Serializable {

	private int dispplanid;
	private int outgoingmask;
	private String servicec;
	private int serviceid;
	private int servicetypeid;

	/**
	 *
	 */
	private int timezoneid;

}
