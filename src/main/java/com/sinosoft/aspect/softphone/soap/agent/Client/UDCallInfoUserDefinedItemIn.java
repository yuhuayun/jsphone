package com.sinosoft.aspect.softphone.soap.agent.Client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UDCallInfoUserDefinedItemIn extends UDBase{

	private String key;
	private String value;

	public String toString(Integer index){
		StringBuffer callinfouserdefinediteminXML = new StringBuffer();
		callinfouserdefinediteminXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		callinfouserdefinediteminXML.append("<key>").append(trim(this.key)).append("</key>\n");
		callinfouserdefinediteminXML.append("<value>").append(trim(this.value)).append("</value>\n");
		callinfouserdefinediteminXML.append("</multiRef>\n");

		return callinfouserdefinediteminXML.toString();
	}

}
