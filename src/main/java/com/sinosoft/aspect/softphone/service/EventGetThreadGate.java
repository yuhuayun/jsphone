package com.sinosoft.aspect.softphone.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.sinosoft.aspect.api.AgentError;
import com.sinosoft.aspect.softphone.caches.ServicesCache;
import com.sinosoft.aspect.softphone.facade.SoftPhoneContent;
import com.sinosoft.aspect.softphone.facade.UserSession;
import com.sinosoft.aspect.softphone.soap.agent.AgentPortalException;
import com.sinosoft.aspect.softphone.soap.agent.AgentWebService;
import com.sinosoft.aspect.softphone.soap.agent.Client.ListType;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgent;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgentInfo;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDInfo;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.*;
import com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.User;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;


/**
 * 此类专用处理 Aspect 事件
 * @author WangJunHua
 * @since 1.0
 *
 */
@Slf4j
public class EventGetThreadGate extends Thread {

	private EventWebServiceDelegate ewsDelegate;
    private ServicesCache serviceManager;
	private UDAgent myUDAgent;
	private TokenBean token;
	private AspectSoftPhone phone;
	private boolean isStop = false;

	public EventGetThreadGate(EventWebServiceDelegate ewsDelegate,
			UDAgent myUDAgent, TokenBean token,
			AspectSoftPhone aspectSoftPhone, ServicesCache serviceManager) {
		this.ewsDelegate = ewsDelegate;
		this.myUDAgent = myUDAgent;
		this.token = token;
		this.phone = aspectSoftPhone;
		this.serviceManager = serviceManager;
	}

	public void run() {
		while (true) {
				if (isStop) {
					break;
				}
				try {
				boolean isNaxt = returnOneEvent(token,myUDAgent.getAgentLoginName());
				if (!isNaxt) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						log.error("EWS  thread 异常" + e.getMessage());
					}
					break;
				}
				}catch (Exception e) {

				}
		}

	}

	private boolean returnOneEvent(final TokenBean TokenBean,
			final String user_name) throws Exception {
		// We already verified that these exist in the session

		boolean firstload = TokenBean.getFirstLoad();

		// Is this our first time being loaded?
		if (firstload) {
			log.info("userName=" + user_name + ":firstload");

			if (!firstLoadInitialize(myUDAgent, user_name, ewsDelegate)) {

				// We failed to initialize, so give up. We already sent a
				// login-failed to user
				return false;
			}

			// We need to take special action on the *first* IDLE event, so set
			// an attribute...

			// Tell the agent to load settings...

			TokenBean.setLoaded(); // We have successfully completed first-time
									// initialization
			return true;
		}

		log.debug("userName=" + user_name + ", Before while, EWS id=" + ewsDelegate.getAgt_id());

		long startTime = System.currentTimeMillis();
		boolean dropEvent;
		do {
			dropEvent = false;

			try {
				// Get our next event
				AgentEvent inEvent = ewsDelegate.getNextEvent(startTime);
				if (inEvent == null) {
					break; // out of do/while loop
				}

				int eventNo = inEvent.getEventId();
				log.info("userName=" + user_name + ", Received Event: "
						+ EventType.toString(eventNo) + " [" + eventNo + "] - "
						+ inEvent.getEventTimestamp());

				// Create our JavaScript snippet for this event
				dropEvent = processEvent(eventNo, user_name, inEvent,
						myUDAgent, serviceManager);

				if (dropEvent) {
					log.info("userName=" + user_name + ", DROPPING eid=" + eventNo);
				}
			} catch (RuntimeException ex) {
				// Runtime exceptions probably indicate a code bug. Drop the
				// event that
				// caused us to take the RuntimeException, complain clearly to
				// the logs,
				// let the client know we dropped an event, and move on to the
				// next event.

				log.error("userName=" + user_name
								+ ", while loop exception: RuntimeException: "
								+ ex.toString(), ex);

			}
		} while (dropEvent);
		return true;
	}

	private boolean processEvent(final int eventNo, final String user_name,
			final AgentEvent inEvent, final UDAgent myUDAgent,
			final ServicesCache serviceManager) {

		SocketIOClient receiverClient = SoftPhoneContent.getClient(myUDAgent.getLDAPUserId());//获取消息接受者的客户端
        UserSession userSession = SoftPhoneContent.getSession(myUDAgent.getLDAPUserId());

		int status = -1;
		boolean dropEvent = false;

		String message; // Used by most cases in the switch statement

		switch (eventNo) {
		case EventType.EVENT_ERROR: // 1
			// if (logger.isDebugEnabled()) {
			log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_ERROR() entered");
			// }
			UDError inPayload1 = (UDError) inEvent.getPayload();
			int status_reason = inPayload1.getFailCode();
			// get the localized/translated failure reason for display to user
			String localizedFailReason = inPayload1.getLocalizedFailReason();
			// get the non-localized failure reason
			String failReason = inPayload1.getFailReason();
			// if (logger.isDebugEnabled()) {
            log.info("userName=" + user_name + ", Event=" + eventNo
					+ ": errorCode=" + status_reason + " ERROR=" + failReason
					+ " Localized ERROR=" + localizedFailReason);
			// }
			if (("GW login failed.").equals(failReason)) {
				//return false;
			}

			switch (status_reason) {
			case 17: // GatewayLoginFailure
				//
				//phone.notifyListeners(17);
				break;
			case 218: // Your Station Number <%s> is being used by Agent <%s>
				//
				//phone.notifyListeners(218);
				break;
			case 219: // AgentAlreadyLoggedIn
				//
//				if (phone.inAgent != null) {
//					// phone.getInfo(ListType);
//					phone.logout(phone.inAgent.getLDAPUserId(), "");
//				} else {
//					//phone.notifyListeners(219);
//				}
				break;
			case 110: // AgentNotAvailable
				//phone.notifyListeners(110);
				break;
			}
			// Replace ', \n and \r before passing data to LogicFrame
			message = "eventAction_EVENT_ERROR('" + errorEscape(failReason)
					+ "','" + errorEscape(localizedFailReason) + "','"
					+ status_reason + "'); ";
            log.error(message);


            AgentError agentError = new AgentError(status_reason,errorEscape(failReason),errorEscape(localizedFailReason));
            receiverClient.sendEvent("event_error",agentError);

			break;

		case EventType.EVENT_PHONEFOUL: // 2

			writeLog(user_name,eventNo,"EVENT_PHONEFOUL");
			UDAgentIndex inPayload2 = (UDAgentIndex)inEvent.getPayload();

			// 通知UI 坐席的网关服务器的音频路径已断开
			receiverClient.sendEvent("event_phonefoul",inPayload2);
			break;

		case EventType.EVENT_PHONEOFFHOOK: // 3

			writeLog(user_name,eventNo,"EVENT_PHONEOFFHOOK");
			UDAgentIndex inPayload3 = (UDAgentIndex)inEvent.getPayload();

			// 通知UI 坐席电话已摘机
			receiverClient.sendEvent("event_phoneoffhook",inPayload3);

			break;

		case EventType.EVENT_UPDATESERVICE: // 4

			writeLog(user_name,eventNo,"EVENT_UPDATESERVICE");

			UDUpdateService inPayload4 = (UDUpdateService) inEvent.getPayload();

			// Sadanand for agent settings update
			if ((inPayload4 != null) && (inPayload4.getServiceID() == 0)) {
				// do nothing but do not ignore
			} else if (!serviceManager.updateService(inPayload4)) {
				dropEvent = true;
				break;
			}

			// 更新服务信息
			phone.onUpdateService(inPayload4);

            // 通知UI层坐席的服务信息有变更
			//receiverClient.sendEvent("event_updateservice",inPayload3);

			break;

		case EventType.EVENT_REMOVESERVICE: // 5

            log.info("userName={},eid={}, EventType.EVENT_REMOVESERVICE() entered",user_name,eventNo);

			UDRemoveService inPayload5 = (UDRemoveService) inEvent.getPayload();

			// Check to see if the agent is in the service, if not ignore the
			// event
			if (!serviceManager.removeService(inPayload5)) {
				dropEvent = true;
				break;
			}

			// 删除坐席服务信息
			phone.removeService(inPayload5.getServiceID());
			// 通知UI坐席已经从服务中删除

			break;

		case EventType.EVENT_UPDATEDISPOSITION: // 6

            log.info("userName={},eid={}, EventType.EVENT_UPDATEDISPOSITION() entered",user_name,eventNo);

			// UDUpdateDisposition inPayload6 = (UDUpdateDisposition)inEvent.getPayload();

			break;

		case EventType.EVENT_UPDATEDISPOSTIONPLAN: // 7

            log.info("userName={},eid={}, EventType.EVENT_UPDATEDISPOSTIONPLAN() entered",user_name,eventNo);

			//UDUpdateDispositionPlan inPayload7 = (UDUpdateDispositionPlan)inEvent.getPayload();

			break;

		case EventType.EVENT_NEWVOICEMAIL: // 8

            log.info("userName={},eid={}, EventType.EVENT_NEWVOICEMAIL() entered",user_name,eventNo);
			// UDUpdateService inPayload8 = (UDUpdateService)inEvent.getPayload();

			break;

		case EventType.EVENT_UPDATECALLDATADEF: // 9

            log.info("userName={},eid={}, EventType.EVENT_UPDATECALLDATADEF() entered",user_name,eventNo);

			UDUpdateCallDataDef inPayload9 = (UDUpdateCallDataDef) inEvent.getPayload();
			if (!serviceManager.isUsingCallDataDef(inPayload9
					.getCallDataDefId())) {
				log.debug("userName="
						+ user_name
						+ " EventType.EVENT_UPDATECALLDATADEF(), Agent not using CallDataDef ID "
						+ inPayload9.getCallDataDefId() + ", event ignored");

				dropEvent = true;
				break;
			}


			break;

		case EventType.EVENT_UPDATEAGENTSTATUSREASONS: // 10

			log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_UPDATEAGENTSTATUSREASONS() entered");

			// UDAgentIndex inPayload10 = (UDAgentIndex)inEvent.getPayload();

			break;

		case EventType.EVENT_UPDATEEXTERNALROUTE: // 11

			log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_UPDATEEXTERNALROUTE() entered");
			//
			// UDExternalRoute inPayload11 = (UDExternalRoute) inEvent
			// .getPayload();
			//
			// logonBean.setAgentrouteaccessid(inPayload11.getExternalRouteId());

			break;

		case EventType.EVENT_UPDATEREALTIMEPORT: // 12

            log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_UPDATEREALTIMEPORT() entered");

			// UDRealTimePort inPayload12 = (UDRealTimePort)inEvent.getPayload();

			break;

		case EventType.EVENT_LOGINCONF: // 13

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_LOGINCONF() entered");

			UDLoginConf inPayload13 = (UDLoginConf) inEvent.getPayload();

			myUDAgent.setAgentIndex(inPayload13.getAgentIndex());
			myUDAgent.setAgentLoginName(inPayload13.getAgentLoginName());
			// myUDAgent.setClientRole(inPayload13.getAgentType());

			log.info("userName=" + user_name + ",    AgentID="
					+ inPayload13.getAgentLoginName() + ", AgentIndex="
					+ inPayload13.getAgentIndex() + ", SwitchID="
					+ inPayload13.getNtSwitchId() + " AgentType="
					+ inPayload13.getAgentType());

			// Set the agent index and user name here in case you don't have an
			// extension and therefore won't get event 15 (EVENT_LOGGINGIN).

            log.debug("userName=" + user_name + ",    AgentID="
					+ inPayload13.getAgentLoginName() + ", AgentIndex="
					+ inPayload13.getAgentIndex() + ", SwitchID="
					+ inPayload13.getNtSwitchId());

			receiverClient.sendEvent("event_loginconf", inPayload13);

			break;

		case EventType.EVENT_SYSTEMWAIT: // 14

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_SYSTEMWAIT() entered");

			UDSystemWait inPayload14 = (UDSystemWait)inEvent.getPayload();
            receiverClient.sendEvent("event_systemwait", inPayload14);

			break;

		case EventType.EVENT_LOGGINGIN: // 15

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_LOGGINGIN() entered");

			UDLoggingIn inPayload15 = (UDLoggingIn) inEvent.getPayload();

			status = inPayload15.getStatusReason();
			log.info("userName=" + user_name + ",eid=" + eventNo
					+ "  status = " + status + " EventType.EVENT_LOGGINGIN() "
					+ agentStats(status));

            receiverClient.sendEvent("event_loggingin", inPayload15);//转发给接收者客户端

			break;

		case EventType.EVENT_PASSCODE: // 16

            log.debug("userName=" + user_name + ",eid=" + eventNo + " EventType.EVENT_PASSCODE() entered");
			UDPasscode inPayload16 = (UDPasscode) inEvent.getPayload();

            receiverClient.sendEvent("event_passcode", inPayload16);

            break;

		case EventType.EVENT_NEWSERVICE: // 17

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_NEWSERVICE() entered");

			// 保存坐席服务信息
			UDNewService inPayload17 = (UDNewService) inEvent.getPayload();

			// 添加坐席的服务信息
			//serviceManager.newService(inPayload17);
			phone.onNewService(inPayload17);

			log.info("UDNewService={}",inPayload17);

			userSession.setServiceID(inPayload17.getServiceID());
            userSession.addNewService(inPayload17);

            receiverClient.sendEvent("event_newservice",inPayload17);
			break;

		case EventType.EVENT_LOGIN: // 18

            log.debug("userName=" + user_name + ",eid=" + eventNo + " EventType.EVENT_LOGIN() entered");

			UDLogin inPayload18 = (UDLogin) inEvent.getPayload();
			int switchID = inPayload18.getNtSwitchId();

			status = inPayload18.getStatusReason();
			log.info("软电话状态: status=" + status + "   " + agentStats(status));

            log.debug("userName=" + user_name + ", Event=" + eventNo
					+ ", switch__id=" + switchID);


			receiverClient.sendEvent("event_login", inPayload18);//转发给接收者客户端

			break;

		case EventType.EVENT_IDLE: // 19

            log.debug("userName=" + user_name + ",eid=" + eventNo + " EventType.EVENT_IDLE() entered");

			UDIdle inPayload19 = (UDIdle) inEvent.getPayload();
			status = inPayload19.getStatusReason();
			log.info("AgentIndex=" + inPayload19.getAgentIndex() + " status=" + status + " " + agentStats(status));

			receiverClient.sendEvent("event_idle",inPayload19);
			break;

		case EventType.EVENT_LOGOUT: // 20

            log.debug("userName=" + user_name + ",eid=" + eventNo + " EventType.EVENT_LOGOUT() entered");

			UDLogout inPayload20 = (UDLogout) inEvent.getPayload();
			status = inPayload20.getStatusReason();

			log.info("AgentIndex=" + inPayload20.getAgentIndex()
					+ " status=" + status + "  " + agentStats(status));


            userSession.getEventGetThread().setStop(true);
            SoftPhoneContent.remove(userSession.getAgentId());

			receiverClient.sendEvent("event_logout",inPayload20);//转发给接收者客户端

			break;

		case EventType.EVENT_SCREENPOP: // 21

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_SCREENPOP() entered");

			UDScreenPop inPayload21 = (UDScreenPop) inEvent.getPayload();

			status = inPayload21.getStatusReason();
			ScreenPopBean screenPopBean21 = new ScreenPopBean();
			screenPopBean21.update(inPayload21);


			log.info("userName="
					+ user_name
					+ ",    userFieldLength="
					+ inPayload21.getCallInfo().getUserDefinedData()
							.getUserDefinedItems().length);
			log.info("userName=" + user_name
					+ ",    EventGet:ScreenPop:RequiredRejectReason="
					+ screenPopBean21.isCallfield_RequiredRejectReason());
			log.info("userName=" + user_name
					+ ",    EventGet:ScreenPop:RequiredResponse="
					+ screenPopBean21.isCallfield_RequiredResponse());
			log.info("userName=" + user_name
					+ ",    EventGet:ScreenPop:TimeoutInSeconds="
					+ screenPopBean21.getCallfield_TimeoutInSeconds());
			log.info("userName=" + user_name
					+ ",    EventGet:ScreenPop:PlayAudioAlert="
					+ screenPopBean21.isCallfield_PlayAudioAlert());
			log.info("userName=" + user_name
					+ ",    EventGet:ScreenPop:PBXAODServiceID="
					+ inPayload21.getPbxaodserviceid() + " status=" + status);
			log.info("CallID=" + inPayload21.getCallID() + " ANI="
					+ screenPopBean21.getCallfield_ANI() + " DNIS="
					+ screenPopBean21.getCallfield_DNIS() + " PhoneNumber="
					+ screenPopBean21.getCallfield_PhoneNumber() + "  "
					+ agentStats(status)+" ssss"+screenPopBean21.getCallfield_CallerID());



			getScreenPop(screenPopBean21, user_name, "ScreenPop");
			phone.updateAgentState(inPayload21.getCallID(), status);


            receiverClient.sendEvent("event_screenpop",inPayload21);//转发给接收者客户端

            break;

		case EventType.EVENT_ACTIVE: // 22

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_ACTIVE() entered" );

			UDActive inPayload22 = (UDActive) inEvent.getPayload();

			status = inPayload22.getStatusReason();


			log.info("userName=" + user_name + ", EventGet:Active:call_id="
					+ inPayload22.getCallID() + " chatServer="
					+ inPayload22.getIPAddress() + ":"
					+ inPayload22.getPortNumber() + " MediaType="
					+ inPayload22.getMediaType() + " status=" + status + "  "
					+ agentStats(status));

			userSession.setCallId(inPayload22.getCallID());
			receiverClient.sendEvent("event_active",inPayload22);//转发给接收者客户端
			break;

		case EventType.EVENT_HOLD: // 23 保持事件

			log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_HOLD() entered");
			UDHeld inPayload23 = (UDHeld) inEvent.getPayload();

			status = inPayload23.getStatusReason();
			log.info("callID=" + inPayload23.getCallID() + "  status="
					+ inPayload23.getStatusReason() + "  " + agentStats(status));


			// 通知UI当前状态
			receiverClient.sendEvent("event_hold",inPayload23);//转发给接收者客户端

			break;

		case EventType.EVENT_WRAP: // 24 后处理

            log.info("userName=" + user_name + ", eid=" + eventNo
					+ " EventType.EVENT_WRAP() entered");
			UDWrap inPayload24 = (UDWrap) inEvent.getPayload();
			status = inPayload24.getStatusReason();

			log.info(" call_id=" + inPayload24.getCallID() + "  status="
					+ status + "  " + agentStats(status));


			receiverClient.sendEvent("event_wrap",inPayload24);//转发给接收者客户端
			break;

		case EventType.EVENT_DIALING: // 25

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_DIALING() entered");

			UDDialing inPayload25 = (UDDialing) inEvent.getPayload();

			status = inPayload25.getStatusReason();

			phone.updateAgentState(inPayload25.getCallID(),
					inPayload25.getStatusReason());

			log.info("callID=" + inPayload25.getCallID() + " status="
					+ status + "  " + agentStats(status));

			receiverClient.sendEvent("event_dialing",inPayload25);//转发给接收者客户端

			break;

		case EventType.EVENT_CALLCLEAR: // 26

            log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CALLCLEAR() entered");

			UDCallClear inPayload26 = (UDCallClear) inEvent.getPayload();
			//endTime = inEvent.getEventTimestamp();
//			System.out.println(endTime+"**************************");
			status = inPayload26.getStatusReason();
			log.info("callID=" + inPayload26.getCallID() + "  status="
					+ status + "  " + agentStats(status));
			int callID = inPayload26.getCallID();
//			if (phone.getConsult_call_id() == callID) {
//				phone.setConsulted(false);
//				phone.setConsult_call_id(-1);
//			}
//
//			if (phone.getConference_call_id() == callID) {
//				phone.setConference_call_id(-1);
//			}
//
//			if (phone.getCallID(phone.currentPane) == callID) {
//				phone.setConsulted(false);
//
//				if (PropertieLoad.isRecording) {
//					phone.record(RecordRequestType.ccpStopRecord);
//				}
//			}
			// 如果是主呼叫挂断的 callID 则更新 咨询的ID为当前callID
//			System.out.println("consult_ID  " + consult_ID);
//			System.out.println("callID  " + callID);
//			if (call_ID == callID) {
////				System.out.println("执行了call_ID==callID");
//				if (consult_ID > 0) {
//					phone.updateCurrentCall(phone.getCurrentPane(), consult_ID);
//				}
//			}
//
//			// 如果是咨询方挂断的 callID 则更新当前callID
//			if (consult_ID == callID) {
////				System.out.println("执行了consult_ID==callID");
//				if (callID > 0) {
//					phone.updateCurrentCall(phone.getCurrentPane(), call_ID);
//				}
//			}

			phone.updateAgentState(inPayload26.getCallID(), inPayload26.getStatusReason());

            receiverClient.sendEvent("event_callclear",inPayload26);

            break;

		case EventType.EVENT_PREVIEW: // 27

            log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_PREVIEW() entered");

			UDPreview inPayload27 = (UDPreview) inEvent.getPayload();

			status = inPayload27.getStatusReason();
			ScreenPopBean screenPopBean27 = new ScreenPopBean();
			screenPopBean27.update(myUDAgent.getAgentIndex(), inPayload27);

            log.info("userName="
					+ user_name
					+ ",    userFieldLength="
					+ inPayload27.getCallInfo().getUserDefinedData()
							.getUserDefinedItems().length);
			log.info("callID=" + inPayload27.getCallID() + "status="
					+ status + "  " + agentStats(status));


			getScreenPop(screenPopBean27, user_name, "Preview");


            receiverClient.sendEvent("event_preview",inPayload27);
			break;

		case EventType.EVENT_CONSULT: // 28

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CONSULT() entered");

			UDConsult inPayload28 = (UDConsult) inEvent.getPayload();
			//consult_ID = inPayload28.getConsultCallID();
			status = inPayload28.getStatusReason();
//			phone.setConsult_call_id(inPayload28.getConsultCallID());
//			phone.updateCurrentCall(phone.getCurrentPane(),
//					inPayload28.getOriginalCallID());
			phone.updateAgentState(inPayload28.getOriginalCallID(), status);

			log.info("call_id=" + inPayload28.getOriginalCallID()
					+ " status=" + status + " ConsultCallID="
					+ inPayload28.getConsultCallID() + "  "
					+ agentStats(status));

			userSession.setConsultCallId(inPayload28.getConsultCallID());

            receiverClient.sendEvent("event_consult",inPayload28);
			break;

		case EventType.EVENT_CALLXFERRED: // 29

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CALLXFERRED() entered");

			UDCallXferred inPayload29 = (UDCallXferred) inEvent.getPayload();

			status = inPayload29.getStatusReason();
            log.debug("userName=" + user_name + ", consult_call_id="
					+ inPayload29.getConsultCallID() + ", original_call_id="
					+ inPayload29.getOriginalCallID() + ", new_call_id="
					+ inPayload29.getNewCallID() + ", original_agent_index="
					+ inPayload29.getOriginalAgentIndex() + ", status="
					+ status + "  " + agentStats(status));

			status = inPayload29.getStatusReason();
			log.info("callID=" + inPayload29.getOriginalCallID()
					+ "  status=" + status + "  " + agentStats(status));
			int originalCallID = inPayload29.getOriginalCallID();
//			if (phone.getConsult_call_id() == originalCallID) {
//				phone.setConsulted(false);
//				phone.setConsult_call_id(-1);
//			}
//
//			if (phone.getCallID(phone.currentPane) == originalCallID) {
//				phone.setConsulted(false);
//
//				if (PropertieLoad.isRecording) {
//					phone.record(RecordRequestType.ccpStopRecord);
//				}
//			}

			//phone.setCurrentPane(1);
			// phone.updateCurrentCall(phone.getCurrentPane(),
			// inPayload29.getNewCallID());

//			if(!phone.consultActive){
//				phone.updateAgentState(inPayload29.getOriginalCallID(), status);
//				//phone.notifyListeners(eventNo, status);
//			}

            receiverClient.sendEvent("event_callxferred",inPayload29);

            break;

		case EventType.EVENT_CALLRELEASEDXFERRED: // 30

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CALLRELEASEDXFERRED() entered");

			// UDCallReleasedXferred inPayload30 =
			// (UDCallReleasedXferred)inEvent.getPayload();

			// phone.notifyListeners(EventType.EVENT_CALLRELEASEDXFERRED);
           // receiverClient.sendEvent("event_consult",inPayload28);

            break;

		case EventType.EVENT_CALLREROUTED: // 31

			log.info("userName=" + user_name + ",eid=" + eventNo + " EventType.EVENT_CALLREROUTED() entered");

			// UDCallReRouted inPayload31 =
			// (UDCallReRouted)inEvent.getPayload();
			//endTime = inEvent.getEventTimestamp();  //转接后，原坐席和客户的通话时间
			 //phone.notifyListeners(EventType.EVENT_CALLREROUTED);
			break;

		case EventType.EVENT_CONFERENCE: // 32

			log.debug("userName=" + user_name + ",eid=" + eventNo + " EventType.EVENT_CONFERENCE() entered");

			UDConference inPayload32 = (UDConference) inEvent.getPayload();

			status = inPayload32.getStatusReason();
			log.info("userName=" + user_name + ", call_id="
					+ inPayload32.getOriginalCallID() + " status=" + status
					+ "  " + agentStats(status) + " ConferenceCallID="
					+ inPayload32.getConferenceCallID() + " ConsultCallID="
					+ inPayload32.getConsultCallID());


			userSession.setConferenceCallId(inPayload32.getConferenceCallID());

            receiverClient.sendEvent("event_conference",inPayload32);

            break;

		case EventType.EVENT_RECORDINGSTATE: // 33 录音事件

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_RECORDINGSTATE() entered");

			UDRecordState inPayload33 = (UDRecordState) inEvent.getPayload();
			int autoRecording = inPayload33.getAutoRecording();
			int state = inPayload33.getRecordingState();

			log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_RECORDINGSTATE() autoRecording="
					+ autoRecording + "   state=" + state);

            log.info("inPayload33.getFileName()="
					+ inPayload33.getFileName());
            log.info("inPayload33.getAgentIndex()="
					+ inPayload33.getAgentIndex());
            log.info("inPayload33.getMediaType()="
					+ inPayload33.getMediaType());
            log.info("inPayload33.getRecordingIndex()="
					+ inPayload33.getRecordingIndex());
            log.info("inPayload33.getRecordingRate() ="
					+ inPayload33.getRecordingRate());
            log.info("inPayload33.getRecordingSeq() ="
					+ inPayload33.getRecordingSeq());
            log.info("inPayload33.getRecordingStoreId() ="
					+ inPayload33.getRecordingStoreId());
            log.info("inPayload33.getRecordingState() ="
					+ inPayload33.getRecordingState());

			// 添加录音信息到内存中
			//phone.addBeanToMap(UDRecordState.class.getName(), inPayload33);
//			if (isTalking) {
            log.info("======Talking RecordId ====");
//			} else {
//				phone.setRecordingSeq(inPayload33.getRecordingSeq());
//				logger.info("平安录音标识： =" + phone.getRecordSeq());

//				if(2==state){//开始录音
////					logger.info("开始录音............");
//					phone.start_recordId = phone.getRecordSeq();
//				}else if(6==state){
////					logger.info("停止录音............");
//					phone.stop_recordId = phone.getRecordSeq();
//				}
////				logger.info("start_recordId:"+phone.start_recordId+"\t stop_recordId:"+phone.stop_recordId+"\t 判断结果："+(!phone.start_recordId.equals(phone.stop_recordId)));
//				if(!phone.start_recordId.equals(phone.stop_recordId)){
////
//					//phone.notifyListeners(EventType.EVENT_RECORDINGSTATE);
//				}
//			}

			if (autoRecording == 1) {
				dropEvent = true;
				break;
			}

			break;

		case EventType.EVENT_MONITORSTATE: // 34
			UDMonitorState inPayload34 = (UDMonitorState) inEvent.getPayload();

            log.info("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_MONITORSTATE() entered   " + "  "
					+ agentStats(inPayload34.getMonitoringState()));

            log.info("EVENT_MONITORSTATE   MonitoringSeq=[ "
					+ inPayload34.getMonitoringSeq() + " ] "
					+ "  MonitoringState=[" + inPayload34.getMonitoringState()
					+ " ] PortNum=" + inPayload34.getPortNum());
			//manaNumber = inPayload34.getMonitoringState();

            receiverClient.sendEvent("event_monitorstate",inPayload34);
            break;

		case EventType.EVENT_GATEWAYDOWN: // 35

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_GATEWAYDOWN() entered");

			UDSwitch inPayload35 = (UDSwitch)inEvent.getPayload();
			receiverClient.sendEvent("event_gatewaydown",inPayload35);
			break;

		case EventType.EVENT_GATEWAYUP: // 36

			log.info("userName={},eid={} EventType.EVENT_GATEWAYUP() entered",user_name,eventNo);

			UDSwitch inPayload36 = (UDSwitch)inEvent.getPayload();
			receiverClient.sendEvent("event_gatewayup",inPayload36);
			break;

		case EventType.EVENT_CTIDOWN: // 37

            log.info("userName={},eid={} EventType.EVENT_CTIDOWN() entered",user_name,eventNo);

            UDSwitch inPayload37 = (UDSwitch)inEvent.getPayload();
			receiverClient.sendEvent("event_ctidown",inPayload37);
			break;

		case EventType.EVENT_CTIUP: // 38

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CTIUP() entered");

			UDSwitch inPayload38 = (UDSwitch)inEvent.getPayload();
			receiverClient.sendEvent("event_ctiup",inPayload38);

			break;

		case EventType.EVENT_LOGOUTPENDING: // 39

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_LOGOUTPENDING() entered");

			UDLogout inPayload39 = (UDLogout)inEvent.getPayload();

            receiverClient.sendEvent("event_logoutpending",inPayload39);

            break;

		case EventType.EVENT_NOTREADYPENDING: // 40

			UDAgentIndex inPayload40 = (UDAgentIndex)inEvent.getPayload();
            log.info("userName={},eid={} EventType.EVENT_NOTREADYPENDING() entered",user_name,eventNo);

			receiverClient.sendEvent("event_notreadypending",inPayload40);
			break;

		case EventType.EVENT_NOTREADY: // 41

            log.debug("userName=" + user_name + ",eid=" + eventNo + " EventType.EVENT_NOTREADY() entered");

			UDNotReady inPayload41 = (UDNotReady) inEvent.getPayload();
			log.info("userName=" + user_name + ",eid=" + eventNo
					+ ", parkFlag=" + inPayload41.isToParkState() + " status="
					+ inPayload41.getStatusReason() + "  "
					+ agentStats(inPayload41.getStatusReason()));

			receiverClient.sendEvent("event_notready",inPayload41);//转发给接收者客户端

			break;

		case EventType.EVENT_PHONERECONNECT: // 42

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_PHONERECONNECT() entered");

			// UDAgentIndex inPayload42 = (UDAgentIndex)inEvent.getPayload();

			break;

		case EventType.EVENT_LISTCHATENTITIES: // 43

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_LISTCHATENTITIES() entered");

			// UDChatEntityList inPayload43 =
			// (UDChatEntityList)inEvent.getPayload();
			break;

		case EventType.EVENT_CHATMESSAGE: // 44

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CHATMESSAGE() entered");

			// UDEntitySendText inPayload44 = (UDEntitySendText) inEvent
			// .getPayload();

			break;

		case EventType.EVENT_CHATURL: // 45

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CHATURL() entered");

			// UDEntityPushUrl inPayload45 = (UDEntityPushUrl) inEvent
			// .getPayload();

			//phone.notifyListeners(EventType.EVENT_CHATURL);
			break;

		case EventType.EVENT_CHATDIALREQUEST: // 46

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CHATDIALREQUEST() entered");

			// UDDialRequest inPayload46 = (UDDialRequest) inEvent.getPayload();

			//phone.notifyListeners(EventType.EVENT_CHATDIALREQUEST);
			break;

		case EventType.EVENT_ACTIVATECALL: // 47

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_ACTIVATECALL() entered");

			// UDActivateCall inPayload47 = (UDActivateCall)
			// inEvent.getPayload();

			//phone.notifyListeners(EventType.EVENT_ACTIVATECALL);
			break;

		case EventType.EVENT_DIRECTORACK: // 48

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_DIRECTORACK() entered");

			// UDDirectorAck inPayload48 = (UDDirectorAck)inEvent.getPayload();
			//phone.notifyListeners(EventType.EVENT_DIRECTORACK);
			break;

		case EventType.EVENT_BADPASSCODE: // 49

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_BADPASSCODE() entered");

			// UDBadPassCode inPayload49 = (UDBadPassCode) inEvent.getPayload();
			//phone.notifyListeners(EventType.EVENT_BADPASSCODE);
			break;

		case EventType.EVENT_CHATENTITYADDED: // 50

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CHATENTITYADDED() entered");

			// UDEntity inPayload50 = (UDEntity) inEvent.getPayload();

			//phone.notifyListeners(EventType.EVENT_CHATENTITYADDED);
			break;

		case EventType.EVENT_CHATENTITYREMOVED: // 51

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_CHATENTITYREMOVED() entered");

			// UDEntity inPayload51 = (UDEntity)inEvent.getPayload();
			break;

		case EventType.EVENT_ADVANCEFEATURE: // 52

			writeLog(user_name,eventNo,"EVENT_ADVANCEFEATURE");

            break;

		case EventType.EVENT_SCREENCAPTURE: // 53

            log.debug("userName=" + user_name + ",eid=" + eventNo
					+ " EventType.EVENT_SCREENCAPTURE() entered");
            break;

		case EventType.EVENT_AGENTSTATS: // 54

            log.info("userName={},eventNo={} EventType.EVENT_AGENTSTATS() entered",user_name,eventNo);

			// UDAgentStatsCollection inPayLoad54 = (UDAgentStatsCollection)inEvent.getPayload();

			break;

		case EventType.EVENT_SESSIONTIMEOUT: // 55

            log.info("userName={},eventNo={} EventType.EVENT_SESSIONTIMEOUT() entered",user_name,eventNo);


			break;

		case EventType.EVENT_CCDOWN: // 56

			writeLog(user_name,eventNo,"EVENT_CCDOWN");
			break;

		// case EventType.EVENT_CCUP: // 57 - should never receive this
		// case EventType.EVENT_WPAMESSAGE: // 58 - should never receive this

		case EventType.EVENT_EMAILSTATS: // 59 未实现

			writeLog(user_name,eventNo,"EVENT_EMAILSTATS");
			break;

		case EventType.EVENT_AUDIOPATHCONNECTION: // 60

			log.debug("userName={},eventNo={} EventType.EVENT_AUDIOPATHCONNECTION() entered",user_name,eventNo);
			// UDAudioPathConnection inPayload60 = (UDAudioPathConnection)inEvent.getPayload();

			break;

		case EventType.EVENT_ACCEPT_CALL_TIMEOUT: // 61

            log.debug("userName={},eventNo={} EventType.EVENT_ACCEPT_CALL_TIMEOUT() entered",user_name,eventNo);

			break;

		case EventType.EVENT_EMAIL_FORWARD_ACK: // 62

            log.debug("userName={},eventNo={} EventType.EVENT_EMAIL_FORWARD_ACK() entered",user_name,eventNo);

			// UDForwardEmailAck inPayload62 = (UDForwardEmailAck)inEvent.getPayload();

			// eventWriter.write("EVENT_EMAIL_FORWARD_ACK",inPayload62.getMessageId());
			break;

		default:
            log.debug("userName={}, UNEXPECTED EVENT {} [{}] *************",user_name,EventType.toString(eventNo),eventNo);
			break;
		}



		return dropEvent;
	}

	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	private void writeLog(String userName,Integer eventNo,String eventName){
		log.info("userName={},eventNo={} EventType.{}() entered.",userName,eventNo,eventName);
	}

	private boolean firstLoadInitialize(final UDAgent myUDAgent,
			final String user_name, final EventWebServiceDelegate ewsDelegate) {
        UserSession session = SoftPhoneContent.getSession(myUDAgent.getLDAPUserId());

		// First, register with EWS
		if (!ewsDelegate.register(myUDAgent)) {

			sendLoginFailed("EventGet:Failed to register with EWS", -2, "EVENT_LOGIN_FAILED [-2]", user_name);
			return false;
		}

		// Now register with the AWS
		try {

			User user = session.getUserInfo();

			UDAgentInfo inAgentInfo = new UDAgentInfo();
			inAgentInfo.setStationId(session.getStation());
			inAgentInfo.setIpAddress(hostAddress());
			log.info("***firstLoadInitialize*******工号：{},所在的工作组ID为：" , myUDAgent.getAgentLoginName());

			if (user != null) {

				inAgentInfo.setAgentWorkgroupID(user.getWorkgroupid());
				inAgentInfo.setAgentType(user.getUsertypemask());
				inAgentInfo.setAgentMbox(user.getEmailaddress());
				inAgentInfo.setAgentWorkgroupID(user.getWorkgroupid());
				inAgentInfo.setAgentRouteAccessID(user.getRouteaccessid());
				inAgentInfo.setPassword(user.getPassword());

				// 在UD 的工作组设置 勾选输入未就绪状态
				// 0 - 输入未就绪状态 1-- 空闲状态
				if (1 == user.getAllowNotReadyFlag()) {
					myUDAgent.setInitState(5);
				}
			}

			log.info("userName={}, EventGet: Before Register:1: remote_ipaddress={}",user_name,hostAddress());

			log.info("EventGet:before Register:myUDAgent:LDAPUserId="
					+ myUDAgent.getLDAPUserId() + ", username="
					+ myUDAgent.getAgentLoginName());

            AgentWebService msgDelegate = session.getMsgDelegate();
			msgDelegate.register(myUDAgent, inAgentInfo);

			myUDAgent.setInitState(0);

			return true; // success
		} catch (AgentPortalException ape) {
			log.error(
					"firstLoadInitialize() Exception, userName=" + user_name);

			// if the csl link is down, force a return to the logon page,
			// treat separately from a dual-logon type of failure
			if (ape.getFaultString()
					.equals("The csl link between CC/ASM and Agent Web Service is down.")) {
				sendLoginFailed("badcsllink", -5, "EVENT_CSL_LINK_DOWN [-5]",
						user_name);
				// logonBean.setUser_LoggedOut(LogonBean.NOT_LOGGED_OUT);
			} else {
				sendLoginFailed(
						"EventGet:AgentPortalException=" + ape.toString(), -2,
						"EVENT_LOGIN_FAILED [-2]", user_name);
			}
		} catch (Exception ee) {
            log.error("异常信息："+ee.getMessage());
            log.error("firstLoadInitialize() Exception, userName=" + user_name + " Exception: ");
//			phone.setFailCode(219);
//			phone.setFailReason(user_name+" 工号已登录");

			sendLoginFailed("EventGet:Exception=" + ee.toString(), -2,
					"EVENT_LOGIN_FAILED [-2]", user_name);
		}

		return false;
	}

	private String hostAddress() {
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			return ia.getHostAddress();
		} catch (Exception e) {
			log.error("hostAddress:失败");
		}
		return null;
	}

	private void sendLoginFailed(final String errMsg, final int msgType,
			final String msgDesc, final String user_name) {

		// phone.setFailReason(errorEscape(localizedFailReason));
		//phone.notifyListeners(EventType.EVENT_ERROR);
		try {

		} catch (RuntimeException ee) {
			log.error( "userName=" + user_name + ", RuntimeException sending LOGIN_FAILED: ");
		}
	}

	private String errorEscape(final String original) {
		return original.replaceAll("'", "&#39;").replaceAll("\n", "")
				.replaceAll("\r", "");
	}


	public void getScreenPop(final ScreenPopBean screenPopBean,
			final String user_name, final String whichRequest) {
		// For an M3 call, use the original service ID. Go to PWS to get the
		// service name.
		if (screenPopBean.getCallfield_CallType() == 6) {
			int originalServiceID = screenPopBean
					.getCallfield_OriginalServiceID();
			if (originalServiceID > 0) {
				String originalServiceName = getServiceName(originalServiceID,
						user_name);
				// screenPopBean.setCallfield_ServiceID(originalServiceID);
				// //CQ00235627
				screenPopBean.setCallfield_ServiceName(originalServiceName);
				log.info("userName=" + user_name + ", EventGet:"
						+ whichRequest + ":M3 call -- using OriginalServiceID "
						+ originalServiceID + " with service name "
						+ originalServiceName);
			} else {
				log.info("userName=" + user_name + ", EventGet:"
						+ whichRequest
						+ ":DID call -- using OriginalServiceID "
						+ originalServiceID + " with no service name ");
			}
		}

		String logStart = "userName=" + user_name + ",    EventGet:"
				+ whichRequest + ":";

		String serviceName = screenPopBean.getCallfield_ServiceName();
		if (serviceName.length() == 0) {
            log.debug(logStart + "No Service Name!!!");
		} else {
            log.debug(logStart + "Service Name=" + serviceName);
		}

		//phone.addSPop(phone.getCurrentPane(), screenPopBean);
		//phone.setHaveScreenPop(true);

	}

	private String getServiceName(final int serviceID, final String user_name) {
		try {
			UDInfo inInfo = new UDInfo();
			inInfo.setListType(ListType.GET_SERVICE);
			inInfo.setServiceId(serviceID);

//			AgentWebService msgDelegate = (AgentWebService) phone
//					.getBeanFromMap(AgentWebService.class.getName());
//			UDAgent myUDAgent = phone.inAgent;
//			Service outService = (Service) msgDelegate.getInfo(myUDAgent,
//					inInfo);
			//return outService.getServicec();
		} catch (AgentPortalException ape) {
			log.error( "userName=" + user_name
					+ ", getServiceName: AgentPortalException", ape);
		} catch (Exception ape) {
            log.error("userName=" + user_name
					+ ", getServiceName: RemoteException", ape);
		}

		return "";
	}

	private String agentStats(int state) {
		StringBuffer agentStats = new StringBuffer("agentStats=");
		switch (state) {
		case 0:
			agentStats.append("登录");
			break;

		case 1:
			agentStats.append("空闲");
			break;

		case 2:
			agentStats.append("激活/接通");
			break;

		case 3:
			agentStats.append("预览");
			break;

		case 4:
			agentStats.append("注解/后处理");
			break;

		case 5:
			agentStats.append("未就绪");
			break;

		case 6:
			agentStats.append("保持");
			break;

		case 7:
			agentStats.append("系统等待");
			break;

		case 8:
			agentStats.append("管理等待");
			break;

		case 9:
			agentStats.append("挂断等待");
			break;

		case 10:
			agentStats.append("拨号中");
			break;

		case 11:
			agentStats.append("咨询");
			break;

		case 12:
			agentStats.append("会议");
			break;

		case 13:
			agentStats.append("会议");
			break;

		case 14:
			agentStats.append("内部通话");
			break;

		default:
			break;
		}

		return agentStats.toString();
	}

}
