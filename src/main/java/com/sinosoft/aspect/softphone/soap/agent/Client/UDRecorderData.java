package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

/**
 * 此对象包含通知 CenterCord 执行与录音相关的命令所需的信息。
 *
 * @author Believe
 *
 */
@Setter
@Getter
public class UDRecorderData extends UDBase{

	private int VCI;
	private int VPI;

	/**
	 * 座席代号<br>
	 *
	 * 必须为非空字符串
	 */
	private String agentId;

	/**
	 *
	 * 工作站号码<br>
	 * 必须为非空字符串
	 */
	private String agentStation;

	/**
	 * 音频标志
	 * 必须大于或等于 0
	 */
	private int audio_f;
	private int bitsPerSample;
	private int callID;
	private String custName;
	private int dualRec_f;
	private String fileName;
	private int fileTypeId;
	private String key1;
	private String key2;
	private String key3;
	private String lastUpdateDt;
	private int monitorClassId;
	private String phone1;
	private int recStoreId;
	private int recordFormatId;
	private int recordRate;
	private String recorderId;
	private String recorderStation;
	private int recordingIndex;
	private int recordingTypeId;
	private int requestType;
	private int samplingRate;
	private int seqNumber;
	private int serviceId;
	private String status;
	private int video_f;


	public String getHref(Integer index){
		return "<data href=\"#id" + index + "\" />\n";
	}

	public String toString(Integer index){
		StringBuilder recorderdataXML = new StringBuilder();
		recorderdataXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		recorderdataXML.append("<VCI>").append(this.VCI).append("</VCI>\n");
		recorderdataXML.append("<VPI>").append(this.VPI).append("</VPI>\n");
		recorderdataXML.append("<agentId>").append(trim(this.agentId)).append("</agentId>\n");
		recorderdataXML.append("<agentStation>").append(trim(this.agentStation)).append("</agentStation>\n");
		recorderdataXML.append("<audio_f>").append(this.audio_f).append("</audio_f>\n");
		recorderdataXML.append("<bitsPerSample>").append(this.bitsPerSample).append("</bitsPerSample>\n");
		recorderdataXML.append("<callID>").append(this.callID).append("</callID>\n");
		recorderdataXML.append("<custName>").append(trim(this.custName)).append("</custName>\n");
		recorderdataXML.append("<dualRec_f>").append(this.dualRec_f).append("</dualRec_f>\n");
		recorderdataXML.append("<fileName>").append(trim(this.fileName)).append("</fileName>\n");
		recorderdataXML.append("<fileTypeId>").append(this.fileTypeId).append("</fileTypeId>\n");
		recorderdataXML.append("<key1>").append(trim(this.key1)).append("</key1>\n");
		recorderdataXML.append("<key2>").append(trim(this.key2)).append("</key2>\n");
		recorderdataXML.append("<key3>").append(trim(this.key3)).append("</key3>\n");
		recorderdataXML.append("<lastUpdateDt>").append(trim(this.lastUpdateDt)).append("</lastUpdateDt>\n");
		recorderdataXML.append("<monitorClassId>").append(this.monitorClassId).append("</monitorClassId>\n");
		recorderdataXML.append("<phone1>").append(trim(this.phone1)).append("</phone1>\n");
		recorderdataXML.append("<recStoreId>").append(this.recStoreId).append("</recStoreId>\n");
		recorderdataXML.append("<recordFormatId>").append(this.recordFormatId).append("</recordFormatId>\n");
		recorderdataXML.append("<recordRate>").append(this.recordRate).append("</recordRate>\n");
		recorderdataXML.append("<recorderId>").append(trim(this.recorderId)).append("</recorderId>\n");
		recorderdataXML.append("<recorderStation>").append(trim(this.recorderStation)).append("</recorderStation>\n");
		recorderdataXML.append("<recordingIndex>").append(this.recordingIndex).append("</recordingIndex>\n");
		recorderdataXML.append("<recordingTypeId>").append(this.recordingTypeId).append("</recordingTypeId>\n");
		recorderdataXML.append("<requestType>").append(this.requestType).append("</requestType>\n");
		recorderdataXML.append("<samplingRate>").append(this.samplingRate).append("</samplingRate>\n");
		recorderdataXML.append("<seqNumber>").append(this.seqNumber).append("</seqNumber>\n");
		recorderdataXML.append("<serviceId>").append(this.serviceId).append("</serviceId>\n");
		recorderdataXML.append("<status>").append(trim(this.status)).append("</status>\n");
		recorderdataXML.append("<video_f>").append(this.video_f).append("</video_f>\n");
		recorderdataXML.append("</multiRef>\n");

		return recorderdataXML.toString();

	}

}
