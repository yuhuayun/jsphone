package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallInfoEmail implements Serializable {

	private String forwardToMBox;
	private int mailQueId;
	private int messageId;

}
