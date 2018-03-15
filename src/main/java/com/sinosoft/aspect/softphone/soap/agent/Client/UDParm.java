package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Data;

/**
 * 包含要对其应用下面的相关方法的呼叫代号。
 *
 * @author wangjunhua
 * @since 1.0
 *
 */
@Data
public class UDParm {

	/**
	 * 由系统指定的呼叫代号 <br>
	 * 必须大于或等于 0
	 */
	private int callId;

	public UDParm(){}

	public UDParm(int callId){
		this.callId = callId;
	}

	public String getHref(Integer index){
		StringBuffer href = new StringBuffer();
		href.append("<parm href=\"#id").append(index).append("\" />\n");
		return href.toString();
	}

	public String toString(Integer index){
		StringBuffer parmXML = new StringBuffer();
		parmXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		parmXML.append("<callId>").append(this.callId).append("</callId>\n");
		parmXML.append("</multiRef>\n");

		return parmXML.toString();
	}
}
