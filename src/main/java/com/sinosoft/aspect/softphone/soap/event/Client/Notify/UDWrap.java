package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

/**
 * 后处理信息
 * @author Believe
 *
 */
@Data
public class UDWrap implements Serializable {

	/**
	 * 标识座席会话的座席索引
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 由系统指定的呼叫代号<br>
	 * 必须大于或等于 0
	 */
	private int callID;

	/**
	 * 系统返回的软电话状态
	 */
	private int statusReason;

}
