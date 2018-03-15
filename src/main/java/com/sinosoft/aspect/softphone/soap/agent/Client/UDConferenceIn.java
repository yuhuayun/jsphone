package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 此对象包含用于建立会议呼叫所需的信息
 *
 * @author Believe
 *
 */
@Setter
@Getter
public class UDConferenceIn {

	/**
	 * 标识座席会话的座席索引 <br>
	 *
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 由系统指定的呼叫代号<br>
	 *
	 * 必须大于或等于 0
	 */
	private int callID;

	/**
	 * 由系统指定的咨询呼叫代号<br>
	 *
	 * 必须大于或等于 0
	 */
	private int consultCallID;

	public String getHref(Integer index){
		StringBuffer href = new StringBuffer();
		href.append("<conf href=\"#id").append(index).append("\" />\n");
		return href.toString();
	}

	public String toString(Integer index){
		StringBuffer conferenceinXML = new StringBuffer();
		conferenceinXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		conferenceinXML.append("<agentIndex>").append(this.agentIndex).append("</agentIndex>\n");
		conferenceinXML.append("<callID>").append(this.callID).append("</callID>\n");
		conferenceinXML.append("<consultCallID>").append(this.consultCallID).append("</consultCallID>\n");
		conferenceinXML.append("</multiRef>\n");

		return conferenceinXML.toString();
	}

}
