package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDNotifyCallDataDefFields implements Serializable {

	private int calldatadefid;
	private int fieldorder;
	private String fieldtype;
	private String label;
	private int mediatypeid;
	private String reservedword;

}
