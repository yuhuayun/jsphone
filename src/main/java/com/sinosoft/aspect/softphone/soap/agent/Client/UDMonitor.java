package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 此对象包含用于通知 CC 应如何进行监控所需的信息。
 * @author Believe
 *
 */
@Setter
@Getter
public class UDMonitor extends UDBase{

	/**
	 * 座席登录名称<br>
	 *
	 * 必须为具有多于 1 个字符的非空字符串
	 */
	private String agentID;

	/**
	 * 标识座席会话的座席索引<br>
	 *
	 * 必须大于或等于 0
	 */
	private int agentIndex;

	/**
	 * 工作站号码<br>
	 *
	 * 必须为具有多于 1 个字符的非空字符串
	 */
	private String agentStationID;

	/**
	 * 监控代号<br>
	 *
	 * 必须大于 0
	 */
	private int monitoringID;

	/**
	 * 监控工作站代号 <br>
	 *
	 * 必须为具有多于 1 个字符的非空字符串
	 */
	private String monitoringStationID;

	/**
	 * 请求类型  <br>
	 *
	 * 必须大于 0
	 *
	 * @see MonitorType
	 */
	private int requestType;

	public String getHref(Integer index){
		StringBuffer href = new StringBuffer();
		href.append("<minfo href=\"#id").append(index).append("\" />\n");
		return href.toString();
	}

	public String toString(Integer index){
		StringBuffer monitorXML = new StringBuffer();
		monitorXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		monitorXML.append("<agentID>").append(trim(this.agentID)).append("</agentID>\n");
		monitorXML.append("<agentIndex>").append(this.agentIndex).append("</agentIndex>\n");
		monitorXML.append("<agentStationID>").append(trim(this.agentStationID)).append("</agentStationID>\n");
		monitorXML.append("<monitoringID>").append(this.monitoringID).append("</monitoringID>\n");
		monitorXML.append("<monitoringStationID>").append((this.monitoringStationID)).append("</monitoringStationID>\n");
		monitorXML.append("<requestType>").append(this.requestType).append("</requestType>\n");
		monitorXML.append("</multiRef>\n");

		return monitorXML.toString();

	}

}
