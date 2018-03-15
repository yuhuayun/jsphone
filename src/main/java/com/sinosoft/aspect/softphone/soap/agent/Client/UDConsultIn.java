package com.sinosoft.aspect.softphone.soap.agent.Client;

import java.lang.reflect.Method;

import lombok.Getter;
import lombok.Setter;

/**
 * 此对象包含与号码或座席建立咨询呼叫所需的信息
 *
 * @author Believe
 *
 */
@Setter
@Getter
public class UDConsultIn extends UDBase{

	/**
	 * 由系统指定的呼叫代号<br>
	 *
	 * 必须大于或等于 0
	 */
	private int callID;

	/**
	 * 指定咨询的类型 <br>
	 *
	 * 必须为 1 （内部咨询）或 2 （外部咨询）
	 */
	private int consultType;

	/**
	 * 外部路由代号 <br>
	 * 必须大于或等于 0
	 */
	private int externalRouteID;

	/**
	 * 要咨询的电话号码 <br>
	 *
	 * 必须为具有多于 1 个字符的非空字符串，并且按推测应为有效的电话号码。
	 * 如果 consultType 为 2 （外部咨询），则此字段为必需字段。
	 *
	 */
	private String phoneNumber;

	/**
	 * 要咨询的服务代号 <br>
	 *
	 * 必须大于或等于 0
	 */
	private int serviceID;

	/**
	 * 是否转接
	 * true (1) 或 false (0)
	 */
	private int blindXferFlag;

	/**
	 * 客户的随录数据
	 */
	private UDCallInfoUserDefinedIn userDefinedData;

	/**
	 * 要咨询的用户 <br>
	 *
	 * 必须为具有 1 到 25 个字符的非空字符串
	 */
	private String userID;


	public String getHref(Integer index){
		return "<cons href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){

		StringBuffer consultinXML = new StringBuffer();
		consultinXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		consultinXML.append("<blindXferFlag>").append(this.blindXferFlag).append("</blindXferFlag>\n");
		consultinXML.append("<callID>").append(this.callID).append("</callID>\n");
		consultinXML.append("<consultType>").append(this.consultType).append("</consultType>\n");
		consultinXML.append("<externalRouteID>").append(this.externalRouteID).append("</externalRouteID>\n");
		consultinXML.append("<phoneNumber>").append(trim(this.phoneNumber)).append("</phoneNumber>\n");
		appendUserDefinedHref(consultinXML,index);
		consultinXML.append("<serviceID>").append(this.serviceID).append("</serviceID>\n");
		consultinXML.append("<userID>").append(trim(this.userID)).append("</userID>\n");
		consultinXML.append("</multiRef>\n");

		if(userDefinedData != null)
			appendUserDefinedData(consultinXML,++index);

		return consultinXML.toString();
	}

	private void appendUserDefinedHref(StringBuffer consultinXML,int index){
		if(userDefinedData == null){
			consultinXML.append("<userDefinedData/>\n");
		}else{
			consultinXML.append("<userDefinedData href=\"#id").append(++index).append("\" />\n");
		}
	}

	private void appendUserDefinedData(StringBuffer consultinXML,int index){
		try {
			if(userDefinedData != null){
				Class type = userDefinedData.getClass();
				String name = type.getName();
				Method method = type.getMethod("toString", Integer.class);
				consultinXML.append(method.invoke(userDefinedData, index));
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
