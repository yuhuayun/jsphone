package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

/**
 * 弹屏事件中呼叫信息
 * @author Believe
 *
 */
@Data
public class UDCallInfoScreen implements Serializable {

	/**
	 * 外部呼入主叫号码
	 */
	private String ani;

	/**
	 * 内部呼入主叫方号码
	 */
	private String callerID;

	/**
	 * 外部呼叫被叫号码
	 */
	private String dnis;

	/**
	 *
	 */
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private boolean isMasked;

}
