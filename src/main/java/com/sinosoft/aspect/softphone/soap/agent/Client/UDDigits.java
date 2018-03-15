package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 此对象包含通知系统将数字传递给电话线所需的信息。
 *
 * @author wangjunhua
 * @since 1.0.0
 *
 */
@Setter
@Getter
public class UDDigits extends UDBase{

	/**
	 * 由系统指定的呼叫代号<br>
	 * 必须大于或等于 0
	 */
	private int callId;

	/**
	 * 要拨打的数字<br>
	 *
	 * 必须为具有多于 1 个字符的非空（可能是数字）字符串
	 */
	private String digits;


	public String getHref(Integer index){
		return "<digits href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuffer digitsXML = new StringBuffer();
		digitsXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		digitsXML.append("<callId>").append(this.callId).append("</callId>\n");
		digitsXML.append("<digits>").append(trim(this.digits)).append("</digits>\n");
		digitsXML.append("</multiRef>\n");

		return digitsXML.toString();
	}

}
