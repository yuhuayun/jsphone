package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDNotifyCallDataDef implements Serializable {

	private int calldatadefid; //呼叫数据字段ID
	private String name;       //名称
	private int tabledefid;    //表字段

}
