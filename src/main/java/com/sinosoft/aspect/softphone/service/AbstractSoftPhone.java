package com.sinosoft.aspect.softphone.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import com.sinosoft.aspect.softphone.facade.SoftPhoneContent;
import com.sinosoft.aspect.softphone.facade.UserSession;
import com.sinosoft.aspect.softphone.soap.agent.AgentWebService;
import com.sinosoft.aspect.softphone.soap.agent.AgentWebServiceServiceLocator;
import com.sinosoft.aspect.softphone.soap.agent.Client.*;
import com.sinosoft.aspect.softphone.soap.agent.ConcertoAgentPortalSoapBindingStub;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNewService;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyCallDataDefFields;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyDispositions;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyService;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyServiceSettings;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDUpdateService;
import com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractSoftPhone {

	/* the amount of time to allow a SOAP call to be outstanding */
	private static final int CONNECT_TIMEOUT = 20000;
	private static final int READ_TIMEOUT = 20000;

	public static String Portal = "";
	public static String Concerto_Agent_Portal_URL = "";
	public static String Concerto_Security_Portal_URL = "";
	public static String Concerto_Event_Portal_URL = "";
	public static String Concerto_HostCon_Portal_URL = "";
	// 服务器类型必须为 1
	protected static final int SERVER_TYPE = 1;

	private final CallObj callObj = new CallObj();
	private ScreenPopBean sPop0 = null;
	private AgentSettings agentSettings = null;


	protected AgentWebService getAgentWebService(String agentId) {

		 AgentWebService msgDelegate = null;
			try {

				getConcertoPortal(agentId);
				log.info(Concerto_Agent_Portal_URL);
				log.info("HTTP connect timeout " + CONNECT_TIMEOUT
						+ " msec HTTP read timeout " + READ_TIMEOUT + " msec");
				URL webServiceURL = new URL(Concerto_Agent_Portal_URL);

				/* delegate wasn't created, make it so */
				AgentWebServiceServiceLocator locator = new AgentWebServiceServiceLocator();
				msgDelegate = locator.getConcertoAgentPortal(webServiceURL);

				/*
                 * use the stub interface to set sessions support and a
                 * timeout
                 */
				if (msgDelegate != null) {
                    ConcertoAgentPortalSoapBindingStub serviceStub = (ConcertoAgentPortalSoapBindingStub) msgDelegate;

                    // maintain Connect
                    serviceStub.setConnectTimeout(CONNECT_TIMEOUT);
                    // Time out after a minute
                    serviceStub.setReadTimeout(READ_TIMEOUT);

                } else {
                    log.info("ConcertoAgentPortal locator returned null");
                }

			} catch (MalformedURLException e) {
			}


		return msgDelegate;
	}

	protected String getAspectVersion(String agentId) throws IOException {
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		UDInfo info = new UDInfo();
		info.setListType(ListType.GET_VERSION);
		return (String) userSession.getMsgDelegate().getInfo(userSession.getUdAgent(), info);
	}

	protected boolean hasUser(String agentId)throws IOException{
		User outUser = this.getUserInfo(agentId);
		return outUser != null;
	}

	protected User getUserInfo(String agentId) throws IOException {
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		if (userSession == null){
			return null;
		}

		AgentWebService msgDelegate = userSession.getMsgDelegate();

		UDAgent agent = new UDAgent();
		agent.setLDAPUserId(agentId);
		// 获取用户信息
		UDInfo info = new UDInfo();
		info.setListType(ListType.GET_LOGIN_USER);

		User outUser = (User) msgDelegate.getInfo(agent, info);
		log.info("拨打的号码是坐席工号: {} 坐席名称{}",agentId,outUser);
		return outUser;
	}

	public UDCallInfoUserDefinedIn getEmptyUserDefinedData(){
		UDCallInfoUserDefinedIn uDefined = new UDCallInfoUserDefinedIn();
		UDCallInfoUserDefinedItemIn[] items = new UDCallInfoUserDefinedItemIn[20];

		for (int i = 0; i < items.length; i++) {
			items[i] = new UDCallInfoUserDefinedItemIn();
			items[i].setKey("emptyk");
			items[i].setValue("");
		}

		uDefined.setUserDefinedItems(items);

		return uDefined;
	}

	/***
	 * 判断外拨号码是固话or手机号码
	 * @param value
	 * @return
	 */
	public static boolean isPhone(String value) {
		if(null!=value && !"".equals(value)){
			return value.matches("^(0[0-9]{2,3}(-)?)?([2-9][0-9]{6,8})+(-[0-9]{1,5})?$") || value.matches("^0?1[3,5,8][0-9]{9}$") || value.equals("10000") || value.equals("10010") || value.equals("10086");
		}
		return false;
	}


	protected Object[] getInfo(String agentId,int ListType) {
		try {
			UserSession session = SoftPhoneContent.getSession(agentId);
			// 获取用户信息
			UDInfo info = new UDInfo();
			info.setListType(ListType);
			info.setServiceId(0);
			Object[] objs = (Object[]) session.getMsgDelegate().getInfo(session.getUdAgent(), info);
			log.info("getInfo 接口查询 " + ListType + " 数量是: " + objs.length);
			return objs;
		} catch (Exception e) {

		}
		return null;
	}

	private void getConcertoPortal(String agentId) {
		getPortal(agentId);
		getConcertoAgentPortal();
		getConcertoEventPortalURL();
		getConcertoSecurityPortalURL();
		log.info("AbstractSoftPhone :init:callObj=" + this.callObj);
	}

	protected String getPortal(String agentId) {
		        Portal = "10.9.11.122:8180";//CMSClientUtil.getRequestPortal(PropertieLoad.Default_Portal_DNS, agentId,m3);
		return "success";
	}


	private void getConcertoAgentPortal() {
		StringBuffer path = new StringBuffer();
		path.append("http://").append(Portal)
				.append("/ConcertoAgentPortal/services/ConcertoAgentPortal");
		Concerto_Agent_Portal_URL = path.toString();
	}

	private void getConcertoEventPortalURL() {
		StringBuffer path = new StringBuffer();
		path.append("http://").append(Portal)
				.append("/EventService/services/EventService");
		Concerto_Event_Portal_URL = path.toString();
	}

	private void getConcertoSecurityPortalURL() {
		StringBuffer path = new StringBuffer();
		path.append("http://").append(Portal)
				.append("/ConcertoSecurityPortal/services/SecurityPortalExt");
		Concerto_Security_Portal_URL = path.toString();
	}

	public static void protectedError(String message, Throwable throwable) {
		try {
			log.error(message, throwable);
		} catch (RuntimeException e) {
			log.error("RuntimeException in protectedError while logging '"
					+ message + "'");
			log.error("RuntimeException taken while trying to log the throwable");
		}
	}


	public int getDispositionLength0() {
		try {
			return this.agentSettings.getDispositionPlanLength();
		} catch (RuntimeException ee) {
			protectedError("getDispositionLength0: RuntimeException", ee);
		}
		return 0;
	}

	public int getDispId0(int i) {
		try {
			return this.agentSettings.getDispositionPlan(i).getDisposition_id();
		} catch (RuntimeException e) {
			protectedError("getDispId0: RuntimeException for disp plan " + i, e);
		}
		return 0;
	}

	public String getDispDesc0(int i) {
		try {
			return this.agentSettings.getDispositionPlan(i)
					.getDisposition_description();
		} catch (RuntimeException e) {
			protectedError("getDispDesc0: RuntimeException for disp plan " + i,
					e);
		}
		return null;
	}


	public void updateAgentState(int callID, int state) {
		try {
			this.callObj.updateAgentState(callID, state);
		} catch (RuntimeException e) {
			protectedError("updateAgentState: RuntimeException for callID "
					+ callID + " and state " + state, e);
		}
	}

	public void updateCurrentCall(int currentCall, int callID) {
		try {
			this.callObj.updateCurrentCall(currentCall, callID);
		} catch (RuntimeException e) {
			protectedError(
					"updateCurrentCall: RuntimeException for currentCall "
							+ currentCall + " and callID " + callID, e);
		}
	}

	public int getAgentState(int callID) {
		try {
			return this.callObj.getAgentState(callID);
		} catch (RuntimeException e) {
			protectedError("getAgentState: RuntimeException for callID "
					+ callID, e);
		}
		return 0;
	}


	public int getServiceID(int i) {
		return this.callObj.getServiceID(i);
	}


	public void getSPop0(int currentCall) {
		try {
			this.sPop0 = this.callObj.getSPop0(currentCall);
		} catch (RuntimeException e) {
			protectedError("getSPop0: RuntimeException for currentCall "
					+ currentCall, e);
		}
	}

	public int getSPop0Int(String key) {
		try {
			return this.sPop0.getInt(key);
		} catch (RuntimeException aex) {
			protectedError("getSPop0Int: RuntimeException for key " + key, aex);
		}

		return -1;
	}

	public boolean getSPop0Boolean(String key) {
		try {
			return this.sPop0.getBoolean(key);
		} catch (RuntimeException aex) {
			protectedError("getSPop0Boolean: RuntimeException for key " + key,
					aex);
		}

		return false;
	}

	public String getSPop0String(String key) {
		try {

			return this.sPop0.getString(key);
		} catch (RuntimeException aex) {
			protectedError("getSPop0String: RuntimeException for key " + key,
					aex);
		}
		return "";
	}

	public void removeService(int serviceID) {
		try {
			log.info("Removing Service from internal list with serviceID: "
					+ serviceID);

			this.callObj.removeService(serviceID);
		} catch (RuntimeException e) {
			protectedError("removeService: RuntimeException for service ID "
					+ serviceID, e);
		}
	}


	public void onNewService(UDNewService newServiceEvent) {
		log.info("onNewService enter");

		AgentSettings settings = new AgentSettings();

		int serviceId = newServiceEvent.getServiceID();
		int agentIndex = newServiceEvent.getAgentIndex();
		if (serviceId > 0) {
			this.callObj.addService(serviceId, settings);

			log.info("ServiceID=" + serviceId + " AgentIndex=" + agentIndex);
			settings.setService_id(serviceId);

			UDNotifyService serviceInfo = newServiceEvent.getServiceInfo();
			UDNotifyServiceSettings serviceSettings = newServiceEvent
					.getServiceSettings();
			UDNotifyDispositions[] dispositions = newServiceEvent
					.getDispositions();
			UDNotifyCallDataDefFields[] callDataDefFields = newServiceEvent
					.getCallDataDefFields();

			settings.handleUDNotifyService(serviceInfo);
			settings.handleUDNotifyServiceSettings(serviceSettings);
			settings.handleUDNotifyDispositions(dispositions);
			settings.handleUDNotifyCallDataDefFields(callDataDefFields);

			log.info(settings.toString());
			log.info(settings.callDataFieldsToString());
			log.info(settings.dispositionsToString());
		} else {
			log.info("onNewService: invalid or missing service ID "
					+ serviceId + "and agent Index " + agentIndex);
		}

		log.info("onNewService exit");
	}

	public void onUpdateService(UDUpdateService updateServiceEvent) {
		log.info("onUpdateService enter");

		int serviceId = updateServiceEvent.getServiceID();
		int agentIndex = updateServiceEvent.getAgentIndex();

		UDNotifyServiceSettings serviceSettings = updateServiceEvent
				.getServiceSettings();
		UDNotifyCallDataDefFields[] callDataDefFields = updateServiceEvent
				.getCallDataDefFields();

		AgentSettings settings = this.callObj.getAgentSettings(serviceId);
		if (settings != null) {
			log.info("ServiceID=" + serviceId + " AgentIndex=" + agentIndex);
			settings.setService_id(serviceId);
			if (serviceId == 0) {
				try {

					settings.handleUDNotifyServiceSettings(serviceSettings);
					settings.handleUDNotifyCallDataDefFields(callDataDefFields);
					log.info("Settings details....");
					log.info(settings.toString());
					log.info(settings.callDataFieldsToString());
				} catch (RuntimeException e) {
					log.error("Error while updating Agent Settings...." + e);
				}
			} else {
				UDNotifyService serviceInfo = updateServiceEvent
						.getServiceInfo();
				UDNotifyDispositions[] dispositions = updateServiceEvent
						.getDispositions();

				settings.handleUDNotifyService(serviceInfo);
				settings.handleUDNotifyServiceSettings(serviceSettings);
				settings.handleUDNotifyDispositions(dispositions);
				settings.handleUDNotifyCallDataDefFields(callDataDefFields);

				log.info(settings.toString());
				log.info(settings.callDataFieldsToString());
				log.info(settings.dispositionsToString());
			}
		} else {
			log.info("onUpdateService for service ID "
					+ serviceId
					+ " but agent does not already know about this service *****");
		}

		log.info("onUpdateService exit");
	}


}
