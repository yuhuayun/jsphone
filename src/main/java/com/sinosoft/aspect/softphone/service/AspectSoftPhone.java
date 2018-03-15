package com.sinosoft.aspect.softphone.service;


import com.sinosoft.aspect.softphone.caches.ServicesCache;
import com.sinosoft.aspect.softphone.facade.SoftPhoneContent;
import com.sinosoft.aspect.softphone.facade.SoftPhoneFacade;
import com.sinosoft.aspect.softphone.facade.UserSession;
import com.sinosoft.aspect.softphone.logging.AgentServices;
import com.sinosoft.aspect.softphone.soap.agent.AgentPortalException;
import com.sinosoft.aspect.softphone.soap.agent.AgentWebService;
import com.sinosoft.aspect.softphone.soap.agent.Client.*;
import com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.Dispositions;
import com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.StatusReasons;
import com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/***
 * 软电话接口实现
 *
 * @author Administrator
 *
 */
@Slf4j
@Component
public class AspectSoftPhone extends AbstractSoftPhone implements SoftPhoneFacade {


	protected ServicesCache serviceManager;

	public String login(String agentId, String password, String number) {
		UserSession user = new UserSession();
		user.setAgentId(agentId);
		user.setPassword(password);
		user.setStation(number);
		user.setAni(number);

		String flag = getPortal(agentId);
		if("fail".equals(flag)){
			return "010:连接服务器异常";
		}

		String result = "000";
		AgentWebService agentWebService = super.getAgentWebService(agentId);

		TokenBean token = new TokenBean();
		token.initialize();

		if (!token.isInitialized()) {
			token.initialize();
		}

		UDAgent inAgent = new UDAgent();
		inAgent.setLDAPUserId(agentId);
		inAgent.setAgentLoginName(agentId);

		// 添加日志功能
        AgentWebService msgDelegate = new AgentServices(agentWebService, inAgent);
		try {
			// 获取用户信息
			UDInfo info = new UDInfo();
			info.setListType(ListType.GET_LOGIN_USER);

			User outUser;
			try {
				outUser = (User) msgDelegate.getInfo(inAgent, info);
				if (outUser != null) {
					log.info("outUser.....{}",outUser);
					log.info("获取到的用户信息index：{}", outUser.getAgentindex());
					log.info("获取到的用户信息id：{}" ,outUser.getUserid());
					log.info("获取到的用户的工作组id：{}",outUser.getWorkgroupid());
					log.info("工作组id：{}",outUser.getWorkgroupid());
				} else {
					log.info("获取到的用户信息为空！");
				}
			} catch (Exception e) {
				log.error("获取用户异常信息："+e.getMessage());
				result = "010:连接服务器异常";
				return result;
			}

			if (outUser != null) {

				// 坐席角色 必须为 1 （座席）、2 （主管）或3 （带有电话的主管）
				inAgent.setClientRole(outUser.getUsertypemask());
				inAgent.setAgentIndex(outUser.getAgentindex());
				// 在UD 的工作组设置 勾选输入未就绪状态
				// 0 - 输入未就绪状态 1-- 空闲状态
				if (1 == outUser.getAllowNotReadyFlag()) {
					inAgent.setInitState(5);
				}
			}

			inAgent.setServerType(SERVER_TYPE);

			UDAgentInfo inAgentInfo = new UDAgentInfo();
			inAgentInfo.setAgentLoginName(agentId);
			inAgentInfo.setPassword(password);
			inAgentInfo.setStationId(number);
			log.info("****AspectSoftPhone******工号：" + agentId + "所在的工作组ID为："+ outUser.getWorkgroupid());
			inAgentInfo.setAgentWorkgroupID(outUser.getWorkgroupid());

			// 验证用户
			msgDelegate.authenticate(inAgent, inAgentInfo);

			user.setUserInfo(outUser);
			user.setToken(token);
			user.setUdAgent(inAgent);

            EventWebServiceDelegate eventDelegate = new EventWebServiceDelegate(agentId);
            EventGetThreadGate eventGetThread = new EventGetThreadGate(eventDelegate, inAgent, token, this, serviceManager);
            eventGetThread.start();

            user.setEventGetThread(eventGetThread);

            user.setMsgDelegate(msgDelegate);
            SoftPhoneContent.addSession(agentId,user);

		} catch (AgentPortalException e) {
			log.info("login Exception Message："+e.getMessage());
			result = "011:其他错误";
		} catch (Exception e) {
			log.info("login Exception Message："+e.getMessage());
			result = "008:登录校验失败，请检查登录的工号、密码";
			if("Connection timed out: connect".equals(e.getMessage()) || "No route to host: connect".equals(e.getMessage())){
				result = "010:连接服务器异常";
			}
		}
		return result;
	}

	public String logout(String agentId) {
		String result = "000";

        UserSession userSession = SoftPhoneContent.getSession(agentId);
		try {

			UDInfo udInfo = new UDInfo();
			udInfo.setListType(ListType.GET_STATUS_REASONS);
			udInfo.setStatusReasonsType(20);

			UDReason reason = new UDReason();
			reason.setReasonId(-99); // panic logout

			AgentWebService msgDelegate = userSession.getMsgDelegate();
			msgDelegate.requestLogout(userSession.getUdAgent(), reason);
			msgDelegate.unRegister(userSession.getUdAgent());

			log.info("坐席工号：{}, 执行 logout .",agentId);
		} catch (AgentPortalException e) {
			log.error("坐席工号：{}, 执行 logout ,出现异常！{}",agentId,e.getMessage());
			result = "003:其它错误";
		}finally {
            userSession.getEventGetThread().setStop(true);
			SoftPhoneContent.remove(agentId);
		}

		return result;
	}

	public String dialCall(String agentId, String number) throws IOException {
		String  result = "000";

        UserSession session = SoftPhoneContent.getSession(agentId);

		if(null == number || "".equals(number)){
			return result="005:外拨号码为空，外拨失败";
		}else if(session.getStation().equals(number)){
			return result="002:外呼号码与分机号相同";
		}

		// 外拨号码是坐席工号
        // 判断坐席是否在线
		boolean isUser = hasUser(number);
		if(isUser){
			log.info("拨打坐席电话：" + number);
			return dial(number);
		}else{
			 //不是坐席工号都可以视为外部号码
			try {
				log.info("拨打外线电话：" + number);
				return dial(agentId,number);
			} catch (Exception e) {
				result="005:其它错误";
				if("Connection timed out: connect".equals(e.getMessage()) || "No route to host: connect".equals(e.getMessage())){
					result = "004:连接服务器异常";
				}
				return result;
			}

			}
	}

	public String releaseCall(String agentId) {
		// 平安专用 ,在会议中挂断自己，把电话转接给会议方
		UserSession session = SoftPhoneContent.getSession(agentId);
		if (session.isConference()) {
			log.info("挂断会议，转接过去");
			return conferenceTransfer(agentId);
		}

		// 平安专用,在咨询中优先挂断咨询方，后挂断客户
		if (session.isConsult()) {
			log.info("挂断咨询");
			return hangupConsult(agentId);
		} else {
			return hangup(agentId);
		}
	}

	public String hangupConsult(String agentId) {
		String result = "000";
		UserSession session = SoftPhoneContent.getSession(agentId);

		try {
			AgentWebService msgDelegate = session.getMsgDelegate();
			msgDelegate.hangup(session.getUdAgent(), new UDParm(session.getConsultCallId()));
		} catch (IOException se) {

			log.error("userName=" + session.getUdAgent().getAgentLoginName() + ","
					+ "hangupConsult:Exception");
			result = "002:其它错误";
		}
		return result;
	}

	public String hangup(String agentId) {

		String result = "000";
		UserSession session = SoftPhoneContent.getSession(agentId);

		UDParm inParm = new UDParm();
		inParm.setCallId(session.getCallId());

		try {
			AgentWebService msgDelegate = session.getMsgDelegate();
			msgDelegate.hangup(session.getUdAgent(), inParm);
		} catch (Exception se) {
			log.error("userName=" + session.getUdAgent().getAgentLoginName() + ","
					+ "HangupAction:Exception"+se.getMessage());
			result = "002:其它错误";
		}
		log.info("*********the hangup() method return message is:"+result);
		return result;
	}

	public String acwFinish(String agentId) {

		String result = "000";
		UserSession session = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = session.getMsgDelegate();

		int disID = 0;
		boolean callBackFlag = false;

		int iType = 0;
		String sDisposition = "";
		boolean wrapRequiredFlag = true;


		UDInfo inInfo = new UDInfo();
		inInfo.setListType(ListType.GET_DISPOSITIONS);
		inInfo.setServiceId(session.getServiceID());

		Object[] disps = null;
		try {
			Object obj = msgDelegate.getInfo(session.getUdAgent(), inInfo);
			if(obj != null){
				disps = (Object[])obj;
			}

		} catch (Exception se) {
			se.printStackTrace();
			log.error( "userName=" + session.getUdAgent().getAgentLoginName() + ","
					+ "CallOutcome:getInfo:Exception");
			return "001:连接服务器异常";
		}

		if (disps != null) {
			log.info(">>>>>>>>>>>>>>  设置结束后处理  = " + disps.length);
			for (int i = 0; i < disps.length; i++) {
				Dispositions disp = (Dispositions) disps[i];
				int id = disp.getDispid();
				String code = disp.getCode();

				if (!(code.equals(sDisposition)))
					continue;
				disID = id;
				break;
			}
		}


		UDDisposition inDisp = new UDDisposition();
		inDisp.setCallBackFlag(callBackFlag);
		inDisp.setCallId(session.getCallId());
		inDisp.setIDisposition(disID);
		inDisp.setIType(iType);
		inDisp.setNextCallId(0);
		inDisp.setSaleFlag(false);
		inDisp.setSDisposition(sDisposition);
		inDisp.setWrapRequiredFlag(wrapRequiredFlag);


		inDisp.setUpdatedCallInfoUserDefined(getEmptyUserDefinedData());
		try {

			msgDelegate.callOutcome(session.getUdAgent(), inDisp);

		} catch (Exception se) {
			se.printStackTrace();
			log.error( "userName=" + session.getUdAgent().getAgentLoginName() + ","
					+ "CallOutcome:callOutcome:Exception");
			result = "002:其他错误";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "001:连接服务器异常";
			}
		}

		return result;
	}


	public String answer(String agentId) {
		String result = "000";
		UserSession session = SoftPhoneContent.getSession(agentId);
		try {

			UDAnswerCall response = new UDAnswerCall();
			response.setAgentIndex(session.getUdAgent().getAgentIndex());
			response.setCallID(session.getCallId());

			// 0 = Accept the call; 1 = Reject the call
			response.setResult(0);

			AgentWebService msgDelegate = session.getMsgDelegate();
			msgDelegate.answerCall(session.getUdAgent(), response);

		} catch (AgentPortalException se) {
			log.error("userName={},CallOutcomeAction:callOutcome:Exception",session.getUdAgent().getAgentLoginName());
			result = "002:其他错误";
		}

		return result;
	}

	public String sayBusy(String agentId,String reason) {
		boolean toParkState = true; // 暂放
		int reasonId = 0;
		return requestUnavailable(agentId,toParkState, reasonId);
	}

	public String sayIdle(String agentId) {
		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);

		try {
			AgentWebService msgDelegate = userSession.getMsgDelegate();
			msgDelegate.available(userSession.getUdAgent());
		} catch (Exception se) {
			log.error("userName=" + userSession.getUdAgent().getAgentLoginName() + ","
					+ "Available_Exception:"+se.getMessage());
			result = "001:连接服务器异常";
		}
		return result;
	}

	public String secondDial(String agentId, String number) {
		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		try {

			UDDigits inDigits = new UDDigits();
			inDigits.setCallId(userSession.getCallId());
			inDigits.setDigits(number);

			msgDelegate.playDigits(userSession.getUdAgent(), inDigits);
		} catch (Exception se) {
			log.error( "userName=" + userSession.getUdAgent().getAgentLoginName()
					+ "," + "playDigits  :Exception"+se.getMessage());
			result = "002:其它错误";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "001:连接服务器异常";
			}
		}
		log.info("*********the playDigits Method retrun message is:"+result);
		return result;
	}


	/**
	 * 转接IVR
	 * <p>
	 * 原transfer接口 转接类型 transferWay 中，1：转座席、2：转技能组、3：转外部号码；<br>
	 * 转接模式 transferMode 中，1：释放转、2：成功转<br>
	 * 具体到转IVR，transferWay传入2<br>
	 * transferMode 如果转接后要求转回来，传2，如验密；如不要求转回来，传1，如满意度
	 * <p>
	 *
	 * */
	public String transfer(String agentId, String number, String transferWay,
			String transferMode) {
		log.info("pingan>>transway :" + transferWay);
		log.info("pingan>>transferMode :" + transferMode);

		log.info("ivr编号：" + number);
		log.info("transferWay编号：" + transferWay);
		log.info("transferMode编号：" + transferMode);

		String result = "000";

		try {

			UserSession session = SoftPhoneContent.getSession(agentId);

			// 开始设置随路数据
			UDTransfer udTransfer = new UDTransfer();
			udTransfer.setServiceID(Integer.parseInt(number));
			udTransfer.setOriginalCallID(session.getCallId());
			udTransfer.setTransferType(TransferType.BLIND_TRANSFER);

			udTransfer.setCallInfoUserDefined(getEmptyUserDefinedData());

			// 把电话转接给M3服务
			AgentWebService msgDelegate = session.getMsgDelegate();
			msgDelegate.transfer(session.getUdAgent(), udTransfer);
		} catch (Exception e) {
			log.info(e.getMessage());
			result = "004:连接服务器异常";
		}

		return result;
	}


	public String singleStepTransfer(String agentId, String number,
			String transferWay) {
		String result = "000";

		UserSession session = SoftPhoneContent.getSession(agentId);

		boolean loggedIn = isPhone(number); // 判断坐席是否在线
		if (!loggedIn) {// 外拨号码是坐席工号
			log.info("转接给工号：" + number);
			UDConsultIn xferParams = new UDConsultIn();
			xferParams.setCallID(session.getCallId());
			xferParams.setConsultType(ConsultType.INTERNAL);
			// xferParams.setServiceID(serviceID);
			xferParams.setUserID(number);
			xferParams.setBlindXferFlag(1);


			xferParams.setUserDefinedData(getEmptyUserDefinedData());

			try {

				AgentWebService msgDelegate = session.getMsgDelegate();
				msgDelegate.consult(session.getUdAgent(), xferParams);

			} catch (Exception se) {
				result = "004:其他错误";
				log.error("userName=" + session.getUdAgent().getAgentLoginName() + ","
						+ "singleStepTransfer:Exception  "+se.getMessage());
				if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
					result = "001:连接服务器异常";
				}

			}
		} else {
			// 无通知转接外线
			log.info("不是工号转接给外线：" + number);
			UDConsultIn xferParams = new UDConsultIn();
			xferParams.setCallID(session.getCallId());
			xferParams.setConsultType(ConsultType.EXTERNAL);
			// xferParams.setServiceID(serviceID);
			xferParams.setPhoneNumber(number);
			xferParams.setBlindXferFlag(1);


			xferParams.setUserDefinedData(getEmptyUserDefinedData());
			try {
				AgentWebService msgDelegate = session.getMsgDelegate();

				msgDelegate.consult(session.getUdAgent(), xferParams);
			} catch (Exception se) {

				log.error("userName=" + session.getUdAgent().getAgentLoginName() + ","
						+ "singleStepTransfer:Exception");
				result = "004:其他错误";
				if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
					result = "001:连接服务器异常";
				}
			}
		}

		log.info("*******the singleStepTransfer Method return message is:"+result);
		return result;
	}

	public String completeTransfer(String agentId, String number,
			String transferWay, String transferMode) {
		String result = "000";
		UserSession session = SoftPhoneContent.getSession(agentId);

			UDTransfer xferParams = new UDTransfer();
			xferParams.setOriginalCallID(session.getCallId());
			xferParams.setThirdParyCallID(session.getConsultCallId());
			xferParams.setServiceID(session.getServiceID());
			xferParams.setTransferType(TransferType.WARM_TRANSFER);


			xferParams.setCallInfoUserDefined(getEmptyUserDefinedData());
			try {

				AgentWebService msgDelegate = session.getMsgDelegate();
				msgDelegate.transfer(session.getUdAgent(), xferParams);
			} catch (Exception se) {
				result = "004:其他错误";
			}

		log.info("***********the method completeTransfer return resutl:"+result);
		return result;
	}

	public String doubleStepConference(String agentId, String number,
			String transferWay) {
		return doubleStepTransfer(agentId, number, transferWay);
	}


	public String completeConference(String agentId) {
		return conference(agentId);
	}

	@Override
	public String supervise(String monitored, String monitoredAgentId) {
		return null;
	}

	@Override
	public String insert(String monitored, String monitoredAgentId) {
		return null;
	}

	@Override
	public String coaching(String agentId, String monitoredAgentId) {
		return null;
	}

	/**
	 * 会议中转接给会议方
	 *
	 * @param agentId 操作坐席
	 * @return 操作结果
	 */
	public String conferenceTransfer(String agentId) {
		String result = "000";

		UserSession session = SoftPhoneContent.getSession(agentId);

		try {

			UDTransfer xferParams = new UDTransfer();
			xferParams.setOriginalCallID(session.getCallId());
			xferParams.setThirdParyCallID(session.getConsultCallId());
			xferParams.setConferenceCallID(session.getConferenceCallId());
			xferParams.setServiceID(session.getServiceID());
			xferParams.setTransferType(TransferType.WARM_TRANSFER);

			xferParams.setCallInfoUserDefined(getEmptyUserDefinedData());

			AgentWebService msgDelegate = session.getMsgDelegate();
			msgDelegate.transfer(session.getUdAgent(), xferParams);

		} catch (Exception se) {
			result = "003:其他错误";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "002:连接服务器异常";
			}
		}
		return result;
	}


	public String addSupervise(String monitored, String monitoredAgentId,
			String monitoredNumber) {
		int monitoringID = 0;
		return monitor(monitoredAgentId, monitoringID, MonitorType.MON_SILENT);
	}

	public String quitSupervise(String monitored, String monitoredAgentId,
			String monitoredNumber) {
		int monitoringID = 0;
		return monitor(monitoredAgentId, monitoringID,
				MonitorType.MON_SILENT_STOP);
	}

	public String addInsert(String monitored, String monitoredAgentId,
			String monitoredNumber) {
		int monitoringID = 0;
		return monitor(monitoredAgentId, monitoringID, MonitorType.MON_BARGEIN);
	}

	public String quitInsert(String monitored, String monitoredAgentId,
			String monitoredNumber) {
		int monitoringID = 0;
		return monitor(monitoredAgentId, monitoringID,
				MonitorType.MON_BARGEIN_STOP);
	}

	// 管理功能中的指导
	public String addCoaching(String monitoredAgentId) {
		int monitoringID = 0;
		return monitor(monitoredAgentId, monitoringID, MonitorType.MON_COACHING);
	}

	// 管理功能中的退出指导
	public String quitCoaching(String monitoredAgentId) {
		int monitoringID = 0;
		return monitor(monitoredAgentId, monitoringID,
				MonitorType.MON_COACHING_STOP);
	}

	private String monitor(String agentId, int monitoringID, int requestType) {

		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		try {

			//TODO 坐席分机号获取需要进一步处理
			//String monitoringStationID = CMSClientUtil.getAgentStation(agentId);
			//log.info("需要监控的坐席 " + agentId + " 分机号是: " + monitoringStationID);


			String agentStationID = userSession.getStation();
//			String monitoringStationID = outUser.getStation();

			UDMonitor inMonitor = new UDMonitor();

//			inMonitor.setAgentID(agentID);
			inMonitor.setAgentIndex(userSession.getUdAgent().getAgentIndex());
			inMonitor.setAgentStationID(agentStationID);
			inMonitor.setMonitoringID(monitoringID);
			//inMonitor.setMonitoringStationID(monitoringStationID.trim());
			inMonitor.setRequestType(requestType);

			msgDelegate.monitor(userSession.getUdAgent(), inMonitor);

			log.info("===========end=======monitor=========monitor  ---------------- ");
		} catch (Exception se) {
			log.info("userName=" + userSession + ","
					+ "addSupervise:Exception");
			result = "003:其它错误";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "001:连接服务器异常";
			}
		}
		return result;
	}

	public String doubleStepTransfer(String agentId, String phoneNumber, String transferWay) {

		String result = "000";
		switch (transferWay){

			//转坐席
			case "0":
				result = consultUser(agentId,phoneNumber);
				break;

			//1-转外部号码
			case "1":
				result = consultPhone(agentId,phoneNumber);
				break;

			//2-转服务
			case "2":
				result = consultService(agentId,phoneNumber);
				break;
		}

		return result;
	}

	public String requestUnavailable(String agentId,boolean toParkState, int reasonId) {
		String result = "000";
		UDInfo udInfo = new UDInfo();
		udInfo.setListType(ListType.GET_STATUS_REASONS);// GET_STATUS_REASONS;
		udInfo.setStatusReasonsType(5);// NotReady Reasons

		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();


		try {
			String reasonDescription = "";
			Object obj = msgDelegate.getInfo(userSession.getUdAgent(), udInfo);
			if(obj != null){
				Object[] objs = (Object[]) obj;
				for (int i = 0; i < objs.length; i++) {
					StatusReasons reasons = (StatusReasons) objs[i];
					if (reasonId == 0) {
						reasonId = reasons.getReasonid();
						reasonDescription = reasons.getDescription();
					}
					break;
				}
			}

			UDReason inReason = new UDReason();
			inReason.setReasonDescription(reasonDescription);
			inReason.setReasonId(reasonId);
			inReason.setToParkState(toParkState);// 是否暂放

			msgDelegate.requestUnavailable(userSession.getUdAgent(), inReason);

		} catch (AgentPortalException se) {
			result = "001：连接服务器异常";
			return result;
		} catch (Exception e) {
			result = "002:其他错误";
			if("Connection timed out: connect".equals(e.getMessage()) || "No route to host: connect".equals(e.getMessage())){
				result = "001:连接服务器异常";
			}
		}

		return result;
	}

	public String conference(String agentId) {
		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		try {

			UDConferenceIn confParams = new UDConferenceIn();
			confParams.setCallID(userSession.getCallId());
			confParams.setConsultCallID(userSession.getConsultCallId());

			msgDelegate.conference(userSession.getUdAgent(), confParams);
		} catch (Exception e) {
			result = "003:其他错误";
			log.info("userName=" + userSession.getUdAgent().getAgentLoginName() + ","
					+ "conference:Exception");
			if("Connection timed out: connect".equals(e.getMessage()) || "No route to host: connect".equals(e.getMessage())){
				result = "002:连接服务器异常";
			}
		}
		log.info("*******the conference Method return message is:"+result);
		return result;
	}

	private String consultPhone(String agentId,String phoneNumber) {
		String result = "000";

		try {

			UDConsultIn udConsultIn = new UDConsultIn();
			udConsultIn.setPhoneNumber(phoneNumber);
			udConsultIn.setConsultType(ConsultType.EXTERNAL);

			consult(agentId,udConsultIn);

		} catch (AgentPortalException se) {
			log.error("agentId={},consultPhone 连接服务器异常", agentId);
			result = "003:连接服务器异常";
		}

		return result;
	}

	private String consultUser(String agentId,String userId) {

		String result = "000";

		try {

			UDConsultIn inConsult = new UDConsultIn();
			inConsult.setUserID(userId);
			inConsult.setConsultType(ConsultType.INTERNAL);

			consult(agentId,inConsult);

		} catch (AgentPortalException se) {
			result = "003:连接服务器异常";
			log.error("agentId={},consultUser 连接服务器异常", agentId);
		}

		return result;
	}

	private String consultService(String agentId,String serviceId) {

		String result = "000";

		try {

			UDConsultIn inConsult = new UDConsultIn();
			inConsult.setServiceID(Integer.valueOf(serviceId));
			inConsult.setConsultType(ConsultType.INTERNAL);

			consult(agentId,inConsult);

		} catch (AgentPortalException se) {
			result = "003:连接服务器异常";
			log.error("agentId={},consultService 连接服务器异常", agentId);
		}

		return result;
	}

	private void consult(String agentId,UDConsultIn inConsult) throws AgentPortalException{
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		inConsult.setBlindXferFlag(0);
		inConsult.setCallID(userSession.getCallId());
		inConsult.setUserDefinedData(getEmptyUserDefinedData());

		msgDelegate.consult(userSession.getUdAgent(), inConsult);
	}

	public String dial(String agentId,String phone) {
		String result = "000";
		int call_id = 1;

		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		UDCall myCall = new UDCall();

		myCall.setServiceID(userSession.getServiceID());
		myCall.setMakeCallType(2);// OUTBOUND
		myCall.setCallID(call_id);
		myCall.setPhoneNumber(phone);

		try {

			msgDelegate.dial(userSession.getUdAgent(), myCall);

		} catch (Exception se) {
			log.error( "userName=" + userSession.getUdAgent().getAgentLoginName()
					+ "," + "dial :Exception");
			result = "005:其它错误";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "004:连接服务器异常";
			}
		}
		return result;
	}

	public String dial(String agentId) {
		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		try {

			UDCall myCall = new UDCall();
			myCall.setServiceID(0);
			myCall.setMakeCallType(1);// INBOUND
			myCall.setUserID(agentId);
			msgDelegate.dial(userSession.getUdAgent(), myCall);
		} catch (Exception se) {
			log.error("userName=" + userSession.getUdAgent().getAgentLoginName()
					+ "," + "dial :Exception");
			result = "005:其它错误";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "004:连接服务器异常";
			}
		}
		log.info("*********the dial Method retrun message is:"+result);
		return result;
	}

	public String hold(String agentId) {
		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		UDParm inParm = new UDParm();
		inParm.setCallId(userSession.getCallId());
		try {
			msgDelegate.hold(userSession.getUdAgent(), inParm);
		} catch (AgentPortalException se) {
			log.error( "userName=" + userSession.getUdAgent().getAgentLoginName()
					+ "," + "hold  :Exception");
			result = "004:保持失败";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "003:连接服务器异常";
			}
		}
		log.info("*********the hold Method retrun message is:"+result);
		return result;
	}

	public String unHold(String agentId) {
		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		try {

			UDParm inParm = new UDParm();
			inParm.setCallId(userSession.getCallId());
			msgDelegate.retrieveHold(userSession.getUdAgent(), inParm);

		} catch (Exception se) {
			log.error( "userName=" + userSession.getUdAgent().getAgentLoginName()
					+ "," + "unhold  :Exception");
			result = "003:取消保持失败";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "002:连接服务器异常";
			}
		}
		log.info("*********the unHold Method retrun message is:"+result);
		return result;
	}

	public String answerCall(String agentId,int reasonId) {
		String result = "000";
		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		try {

			UDAnswerCall response = new UDAnswerCall();
			response.setAgentIndex(userSession.getUdAgent().getAgentIndex());
			response.setCallID(userSession.getCallId());

			if(reasonId > 0){//当系统配置拒接原因是执行
				UDInfo udInfo = new UDInfo();
				udInfo.setListType(ListType.GET_STATUS_REASONS);// GET_STATUS_REASONS;
				udInfo.setStatusReasonsType(29);// NotReady Reasons

				Object[] objs = (Object[]) msgDelegate.getInfo(userSession.getUdAgent(), udInfo);
				for (int i = 0; i < objs.length; i++) {
					StatusReasons reasons = (StatusReasons) objs[i];
					int rId = reasons.getReasonid();
					if (rId == reasonId) {
						response.setRejectReason(reasonId);
						break;
					}
				}
			}

			// 0 = Accept the call; 1 = Reject the call
			response.setResult(1);
			msgDelegate.answerCall(userSession.getUdAgent(), response);

		} catch (AgentPortalException e) {
			result = "001:连接服务器异常";
			return result;
		} catch (Exception se) {
			log.error( "userName=" + userSession.getUdAgent().getAgentLoginName()
					+ "," + "answer :Exception");
		}
		return result;
	}

	public String sayBusy(String agentId,boolean toParkState, int reasonId) {
		String result = "000";

		UserSession session = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = session.getMsgDelegate();


		UDInfo udInfo = new UDInfo();
		udInfo.setListType(ListType.GET_STATUS_REASONS);// GET_STATUS_REASONS;
		udInfo.setStatusReasonsType(5);// NotReady Reasons
		try {
			Object[] objs = (Object[]) msgDelegate.getInfo(session.getUdAgent(), udInfo);
			String reasonDescription = "";
			for (int i = 0; i < objs.length; i++) {
				StatusReasons reasons = (StatusReasons) objs[i];
				if (reasonId == 0) {
					reasonId = reasons.getReasonid();
					reasonDescription = reasons.getDescription();
				}
				break;
			}

			UDReason inReason = new UDReason();
			inReason.setReasonDescription(reasonDescription);
			inReason.setReasonId(reasonId);
			inReason.setToParkState(toParkState);// 是否暂放

			msgDelegate.requestUnavailable(session.getUdAgent(), inReason);
		} catch (AgentPortalException e) {
			result = "001:连接服务器异常";
			return result;
		} catch (Exception se) {
			result = "002:其他错误";
			if("Connection timed out: connect".equals(se.getMessage()) || "No route to host: connect".equals(se.getMessage())){
				result = "003:连接服务器异常";
			}
		}

		return result;
	}

	public void record(String agentId,int requestType) {

		UserSession userSession = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = userSession.getMsgDelegate();

		String recorderStation = userSession.getStation();

		Map calldata = new HashMap();
		//getSPop0(currentPane);

		// calldata.put("ani", getSPop0String("Callfield_ANI")); //外部呼入时有值
		// calldata.put("dnis", getSPop0String("Callfield_DNIS"));//外部呼入时有值
		int callID = -1;
		int serviceId;
		try {
			serviceId = getServiceID(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return;
		}
		ScreenPopBean screenpop = new ScreenPopBean();
		String phone1 = screenpop.getCallfield_PhoneNumber();

		if ("".equalsIgnoreCase(phone1)) {
			//phone1 = super.getPhoneNumber();
		}

//		String recorderId = inAgent.getAgentLoginName();
//		UDRecorderData inRecordData = new UDRecorderData();
//		log.info("record:requestType=" + requestType);
//		if (requestType == RecordRequestType.ccpStartRecord) {
//			inRecordData.setAgentStation(getUser_extension());
//			inRecordData.setAgentId(inAgent.getLDAPUserId());
//			inRecordData.setCallID(callID);
//			inRecordData.setServiceId(serviceId);
//			inRecordData.setPhone1(phone1);
//			inRecordData.setRequestType(requestType);
//			inRecordData.setRecorderId(recorderId);
//			inRecordData.setAudio_f(1);
//			inRecordData.setRecorderStation(recorderStation);
//			inRecordData.setRecordingIndex(0);
//			inRecordData.setStatus("Record");
//			log.info("record:requestType=" + requestType + ", agentStation="
//					+ recorderStation + ", agentId=" + agentId + ", callID="
//					+ callID + ", serviceId=" + serviceId + ", phone1="
//					+ phone1 + ", recorderId=" + recorderId + ", audio_f=" + 1
//					+ ", recorderStation=" + recorderStation
//					+ ", recordingIndex=" + 0);
//
//		}

		if (requestType == RecordRequestType.ccpStopRecord) {
			//int seqNumber = this.getRecordingSeq();
//			inRecordData.setSeqNumber(seqNumber);
//			inRecordData.setRequestType(requestType);
//
//			inRecordData.setCallID(userSession.getCallId());

//			log.info("record:requestType=" + requestType + ", seqNumber="
//					+ seqNumber + ", callID=" + callID);

		}

		try {
			//msgDelegate.record(userSession.getUdAgent(), inRecordData);
		} catch (Exception se) {
			log.info("userName=" + userSession.getUdAgent().getAgentLoginName() + ","
					+ "record:Exception");
		}
	}


	public Map getDispositions() {
		Map disp = new HashMap();
		int len = getDispositionLength0();
		for (int i = 0; i > len; i++) {
			String id = getDispId0(i) + "";
			String description = getDispDesc0(i);
			disp.put(id, description);
		}
		return disp;
	}

	/**
	 * 获取状态原因
	 * @param agentId 坐席ID
	 * @param statusReasonsType 原因类型
	 * @return 状态原因列表
	 */
	public List<StatusReasons> getStatusReasons(String agentId, int statusReasonsType) {

		UserSession session = SoftPhoneContent.getSession(agentId);
		AgentWebService msgDelegate = session.getMsgDelegate();

		UDInfo udInfo = new UDInfo();
		udInfo.setListType(ListType.GET_STATUS_REASONS);// GET_STATUS_REASONS;
		udInfo.setStatusReasonsType(statusReasonsType);// NotReady Reasons

		Object[] objs = (Object[]) msgDelegate.getInfo(session.getUdAgent(), udInfo);

		return Stream.of(objs).map(o -> (StatusReasons) o).collect(toList());
	}



}
