package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;


import lombok.Data;

@Data
public class UDNewService implements Serializable {

	private int agentIndex;  //坐席登录在系统中的索引
	private int serviceID;   //坐席所在的服务ID
	private UDNotifyCallDataDef callDataDef;   //通知调用数据
	private UDNotifyCallDataDefFields callDataDefFields[];  //通知调用字段
	private UDNotifyDispositionPlan dispositionPlan;   //通知配置代码
	private UDNotifyDispositions dispositions[];       //通知配置设置
	private UDNotifyService serviceInfo;               //服务信息
	private UDNotifyServiceSettings serviceSettings;   //服务配置信息

}
