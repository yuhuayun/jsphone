package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;


import lombok.Data;

@Data
public class UDCallInfo implements Serializable {

	private UDCallInfoAod aodData;
	private UDCallInfoCallback callbackData;
	private UDCallInfoEmail emailData;
	private UDCallInfoOther otherData;
	private UDCallInfoScreen screenData;
	private UDCallInfoUserDefined userDefinedData;

}
