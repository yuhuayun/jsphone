package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallInfoUserDefinedItem implements Serializable {

	private String key;
	private String value;

}
