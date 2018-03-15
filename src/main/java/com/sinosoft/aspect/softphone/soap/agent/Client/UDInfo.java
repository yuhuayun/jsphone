package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 该类包含检索关于座席、服务、服务设置、配置代码和呼叫数据定义所需的信息。
 *
 * @author wangjunhua
 * @since 1.0.0
 *
 */
@Setter
@Getter
public class UDInfo extends UDBase{

	private int attachementId;
	private int callDataDefId;
	private int categoryArray[];
	private int classId;
	private String description;
	private int dispostionId;
	private String fromDate;
	private int id;
	private int indicator;
	private String ipAddress1;
	private String ipAddress2;

	/**
	 * 定义信息请求的类型
	 * 必须为在范围 100-200 内的整数并且必须为有效的列表类型
	 * @see  ListType
	 */
	private int listType;
	private String localeUser;
	private int mailId;
	private int mailQId;
	private int messageId;
	private int minMsgId;
	private int recordCount;
	private String recordingComment;
	private int rowCount;
	private String searchField;
	private String searchText;
	private String searchType;
	private int seqNum;
	private int serviceId;
	private int serviceType;
	private int statusId;
	private int statusReasonsType;
	private int statusType;
	private String toDate;
	private int urlId;
	private int userType;
	private int userTypeMask;

	public String getHref(Integer index){
		StringBuffer href = new StringBuffer();
		href.append("<info href=\"#id").append(index).append("\" />\n");
		return href.toString();
	}

	public String toString(Integer index){
		StringBuffer infoXML = new StringBuffer();
		infoXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		infoXML.append("<attachementId>").append(this.attachementId).append("</attachementId>\n");
		infoXML.append("<callDataDefId>").append(this.callDataDefId).append("</callDataDefId>\n");
//		if (getCategoryArray() != null) {
//			infoXML.append("<categoryArray>");
//			infoXML.append(Arrays.toString(categoryArray));
//			infoXML.append("</categoryArray>\n");
//		}

		infoXML.append("<classId>").append(this.classId).append("</classId>\n");
		infoXML.append("<description>").append(trim(this.description)).append("</description>\n");
		infoXML.append("<dispostionId>").append(this.dispostionId).append("</dispostionId>\n");
		infoXML.append("<fromDate>").append(trim(this.fromDate)).append("</fromDate>\n");
		infoXML.append("<id>").append(this.id).append("</id>\n");
		infoXML.append("<indicator>").append(this.indicator).append("</indicator>\n");
		infoXML.append("<ipAddress1>").append(trim(this.ipAddress1)).append("</ipAddress1>\n");
		infoXML.append("<ipAddress2>").append(trim(this.ipAddress2)).append("</ipAddress2>\n");
		infoXML.append("<listType>").append(this.listType).append("</listType>\n");
		infoXML.append("<localeUser>").append(trim(this.localeUser)).append("</localeUser>\n");
		infoXML.append("<mailId>").append(this.mailId).append("</mailId>\n");
		infoXML.append("<mailQId>").append(this.mailQId).append("</mailQId>\n");
		infoXML.append("<messageId>").append(this.messageId).append("</messageId>\n");
		infoXML.append("<minMsgId>").append(this.minMsgId).append("</minMsgId>\n");
		infoXML.append("<recordCount>").append(this.recordCount).append("</recordCount>\n");
		infoXML.append("<recordingComment>").append(trim(this.recordingComment)).append("</recordingComment>\n");
		infoXML.append("<rowCount>").append(this.rowCount).append("</rowCount>\n");
		infoXML.append("<searchField>").append(trim(this.searchField)).append("</searchField>\n");
	    infoXML.append("<searchText>").append(trim(this.searchText)).append("</searchText>\n");
		infoXML.append("<searchType>").append(trim(this.searchType)).append("</searchType>\n");
		infoXML.append("<seqNum>").append(this.seqNum).append("</seqNum>\n");
		infoXML.append("<serviceId>").append(this.serviceId).append("</serviceId>\n");
		infoXML.append("<serviceType>").append(this.serviceType).append("</serviceType>\n");
		infoXML.append("<statusId>").append(this.statusId).append("</statusId>\n");
		infoXML.append("<statusReasonsType>").append(this.statusReasonsType).append("</statusReasonsType>\n");
		infoXML.append("<statusType>").append(this.statusType).append("</statusType>\n");
		infoXML.append("<toDate>").append(trim(this.toDate)).append("</toDate>\n");
		infoXML.append("<urlId>").append(this.urlId).append("</urlId>\n");
		infoXML.append("<userType>").append(this.userType).append("</userType>\n");
		infoXML.append("<userTypeMask>").append(this.userTypeMask).append("</userTypeMask>\n");
		infoXML.append("</multiRef>\n");

		return infoXML.toString();

	}

}
