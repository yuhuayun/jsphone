package com.sinosoft.aspect.softphone.soap.agent.Client;

import java.lang.reflect.Method;

import lombok.Getter;
import lombok.Setter;

/**
 * 包含要与呼叫相关联的配置代码
 *
 * @author wangjunhua
 * @since 1.0.0
 *
 */
@Setter
@Getter
public class UDDisposition extends UDBase{

	/**
	 * 由系统指定的呼叫代号<br>
	 * 必须大于或等于 0
	 */
	private int callId;

	/**
	 * 数字配置代码<br>
	 * 必须大于或等于 0
	 */
	private int IDisposition;

	/**
	 * 未使用，应为 1<br>
	 * 必须大于或等于 0
	 */
	private int IType;

	/**
	 * 文本配置代码 <br>
	 * 必须为非空字符串
	 */
	private String SDisposition;

	/**
	 * 回调状态
	 * true 或 false
	 */
	private boolean callBackFlag;

	/**
	 * 下一个呼叫代号 <br>
	 * 必须大于或等于 0
	 */
	private int nextCallId;

	/**
	 * true 或 false
	 */
	private boolean saleFlag;

	/**
	 * 包含更新的 callinfo
	 */
	private UDCallInfoUserDefinedIn updatedCallInfoUserDefined;

	/**
	 * true 或 false
	 */
	private boolean wrapRequiredFlag;


	public String getHref(Integer index){
		StringBuffer href = new StringBuffer();
		href.append("<disposition href=\"#id").append(index).append("\" />\n");
		return href.toString();
	}

	public String toString(Integer index){
		StringBuffer dispositionXML = new StringBuffer();
		dispositionXML.append("<multiRef id=\"id").append(index).append("\" >\n");
		dispositionXML.append("<IDisposition>").append(this.IDisposition).append("</IDisposition>\n");
		dispositionXML.append("<IType>").append(this.IType).append("</IType>\n");
		dispositionXML.append("<SDisposition>").append(trim(trim(this.SDisposition))).append("</SDisposition>\n");
		dispositionXML.append("<callBackFlag>").append(this.callBackFlag).append("</callBackFlag>\n");
		dispositionXML.append("<callId>").append(this.callId).append("</callId>\n");
		dispositionXML.append("<nextCallId>").append(this.nextCallId).append("</nextCallId>\n");
		dispositionXML.append("<saleFlag>").append(this.saleFlag).append("</saleFlag>\n");

		appendUserDefinedHref(dispositionXML,index);

		dispositionXML.append("<wrapRequiredFlag>").append(this.wrapRequiredFlag).append("</wrapRequiredFlag>\n");
		dispositionXML.append("</multiRef>\n");

		if(updatedCallInfoUserDefined != null)
			appendUserDefinedData(dispositionXML,index + 1);

		return dispositionXML.toString();
	}

	private void appendUserDefinedHref(StringBuffer consultinXML,Integer index){
		if(updatedCallInfoUserDefined == null){
			consultinXML.append("<updatedCallInfoUserDefined/>\n");
		}else{
			consultinXML.append("<updatedCallInfoUserDefined href=\"#id").append(index + 1).append("\" />\n");
		}
	}

	private void appendUserDefinedData(StringBuffer consultinXML,int index){
		try {
			if(updatedCallInfoUserDefined != null){
				Class type = updatedCallInfoUserDefined.getClass();
				Method method = type.getMethod("toString", Integer.class);
				consultinXML.append(method.invoke(updatedCallInfoUserDefined, index));
			}
		} catch (Exception e) {

		}
	}
}
