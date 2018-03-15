package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyCallDataDef;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyCallDataDefFields;

import lombok.Data;

@Data
public class UDUpdateCallDataDef implements Serializable {

	private UDNotifyCallDataDef callDataDef;
	private UDNotifyCallDataDefFields callDataDefFields[];
	private int callDataDefId;

}
