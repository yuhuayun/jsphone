package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDCallInfoUserDefined implements Serializable {

	private UDCallInfoUserDefinedItem UDCallInfoUserDefinedItem;
	private UDCallInfoUserDefinedItem userDefinedItems[];

}
