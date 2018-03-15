package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

/**
 * 座席已发送命令以请求从系统中注销。座席在发送 Login 命令后，可在任何时候发送此命令。<br>
 * 如果他的状态不是进行注销的正确状态（例如，座席正在呼叫中处于"活动"状态），则请求将被搁置，并且 CenterCord 将向该座席发送事件 LogoutPenging。<br>
 * 座席终止当前呼叫后，CenterCord 就会注销该座席。<br>
 * 如果座席处于正确的状态，则将接收到 logout 事件。
 *
 * @author wangjunhua
 * @since 1.0.0
 */
@Data
public class UDLogout implements Serializable {

	/**
	 * 标识座席会话的座席索引
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 状态描述
	 */
	private int statusReason;

}
