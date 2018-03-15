package com.sinosoft.aspect.softphone.soap.agent.Client;

import java.lang.reflect.Method;

import lombok.Getter;
import lombok.Setter;

/**
 * 此对象包含进行媒体传输所需的信息。
 * @author wangjunhua
 * @since 1.0.0
 *
 */
@Getter
@Setter
public class UDTransfer {

	/**
	 * 由系统指定的会议呼叫代号<br>
	 *
	 * 必须大于 0
	 */
	private int conferenceCallID;

	/**
	 * 由系统指定的当前呼叫代号<br>
	 * 必须大于 0
	 */
	private int originalCallID;

	/**
	 * 用于转接呼叫的服务代号<br>
	 * 必须大于 0
	 */
	private int serviceID;

	/**
	 * 第三方呼叫代号<br>
	 * 必须大于 0
	 */
	private int thirdParyCallID;

	/**
	 * 转接类型<br>
	 * 问询 (1) 或无通知 (2)<br>
	 * 必须大于或等于 0
	 */
	private int transferType;

	/**
	 * 呼叫信息
	 */
	private UDCallInfoUserDefinedIn callInfoUserDefined;


	public String getHref(Integer index){
		return "<trans href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuffer transferXML = new StringBuffer();
		transferXML.append("<multiRef id=\"id").append(index).append("\" >\n");

		appendUserDefinedHref(transferXML,index);

		transferXML.append("<conferenceCallID>").append(this.conferenceCallID).append("</conferenceCallID>\n");
		transferXML.append("<originalCallID>").append(this.originalCallID).append("</originalCallID>\n");
		transferXML.append("<serviceID>").append(this.serviceID).append("</serviceID>\n");
		transferXML.append("<thirdParyCallID>").append(this.thirdParyCallID).append("</thirdParyCallID>\n");
		transferXML.append("<transferType>").append(this.transferType).append("</transferType>\n");
		transferXML.append("</multiRef>\n");

		if(this.callInfoUserDefined != null){

			appendUDCallInfoUserDefinedItem(transferXML,++index);
		}

		return transferXML.toString();

	}

	private void appendUserDefinedHref(StringBuffer consultinXML,int index){
		if(this.callInfoUserDefined == null){
			consultinXML.append("<callInfoUserDefined/>");
		}else{
			consultinXML.append("<callInfoUserDefined href=\"#id").append(++index).append("\" />\n");
		}
	}

	private void appendUDCallInfoUserDefinedItem(StringBuffer consultinXML,int index){
		try {
			Class type = callInfoUserDefined.getClass();
			Method method = type.getMethod("toString", Integer.class);
			consultinXML.append(method.invoke(callInfoUserDefined, index));
		} catch (Exception e) {
//
		}
	}
}
