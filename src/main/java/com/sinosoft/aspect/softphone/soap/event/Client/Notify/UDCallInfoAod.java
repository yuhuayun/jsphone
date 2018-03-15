package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallInfoAod implements Serializable {

	private int callTableRecordNum;
	private int storeManagerId;
	private String tableName;
	private int targetManagerId;

}
