package com.sinosoft.aspect.softphone.soap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * SOAP 包解析类
 * 解析返回XML数据转换成对象
 * @author wangjunhua
 * @since 1.0
 *
 */
@Slf4j
public class SoapNode {

	private boolean href;
	private String id;
	private String name;
	private Object obj;
	private Node root;
	private Map<String,String> properties = new HashMap<>();
	private Map arrays = new HashMap();
	private static Map<String,Class> beans = new HashMap<>();

	static{
		_initEventClass();
		_initEventClass1();
		_initProvisioningClass();
	}

	public SoapNode(Node root){
		this.root = root;
		try {
			initialize();
			read();
		} catch (InstantiationException e) {
			log.error("SoapNode: Instantiation  exception");
		} catch (IllegalAccessException e) {
			log.error("SoapNode: IllegalAccess  exception");

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHref() {
		return href;
	}

	public void setHref(boolean href) {
		this.href = href;
	}

	public String getId() {
		Node id = root.getAttributes().getNamedItem("id");
		return id.getNodeValue();
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getObject() {
		return obj;
	}

	public void setNode(Object node) {
		this.obj = node;
	}

	public Object getProperties(String properties) {
		return this.properties.get(properties);
	}

	public void setProperties(String properties,String value) {
		this.properties.put(properties, value);
	}

	public void setProperties(String properties,List value) {
		this.arrays.put(properties, value);
	}

	public Map getAllProperties() {
		return this.properties;
	}

	public Map getArrays() {
		return this.arrays;
	}

	/**
	 * 提取出SOAP 中的数据设置到对象中
	 * @param soap
	 * @param ids
	 */
	public void setSoapNodeValue(SoapNode soap, Map ids) {
		Object obj = soap.getObject();
		Map properties = soap.getAllProperties();
		for(Iterator it = properties.keySet().iterator();it.hasNext();){

			String mothdName = (String) it.next();
			String value = (String) properties.get(mothdName);
			SoapNode pram = (SoapNode) ids.get(value);
			soap.setValue(obj, mothdName, pram.getObject());

			if(pram.isHref()){
				pram.setSoapNodeValue(pram, ids);
			}
		}

		setArrays(soap,ids);
	}

	public void setArrays(SoapNode soap, Map ids){
		Object obj = soap.getObject();
		Map properties = soap.getArrays();
		for(Iterator it = properties.keySet().iterator();it.hasNext();){

			List arrays = new ArrayList();
			String mothdName = (String) it.next();
			List id = (List) properties.get(mothdName);

		    log.debug("执行的方法名称：>>>  " + mothdName + "    id  " + id);

			for(int i=0;i<id.size();i++){
				String key = (String) id.get(i);
				SoapNode pram = (SoapNode) ids.get(key);
				if(pram.isHref()){
					pram.setSoapNodeValue(pram, ids);
				}

				arrays.add(pram.getObject());
			}

			if(obj != null)
			soap.setValue(obj, mothdName, arrays.toArray());
		}
	}

	public void getNodeValue(Object obj, Node nodeh) {
		String mothdName = nodeh.getNodeName();
		Node href = nodeh.getAttributes().getNamedItem("href");
		if (href != null) {
			log.debug("href类型: >>> " + mothdName + " href值:>>> " + href.getNodeValue().substring(1));

			this.setHref(true);
			setProperties(mothdName,href.getNodeValue().substring(1));
		}else{

			Node type = nodeh.getAttributes().getNamedItem("xsi:type");
			if(type != null){
				if("soapenc:Array".equals(type.getNodeValue())){

					log.debug("href是数组类型: >>> " + mothdName + " href值:>>>  " + type.getNodeValue());

					NodeList fields = nodeh.getChildNodes();
					List<String> attributes = new ArrayList<>();
					for (int h = 0; h < fields.getLength(); ++h) {
						Node node = fields.item(h);
						Node hr = node.getAttributes().getNamedItem("href");
						if(hr != null){
							this.setHref(true);
							String ctype = hr.getNodeValue().substring(1);
							log.debug("数组类型: >>> " + mothdName + "  href值:>>>  " + ctype);

							attributes.add(ctype);
						}
					}
					//保存属性是数组类型的方法
					setProperties(mothdName,attributes);
				}else{
					log.debug("基本类型: >>> " + mothdName + "  值：>>>  " + type.getNodeValue());
					if(nodeh.getFirstChild()!=null){
						String value = nodeh.getFirstChild().getNodeValue();

						setValue(obj, mothdName, value);
					}
				}
			}
		}
	}

	private void setValue(Object obj,String fieldName,String value){
		Class c = obj.getClass();

		try {

			Field field = c.getDeclaredField(fieldName);
			log.debug("Class= {}, field.getName()  == {} field.getType()={}",c,field.getName(),field.getType());

			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), c);
			Method method = pd.getWriteMethod();//获得写方法
			toTypeSetValue(obj,method,field, value);

		} catch (Exception e) {
			log.error("Class= {},fieldName:{},异常{}",c,fieldName,e);
		}
	}

	private void setValue(Object target,String fieldName,Object value){
		try {
			Class c = target.getClass();
			Field field = c.getDeclaredField(fieldName);

			log.debug(" field.getName()  ==  " + field.getName() + " field.getType()  ==  " + field.getType());

			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), c);
			Method method = pd.getWriteMethod();//获得写方法
			if(field.getType().isArray()){
				Object[] v = (Object[])value;
				Class itemType = field.getType().getComponentType();//获取字段类型的数组组件类型的 Class

				Object array= Array.newInstance(itemType, v.length);
				for(int i=0;i<v.length;i++){
					Array.set(array, i, v[i]);
				}
				 method.invoke(obj, array);
				 log.debug("method : " + method + "  value " + value);
			}else{
				method.invoke(obj, value);
				log.debug("method : " + method + "  value " + value);
			}
		} catch (Exception e) {
			log.error("setValue:   =exception");
		}
	}

	private void initialize() throws IllegalAccessException, InstantiationException {
		Node classType = root.getAttributes().getNamedItem("xsi:type");
		String[] classzs = classType.getNodeValue().split(":");
		this.setName(classzs[1]);

		log.debug(name);
		if(beans.containsKey(name)){
			Class clazz = beans.get(name);
			obj = clazz.newInstance();
		}
	}

	private void read() {
		NodeList fields = root.getChildNodes();
		for (int h = 0; h < fields.getLength(); ++h) {
			Node nodeh = fields.item(h);
			if(obj != null){
				getNodeValue(obj, nodeh);
			}
		}
	}

	private void toTypeSetValue(Object obj,Method method,Field field,String value) throws Exception{
		Class classType = field.getType();
		//参数是基本数据类型  boolean、byte、char、short、int、long、float 和 double
		if(classType.isPrimitive()){
			String type = classType.toString();
			setValue(obj,method,type,value);

			log.debug("method : {},value={}",method,value);
		}else{
			log.debug("method : {},value={}",method,value);
			method.invoke(obj, value);
		}
	}

	// 参数是基本数据类型 boolean、byte、char、short、int、long、float 和 double
	private void setValue(Object obj, Method method, String type, String value)
			throws Exception {
		if (type.equals("boolean")) {
			method.invoke(obj, Boolean.valueOf(value));
		}

		if (type.equals("byte")) {
			method.invoke(obj, new Byte(value));
		}

		if (type.equals("short")) {
			method.invoke(obj, new Short(value));
		}

		if (type.equals("int")) {
			method.invoke(obj, new Integer(value));
		}

		if (type.equals("long")) {
			method.invoke(obj, new Long(value));
		}

		if (type.equals("float")) {
			method.invoke(obj, new Float(value));
		}

		if (type.equals("double")) {
			method.invoke(obj, new Double(value));
		}
	}

	private static void _initEventClass() {
		beans.put("AgentEvent", com.sinosoft.aspect.softphone.soap.event.Client.Notify.AgentEvent.class);
		beans.put("UDActive", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDActive.class);
		beans.put("UDAgentIndex", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDAgentIndex.class);
		beans.put("UDCallClear", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallClear.class);
		beans.put("UDCallInfo", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfo.class);
		beans.put("UDCallInfoAod", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoAod.class);
		beans.put("UDCallInfoCallback", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoCallback.class);
		beans.put("UDCallInfoEmail", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoEmail.class);
		beans.put("UDCallInfoOther", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoOther.class);
		beans.put("UDCallInfoScreen", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoScreen.class);
		beans.put("UDCallInfoUserDefined", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoUserDefined.class);
		beans.put("UDCallInfoUserDefinedItem", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoUserDefinedItem.class);
		beans.put("UDCallXferred", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallXferred.class);
		beans.put("UDMonitorState", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDMonitorState.class);
		beans.put("UDNotifyCallDataDef", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyCallDataDef.class);
		beans.put("UDNotifyCallDataDefFields", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyCallDataDefFields.class);
		beans.put("UDNotifyDispositionPlan", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyDispositionPlan.class);
		beans.put("UDNotifyDispositions", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyDispositions.class);
		beans.put("UDNotifyService", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyService.class);
		beans.put("UDNotifyServiceSettings", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyServiceSettings.class);
		beans.put("UDAudioPathConnection", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDAudioPathConnection.class);


	}

	private static void _initProvisioningClass() {
		beans.put("Dispositions", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.Dispositions.class);
		beans.put("Service", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.Service.class);
		beans.put("StatusReasons", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.StatusReasons.class);
		beans.put("User", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.User.class);
		beans.put("LoggedInUsers", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.LoggedInUsers.class);
		beans.put("AgentXfer", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.AgentXfer.class);
		beans.put("DirectorXfer", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.DirectorXfer.class);
		beans.put("ServiceXfer", com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.ServiceXfer.class);
	}

	private static void _initEventClass1() {
		beans.put("UDHeld", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDHeld.class);
		beans.put("UDIdle", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDIdle.class);
		beans.put("UDLoggingIn", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDLoggingIn.class);
		beans.put("UDLogin", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDLogin.class);
		beans.put("UDLoginConf", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDLoginConf.class);
		beans.put("UDLogout", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDLogout.class);
		beans.put("UDError", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDError.class);
		beans.put("UDNewService", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNewService.class);
		beans.put("UDScreenPop", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDScreenPop.class);
		beans.put("UDNotReady", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotReady.class);
		beans.put("UDConference", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDConference.class);
		beans.put("UDConsult", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDConsult.class);
		beans.put("UDDialing", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDDialing.class);
		beans.put("UDRemoveService", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDRemoveService.class);
		beans.put("UDUpdateCallDataDef", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDUpdateCallDataDef.class);
		beans.put("UDUpdateService", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDUpdateService.class);
		beans.put("UDWrap", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDWrap.class);
		beans.put("UDPasscode", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDPasscode.class);
		beans.put("UDPreview", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDPreview.class);
		beans.put("UDRecordState", com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDRecordState.class);
	}
}
