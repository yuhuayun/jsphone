package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 对象包含描述座席配置的属性
 * @author Believe
 *
 */
@Setter
@Getter
public class UDAgentInfo extends UDBase{

	/**
	 * 标识座席会话的座席索引
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 座席登录名称/ 代号
	 * 必须为具有 1 到 25 个字符的非空字符串
	 */
	private String agentLoginName;

	/**
	 * 座席邮箱地址
	 * 必须为带有有效电子邮件地址的非空字符串
	 */
	private String agentMbox;

	/**
	 * 未使用
	 */
	private int agentRouteAccessID;

	/**
	 * 定义电话连接
	 * 必须为 0、1 （窄带）、2 （宽带）或 3 (VOIP)
	 */
	private int agentType;

	/**
	 * 未使用
	 */
	private int agentVCI;

	/**
	 * 未使用
	 */
	private int agentVPI;

	/**
	 * 未使用
	 */
	private int agentWorkgroupID;

	/**
	 * 未使用
	 */
	private int codec;

	/**
	 * 未使用
	 */
	private String errorMessage;

	/**
	 * 未使用
	 */
	private String firstName;

	/**
	 * 座席机器的 IP 地址
	 * 必须为以点分十进制表示的有效 IPv4 IP 地址
	 */
	private String ipAddress;

	/**
	 * 未使用
	 */
	private String lastName;

	/**
	 * 未使用
	 */
	private String loginType;

	/**
	 * 未使用
	 */
	private int ntSwitchID;

	/**
	 * 未使用
	 */
	private String passcode;

	/**
	 * 未使用
	 */
	private String password;

	/**
	 * 未使用
	 */
	private int payloadLength;

	/**
	 * 未使用
	 */
	private int referenceID;

	/**
	 * 未使用
	 */
	private int rtpPort;

	/**
	 * 未使用
	 */
	private int serverType;

	/**
	 * 数字工作站代号
	 * 必须为非空数字字符串（例如 54321，但不能是 T5858），并且按推测应为有效的工作站代号
	 */
	private String stationId;

	/**
	 * 未使用
	 */
	private String ATMAddress;

	public String getHref(Integer index){
		return "<adata href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuffer agentInfoXML = new StringBuffer();
			agentInfoXML.append("<multiRef id=\"id").append(index).append("\" >\n");
			agentInfoXML.append("<ATMAddress>").append(trim(this.ATMAddress)).append("</ATMAddress>\n");
			agentInfoXML.append("<agentIndex>").append(this.agentIndex).append("</agentIndex>\n");
			agentInfoXML.append("<agentLoginName>").append(trim(this.agentLoginName)).append("</agentLoginName>\n");
			agentInfoXML.append("<agentMbox>").append(trim(this.agentMbox)).append("</agentMbox>\n");
			agentInfoXML.append("<agentRouteAccessID>").append(this.agentRouteAccessID).append("</agentRouteAccessID>\n");
			agentInfoXML.append("<agentType>").append(this.agentType).append("</agentType>\n");
			agentInfoXML.append("<agentVCI>").append(this.agentVCI).append("</agentVCI>\n");
			agentInfoXML.append("<agentVPI>").append(this.agentVPI).append("</agentVPI>\n");
			agentInfoXML.append("<agentWorkgroupID>").append(this.agentWorkgroupID).append("</agentWorkgroupID>\n");
			agentInfoXML.append("<codec>").append(this.codec).append("</codec>\n");
			agentInfoXML.append("<errorMessage>").append(trim(this.errorMessage)).append("</errorMessage>\n");
			agentInfoXML.append("<firstName>").append(trim(this.firstName)).append("</firstName>\n");
			agentInfoXML.append("<ipAddress>").append(trim(this.ipAddress)).append("</ipAddress>\n");
			agentInfoXML.append("<lastName>").append(trim(this.lastName)).append("</lastName>\n");
			agentInfoXML.append("<loginType>").append(trim(this.loginType)).append("</loginType>\n");
			agentInfoXML.append("<ntSwitchID>").append(this.ntSwitchID).append("</ntSwitchID>\n");
			agentInfoXML.append("<passcode>").append(trim(this.passcode)).append("</passcode>\n");
			agentInfoXML.append("<password>").append(trim(this.password)).append("</password>\n");
			agentInfoXML.append("<payloadLength>").append(this.payloadLength).append("</payloadLength>\n");
			agentInfoXML.append("<referenceID>").append(this.referenceID).append("</referenceID>\n");
			agentInfoXML.append("<rtpPort>").append(this.rtpPort).append("</rtpPort>\n");
			agentInfoXML.append("<serverType>").append(this.serverType).append("</serverType>\n");
			agentInfoXML.append("<stationId>").append(trim(this.stationId)).append("</stationId>\n");
		agentInfoXML.append("</multiRef>\n");
		return agentInfoXML.toString();
	}

}
