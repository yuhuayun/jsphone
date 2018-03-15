package com.sinosoft.aspect.softphone.soap.agent;


import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgent;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgentInfo;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAnswerCall;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDCall;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDConferenceIn;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDConsultIn;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDDigits;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDDisposition;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDInfo;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDMonitor;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDParm;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDReason;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDRecorderData;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDTransfer;

import java.rmi.RemoteException;

/**
 * 软电话服务接口
 *
 * @author wangjunhua
 * @since 1.0
 *
 */
public interface AgentWebService {

	/**
	 * 注册
	 * 此方法用于在座席经过验证后，将座席"登录"到后端系统。这是用户与系统进行联系所调用的第一个方法。
     *
	 * @param udAgent 对象及其相关联的属性唯一标识座席
	 * @param udAgentInfo 对象包含描述座席配置的属性
	 * @throws AgentPortalException 访问服务异常
	 */
	void register(UDAgent udAgent, UDAgentInfo udAgentInfo) throws AgentPortalException;

	/**
	 * 此方法用于接收由座席拥有的呼叫，然后再将该呼叫发给另一个座席控制。
	 * 有问询转接和无通知转接两种转接方式。
	 * @param udAgent 座席
	 * @param udTransfer 此对象包含进行媒体传输所需的信息
	 * @throws AgentPortalException 访问服务异常
	 */
	void transfer(UDAgent udAgent, UDTransfer udTransfer) throws AgentPortalException;

	/**
	 * 示闲
	 *
	 * 如果客户端处于 "未就绪" 或 "不可用" 状态，可调用此方法来发送通知，以表明客户端想要
	 * 在系统中再次成为“活动”状态。
	 * @param udAgent 座席
	 * @throws AgentPortalException 访问服务异常
	 */
	void available(UDAgent udAgent) throws AgentPortalException;

	/**
	 * 用于针对安全 Web 服务对用户进行验证
	 * @param udAgent 坐席
	 * @param udAgentInfo 坐席信息
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void authenticate(UDAgent udAgent, UDAgentInfo udAgentInfo) throws AgentPortalException;

	/**
	 * 管理功能
	 * 此方法用于由系统发起对座席的监控。此方法不应代表座席执行。
	 * 功能包括：监听、指导、干预
	 * @param udAgent 坐席
	 * @param udMonitor 管理员
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void monitor(UDAgent udAgent, UDMonitor udMonitor) throws AgentPortalException;

	/**
	 * 此方法用于通过配置 Web 服务从数据库中检索与座席相关的信息和配置数据，而不必访问 Web服务或了解关于该服务的任何信息。
	 * getInfo 调用将把数据作为通用对象返回，需要客户端在它们所在一端来对对象进行强制类型转换。<br>
	 * 下面描述了返回对象到信息类型的映射。
	 * @param udAgent 座席
	 * @param udInfo 该类包含检索关于座席、服务、服务设置、配置代码和呼叫数据定义所需的信息。
	 * @return 坐席配置信息
	 * @throws AgentPortalException 访问服务异常
	 *
	 * @see com.sinosoft.aspect.softphone.soap.agent.Client.ListType
	 */
	Object getInfo(UDAgent udAgent, UDInfo udInfo) throws AgentPortalException;

	/**
	 * 心跳
	 * 由于 HTTP 会话的无连接特性，座席与 Tomcat 应用程序服务器之间的会话受超时限制。<br>
	 * 如果在5 分钟的超时期限内未接收到请求，将会消除并清除 Tomcat 中的会话。<br>
	 * 应每分钟发送一次此 keepAlive 消息以确保会话在由座席终止前不会被清除。
	 * @param udAgent 座席
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void keepAlive(UDAgent udAgent) throws AgentPortalException,RemoteException;

	/**
	 * 在客户处于“保持”状态时，如果座席想要通过电话咨询另一个座席或主管，则可调用此方法。
	 * 这将允许两个座席之间自由谈话，而不会被客户听到。
	 * @param udAgent 座席
	 * @param udConsultIn 此对象包含与号码或座席建立咨询呼叫所需的信息
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void consult(UDAgent udAgent, UDConsultIn udConsultIn) throws AgentPortalException;

	/**
	 * 在座席想要在自己与另一个座席或主管以及电话线另一端的客户之间进行会议时，可调用此方法。
	 * @param udAgent 座席
	 * @param udConferenceIn 此对象包含用于建立会议呼叫所需的信息
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void conference(UDAgent udAgent, UDConferenceIn udConferenceIn) throws AgentPortalException;

	/**
	 * 当座席与客户正在进行呼叫并且他们想要将呼叫置于“保持”状态时，可调用此方法。
	 * @param udAgent 座席
	 * @param udParm 包含要对其应用下面的相关方法的呼叫代号。
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void hold(UDAgent udAgent, UDParm udParm) throws AgentPortalException;

	/**
	 * 方法用在“接受呼叫”处理期间。“接受呼叫”允许用户在屏幕弹出显示后决定是否想要发起与呼入客户的电话连接。
	 * @param udAgent 座席
	 * @param udAnswerCall 拨号对象
	 * @throws AgentPortalException 访问服务异常
	 */
	void answerCall(UDAgent udAgent, UDAnswerCall udAnswerCall) throws AgentPortalException;

	/**
	 * 当客户端在系统中处于“注解”状态时，可调用 callOutcome 方法。
	 * 之后将发送呼叫结果，其中包含将由系统保存的配置代码。如果未选择配置代码，则应使用缺省的 64 代码。
	 * @param udAgent 座席
	 * @param udDisposition 后处理内容
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void callOutcome(UDAgent udAgent, UDDisposition udDisposition) throws AgentPortalException,RemoteException;

	/**
	 * 在座席想要进行人工外拨呼叫时，可调用此方法。
	 * @param udAgent 坐席信息
	 * @param udCall  呼叫信息
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void dial(UDAgent udAgent, UDCall udCall) throws AgentPortalException;

	/**
	 * 当座席与客户正在进行呼叫并且他们想要挂断呼叫时，可调用此方法。此方法仅应调用一次。
	 * @param udAgent 坐席信息
	 * @param udParm 包含要对其应用下面的相关方法的呼叫代号
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void hangup(UDAgent udAgent, UDParm udParm) throws AgentPortalException,RemoteException;

	/**
	 * 此方法将向后端电话服务器发送单位数字（0-9、#、或 *）。这用于输入将通过电话线发出的数字以用于在 ACD 上回答问题。这用于模拟硬电话的按键面板。
	 * @param udAgent  坐席信息
	 * @param udDigits 此对象包含通知系统将数字传递给电话线所需的信息
	 * @throws AgentPortalException  访问服务异常
	 *
	 */
	void playDigits(UDAgent udAgent, UDDigits udDigits) throws AgentPortalException;

	/**
	 * 此方法用于通知系统注销此座席。除非不存在代表该座席进行的呼叫，否则，通常系统会以 pending 事件进行响应。
	 * @param udAgent 坐席信息
	 * @param udReason 该对象包含座席注销或转为“未就绪”状态的原因。
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void requestLogout(UDAgent udAgent, UDReason udReason) throws AgentPortalException;

	/**
	 * 此方法用于通知系统将此座席置于“未就绪”状态。除非不存在代表该座席进行的呼叫，否则，通常系统会以 pending 事件进行响应。
	 * @param udAgent 坐席信息
	 * @param udReason 原因
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void requestUnavailable(UDAgent udAgent, UDReason udReason) throws AgentPortalException;

	/**
	 * 在客户原来被置于"保持"状态时，此方法用于将客户从"保持"状态恢复。
	 * @param udAgent 座席
	 * @param udParm 随录数据
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void retrieveHold(UDAgent udAgent, UDParm udParm) throws AgentPortalException;

	/**
	 * 应在座席已成功从系统中注销后调用此方法。这会清除座席 Web 服务中任何代表座席实例化的对象。
	 * @param udAgent 坐席信息
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void unRegister(UDAgent udAgent) throws AgentPortalException;

	/**
	 * 此方法用于发起对系统上的语音录音的状态的更改。它用于发起或停止录音。
	 * @param udAgent 座席
	 * @param udRecorderData 录音相关
	 * @throws AgentPortalException 访问服务异常
	 *
	 */
	void record(UDAgent udAgent, UDRecorderData udRecorderData) throws AgentPortalException;

}
