package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 包含应答呼叫（包括分类为 "接受呼叫" 的呼叫接受或呼叫拒绝）所必需的信息
 * @author Believe
 *
 */
@Setter
@Getter
public class UDAnswerCall {

	/**
	 * 标识座席会话的座席索引<br>
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 由系统指定的呼叫代号<br>
	 * 必须大于或等于 0
	 */
	private int callID;

	/**
	 * 拒绝呼叫的原因<br>
	 * 必须大于或等于 0，并且按推测应为有效的拒绝原因
	 */
	private int rejectReason;

	/**
	 * 指定应接受还是拒绝呼叫<br>
	 *
	 * 必须为 0 （接受）或 1 （拒绝）
	 */
	private int result;

	public String getHref(Integer index){
		return "<answer href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuffer answercallXML = new StringBuffer();
		answercallXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		answercallXML.append("<agentIndex>").append(this.agentIndex).append("</agentIndex>\n");
		answercallXML.append("<callID>").append(this.callID).append("</callID>\n");
		answercallXML.append("<rejectReason>").append(this.rejectReason).append("</rejectReason>\n");
		answercallXML.append("<result>").append(this.result).append("</result>\n");
		answercallXML.append("</multiRef>\n");

		return answercallXML.toString();
	}

}