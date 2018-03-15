package com.sinosoft.aspect.softphone.soap.agent.Client;


import lombok.Getter;
import lombok.Setter;

/**
 * UDAgent 对象及其相关联的属性唯一标识座席。
 * 许多 Web 服务方法要求使用此对象。
 * @author Believe
 *
 */
@Setter
@Getter
public class UDAgent extends UDBase{

	/**
	 * 标识座席会话的座席索引
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 座席登录名称/代号
	 * 必须为具有 1 到 25 个字符的非空字符串
	 */
	private String agentLoginName;

	/**
	 * 已定义的客户端角色
	 * 必须为 1 （座席）、2 （主管）或3 （带有电话的主管）
	 */
	private int clientRole = 0;

	/**
	 * 定义座席在注册时的初始状态 [0 - 空闲或 5 - 未就绪] 的标志。
	 * 座席处于"空闲"状态时已准备好进行呼叫，可以立即接收呼叫。
	 * 座席处于"未就绪"状态时，在进入"就绪"状态前，不应接收呼叫，这对于初始化客户端很有用。
	 * 必须处于 0（空闲）或 5 （未就绪）状态
	 */
	private int initState = -1;

	/**
	 * LDAP 用户代号
	 * 必须为具有 1 到 25 个字符的非空字符串
	 */
	private String LDAPUserId;

	/**
	 * 服务器类型必须为 1
	 */
	private int serverType;

	public String getHref(Integer index){
		return "<agent href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuffer udagentXML = new StringBuffer();
		udagentXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		udagentXML.append("<LDAPUserId>").append(trim(this.LDAPUserId)).append("</LDAPUserId>\n");
		udagentXML.append("<agentIndex>").append(this.agentIndex).append("</agentIndex>\n");
		udagentXML.append("<agentLoginName>").append(trim(this.agentLoginName)).append("</agentLoginName>\n");

		if(this.clientRole > 0){
			udagentXML.append("<clientRole>").append(this.clientRole).append("</clientRole>\n");
		}

		if(this.initState != -1){
			udagentXML.append("<initState>").append(this.initState).append("</initState>\n");
		}

		udagentXML.append("<serverType>").append(this.serverType).append("</serverType>\n");
		udagentXML.append("</multiRef>\n");

		return udagentXML.toString();
	}

}
