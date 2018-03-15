package com.sinosoft.aspect.softphone.soap.agent.Client;

import java.lang.reflect.Method;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UDCallInfoUserDefinedIn {

	private UDCallInfoUserDefinedItemIn UDCallInfoUserDefinedItem;
	private UDCallInfoUserDefinedItemIn userDefinedItems[];

	public String toString(Integer index){
		int i = index;
		int count = index;
		StringBuffer callinfouserdefinedinXML = new StringBuffer();
		callinfouserdefinedinXML.append("<multiRef id=\"id").append(i).append("\" >\n");
		if(this.UDCallInfoUserDefinedItem == null){
			callinfouserdefinedinXML.append("<UDCallInfoUserDefinedItem/>");
		}else{
			i++;
			callinfouserdefinedinXML.append("<UDCallInfoUserDefinedItem href=\"#id").append(i).append("\" />\n");
		}

		if(this.userDefinedItems == null){
			callinfouserdefinedinXML.append("<userDefinedItems/>");
		}else{
			callinfouserdefinedinXML.append("<userDefinedItems>");
			for(int k=0;k<userDefinedItems.length;k++){
				i++;
				callinfouserdefinedinXML.append("<item href=\"#id").append(i).append("\" />\n");
			}
			callinfouserdefinedinXML.append("</userDefinedItems>");
		}
		callinfouserdefinedinXML.append("</multiRef>\n");

		if(this.UDCallInfoUserDefinedItem != null){
			count++;
			appendUDCallInfoUserDefinedItem(callinfouserdefinedinXML,count);
		}

		if(this.userDefinedItems != null){
			count++;
			appendUserDefinedItems(callinfouserdefinedinXML,count);
		}


		return callinfouserdefinedinXML.toString();
	}

	private void appendUDCallInfoUserDefinedItem(StringBuffer consultinXML,int index){
		try {
			Class type = UDCallInfoUserDefinedItem.getClass();
			Method method = type.getMethod("toString", Integer.class);
			consultinXML.append(method.invoke(UDCallInfoUserDefinedItem, index));
		} catch (Exception e) {
		}
	}

	private void appendUserDefinedItems(StringBuffer consultinXML,int index){
		try {
			if(userDefinedItems != null && userDefinedItems.length > 0)
			for(int i=0;i<userDefinedItems.length;i++){
				UDCallInfoUserDefinedItemIn item = userDefinedItems[i];
				if(item != null){
					Class type = item.getClass();
					Method method = type.getMethod("toString", Integer.class);
					consultinXML.append(method.invoke(item, new Object[]{new Integer(index)}));
					index++;
				}
			}
		} catch (Exception e) {
		}
	}
}
