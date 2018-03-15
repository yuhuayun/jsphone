package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 包含拨打呼叫所必需的信息
 * @author Believe
 *
 */
@Setter
@Getter
public class UDCall extends UDBase{

	/**
	 * 由系统指定的呼叫代号<br>
	 * 必须大于或等于 0
	 */
	private int callID;

	/**
	 * 外部路由代号<br>
	 * 必须大于或等于 0
	 */
	private int externalRouteID;

	/**
	 * 定义要发起的呼叫的类型<br>
	 * 必须为 1 （内部）、2 （外部）或 3 （服务）
	 */
	private int makeCallType;

	/**
	 * 如果 makeCallType 为 2，则它表示要拨打的号码 <br>
	 *
	 * 必须为具有多于 1 个字符的非空字符串，并且按推测应为有效的电话号码
	 *
	 */
	private String phoneNumber;

	/**
	 * 如果 makeCallType 为 2，则它表示将与呼叫相关联的服务代号。 <br>
	 * 如果 makeCallType 为 3，则它表示要呼叫的服务代号 <br>
	 * 必须大于或等于 0
	 */
	private int serviceID;

	/**
	 * 如果 makeCallType 为 1，则它表示要拨打的座席 <br>
	 *
	 * 必须为具有 1 到 25 个字符的非空字符串
	 *
	 */
	private String userID;

	/**
	 * 不建议继续使用
	 */
	private int wrapRequiredFlag;


	public String getHref(Integer index){
		return "<callinfo href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuffer callXML = new StringBuffer();
		callXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		callXML.append("<callID>").append(this.callID).append("</callID>\n");
		callXML.append("<externalRouteID>").append(this.externalRouteID).append("</externalRouteID>\n");
		callXML.append("<makeCallType>").append(this.makeCallType).append("</makeCallType>\n");
		callXML.append("<phoneNumber>").append(trim(this.phoneNumber)).append("</phoneNumber>\n");
		callXML.append("<serviceID>").append(this.serviceID).append("</serviceID>\n");
		callXML.append("<userID>").append(trim(this.userID)).append("</userID>\n");
		callXML.append("<wrapRequiredFlag>").append(this.wrapRequiredFlag).append("</wrapRequiredFlag>\n");
		callXML.append("</multiRef>\n");

		return callXML.toString();
	}

}
