package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

/**
 * 振铃对象
 * @author Believe
 *
 */
@Data
public class UDDialing implements Serializable {

	/**
	 * 座席索引
	 */
	private int agentIndex;

	/**
	 * 原始呼叫索引
	 */
	private int callID;

	/**
	 * 指定呼叫的类型
	 * @see MediaType
	 */
	private int statusReason;

}
