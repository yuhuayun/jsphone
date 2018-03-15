package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 该对象包含座席注销或转为 "未就绪" 状态的原因。
 * @author wangjunhua
 * @since 1.0.0
 *
 */
@Setter
@Getter
public class UDReason extends UDBase{

	/**
	 * 文本格式原因<br>
	 *
	 * 必须为具有多于 1 个字符的非空字符串
	 */
	private String reasonDescription;

	/**
	 * 数字格式原因（对于紧急注销为 -99）<br>
	 * 必须大于 0，紧急注销除外
	 *
	 */
	private int reasonId;

	/**
	 * 未使用
	 */
	private boolean toParkState;

	public String getHref(Integer index){
		return "<reason href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuffer reasonXML = new StringBuffer();
		reasonXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		reasonXML.append("<reasonDescription>").append(trim(this.reasonDescription)).append("</reasonDescription>\n");
		reasonXML.append("<reasonId>").append(this.reasonId).append("</reasonId>\n");
		reasonXML.append("<toParkState>").append(this.toParkState).append("</toParkState>\n");
		reasonXML.append("</multiRef>\n");

		return reasonXML.toString();
	}

}
