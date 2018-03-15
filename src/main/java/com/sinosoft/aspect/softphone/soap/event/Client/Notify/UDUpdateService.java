package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyCallDataDefFields;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyDispositions;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyService;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyServiceSettings;

import lombok.Data;


@Data
public class UDUpdateService implements Serializable {

	private int agentIndex;
	private UDNotifyCallDataDefFields callDataDefFields[];
	private UDNotifyDispositions dispositions[];
	private int serviceID;
	private UDNotifyService serviceInfo;
	private UDNotifyServiceSettings serviceSettings;

}
