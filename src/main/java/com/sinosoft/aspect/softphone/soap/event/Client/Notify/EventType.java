package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

/**
 * 座席 Web 服务传递给座席的座席事件<br>
 * 以下为事件和它们的代号的列表，列表下面为事件及它们的有效事件返回对象的描述。
 * @author Believe
 *
 */
public class EventType {

	/**
	 * 此事件用于通知座席请求的操作已失败。
	 *
	 * 返回对象:  UDError
	 * @see UDError
	 */
	public static final int EVENT_ERROR = 1;

	/**
	 * 此事件用于通知座席他到网关服务器的音频路径已断开。
	 *
	 * 返回对象:  UDAgentIndex
	 * @see UDAgentIndex
	 */
	public static final int EVENT_PHONEFOUL = 2;

	/**
	 * 此事件用于通知座席电话已摘机。
	 *
	 * 返回对象: UDAgentIndex
	 * @see UDAgentIndex
	 */
	public static final int EVENT_PHONEOFFHOOK = 3;

	/**
	 * 此事件用于通知座席事件中指定的服务的设置已更改。
	 *
	 * 返回对象:  UDUpdateService
	 * @see UDUpdateService
	 */
	public static final int EVENT_UPDATESERVICE = 4;

	/**
	 * 此事件用于通知座席该座席已从字段 ServiceID 中指定的服务中删除。
	 *
	 * 返回对象:  UDRemoveService
	 * @see UDRemoveService
	 */
	public static final int EVENT_REMOVESERVICE = 5;

	/**
	 * 此事件用于通知座席事件中指定的配置代码已更改。
	 *
	 * 返回对象:  UDUpdateDisposition
	 * @see UDUpdateDisposition
	 */
	public static final int EVENT_UPDATEDISPOSITION = 6;

	/**
	 * 此事件用于通知座席事件中指定的配置代码计划已更改。
	 *
	 * 返回对象:  UDUpdateDispositionPlan
	 * @see UDUpdateDispositionPlan
	 */
	public static final int EVENT_UPDATEDISPOSTIONPLAN = 7;

	/**
	 * 此事件用于通知座席它有新语音邮件到达。
	 *
	 * 返回对象:  UDUpdateService
	 * @see UDUpdateService
	 */
	public static final int EVENT_NEWVOICEMAIL = 8;

	/**
	 * 此事件用于通知座席事件中指定的 CallDataDef 已更改。
	 *
	 * 返回对象:  UDUpdateCallDataDef
	 * @see UDUpdateCallDataDef
	 */
	public static final int EVENT_UPDATECALLDATADEF = 9;

	/**
	 * 此事件用于通知座席状态原因已更改。
	 *
	 * 返回对象:  UDAgentIndex
	 * @see UDAgentIndex
	 */
	public static final int EVENT_UPDATEAGENTSTATUSREASONS = 10;

	/**
	 * 此事件用于通知座席它的外部路由已更改。
	 *
	 * 返回对象:  UDExternalRoute
	 * @see UDExternalRoute
	 */
	public static final int EVENT_UPDATEEXTERNALROUTE = 11;

	/**
	 * 此事件用于通知座席 RealTimePort 已更改。
	 *
	 * 返回对象:  UDRealTimePort
	 * @see UDRealTimePort
	 */
	public static final int EVENT_UPDATEREALTIMEPORT = 12;

	/**
	 * 一旦 CenterCord 从座席 Web 服务接收到对座席的注册请求作出响应的 Login 命令，它就会给请求登录的座席指定唯一的座席索引。<br>
     * 从此时起，此座席索引就成为该座席的标识。
	 *
	 * 返回对象:  UDLoginConf
	 * @see UDLoginConf
	 */
	public static final int EVENT_LOGINCONF = 13;

	/**
	 * CenterCord 发送 SystemWait 以通知座席门户网关服务器尚未启动。所有登录请求都将被搁置。<br>
     * 一旦网关服务器启动， CenterCord 就会继续处理登录流程。
	 *
	 * 返回对象:  UDSystemWait
	 * @see UDSystemWait
	 */
	public static final int EVENT_SYSTEMWAIT = 14;

	/**
	 * 此事件用于通知座席 CenterCord 正在将座席登录到系统。
	 *
	 * 返回对象:  UDLoggingIn
	 * @see UDLoggingIn
	 */
	public static final int EVENT_LOGGINGIN = 15;

	/**
	 * 如果 CenterCord 必须将座席登录到电话网关服务器，则它将向座席发送此事件以通知座席他的密码是什么。<br>
	 * 然后座席就会显示此密码以便可通过电话输入此密码以用于标识。
	 *
	 * 返回对象:  UDPasscode
	 * @see UDPasscode
	 */
	public static final int EVENT_PASSCODE = 16;

	/**
	 * 一旦 CenterCord 成功将座席登录到系统，它就会发送此事件以通知座席当前与他相关联的服务。<br>
	 *  对于座席参与的每个服务， CenterCord 都会发送一个 NewService。
	 *
	 * 返回对象:  UDNewService
	 * @see UDNewService
	 */
	public static final int EVENT_NEWSERVICE = 17;

	/**
	 * 此事件用于通知座席他已成功登录到系统。
	 *
	 * 返回对象:  UDLogin
	 * @see UDLogin
	 */
	public static final int EVENT_LOGIN = 18;

	/**
	 * 此事件用于通知座席他处于 "空闲" 状态且已准备好工作。
	 *
	 * 返回对象:   UDIdle
	 * @see UDIdle
	 */
	public static final int EVENT_IDLE = 19;

	/**
	 * 座席已发送命令以请求从系统中注销。座席在发送 Login 命令后，可在任何时候发送此命令。<br>
	 * 如果他的状态不是进行注销的正确状态（例如，座席正在呼叫中处于"活动"状态），则请求将被搁置，并且 CenterCord 将向该座席发送事件 LogoutPenging。<br>
	 * 座席终止当前呼叫后，CenterCord 就会注销该座席。<br>
	 * 如果座席处于正确的状态，则将接收到 logout 事件。
	 *
	 * 返回对象:   UDLogout
	 * @see UDLogout
	 */
	public static final int EVENT_LOGOUT = 20;

	/**
	 * 此事件用于通知座席在他的工作站上存在响铃呼叫或可通过电子邮件或聊天界面获得的呼叫。<br>
	 * 它将提供关于该呼叫的所有信息，包括呼叫索引、呼叫类型、ANI、DNIS 和呼叫数据。<br>
     * CenterCord 然后将为座席应答该呼叫。<br>
     * 字段 statusReason 指定呼叫的类型。
	 *
	 * 返回对象:  UDScreenPop
	 * @see UDScreenPop
	 */
	public static final int EVENT_SCREENPOP = 21;

	/**
	 * 此事件用于通知座席列在字段 nCallID 中的呼叫处于 "活动" 状态。在应答了呼入呼叫，或恢复了处于 "保持" 状态的呼叫时，可能发生此情况。<br>
	 * 参数 statusReason 指定座席的状态。<br>
	 * 座席可对单个呼叫处于 "活动" 状态。<br>
	 * 或者，他也可以对多个呼叫处于"咨询" 或 "会议" 状态。
	 *
	 * 返回对象:  UDActive
	 * @see UDActive
	 */
	public static final int EVENT_ACTIVE = 22;

	/**
	 * 此事件用于通知座席列在字段 CallID 中的呼叫处于 "保持" 状态。
	 *
	 * 返回对象:  UDHeld
	 * @see UDHeld
	 */
	public static final int EVENT_HOLD = 23;

	/**
	 * 此事件用于通知座席呼叫已结束，他处于 "注解" 状态。在呼叫结束后，即使呼叫不需要配置代码， CenterCord 也是总会将座席置于"注解" (后处理)状态。<br>
	 * 要由座席来发送 Disposition 命令来终止呼叫。<br>
	 * 在不需要配置代码时，将发送 (64) 缺省配置代码。
	 *
	 * 返回对象:  UDWrap
	 * @see UDWrap
	 */
	public static final int EVENT_WRAP = 24;

	/**
	 *
	 * 此事件用于通知座席 Dial 或 Consult 请求正在处理过程中。statusReason 指定呼叫的类型。<br>
	 * 如果类型为 CCProMediaInternalConsult，则此事件为 ccpConsultIndex 和 ccpDialing 的组合，如在 AMP 座席系统中那样。
	 *
	 * 返回对象:  UDDialing
	 * @see UDDialing
	 */
	public static final int EVENT_DIALING = 25;

	/**
	 *
	 * 此事件用于通知座席字段 nCallID 中指定的呼叫已结束。参数 statusReason 会通知座席他的下一个状态。<br>
	 * 在呼叫结束后，座席可能为以下状态：<br>
	 * 			• "注解"状态 (AgWrap)：在所有呼叫都结束后，座席将进入"注解"状态。此后，座席将立刻接收到 EvtWrap 事件。<br>
	 * 			• "空闲"状态 (AgIdle)：呼叫为不需要注解的内部呼叫（CCProMediaInternalManual 或 CCProMediaInternalConsult）。此后，座席将立刻接收到 EvtIdle 事件。<br>
	 * 			• "活动"状态 (AgActive)：咨询或会议中的一个呼叫结束，但剩余的呼叫为"活动"状态。座席将返回"活动"状态。此后，座席不会接收到 EvtActive。<br>
	 * 			• "保持"状态 (AgHeld)：与 AgActive 相同，只是剩余的呼叫处于"保持"状态。座席将回到"保持"状态。此后，座席不会收到 EvtHeld 事件。<br>
	 *
	 * 有三种类型的人工呼叫：内部呼叫、外拨呼叫和预览呼叫。
	 *
	 * 返回对象: UDCallClear
	 * @see UDCallClear
	 */
	public static final int EVENT_CALLCLEAR = 26;

	/**
	 * 此事件将向该座席呈示预览呼叫。<br>
	 * 此事件中的信息与 Screenpop 中的信息相同，只是 CenterCord不会进行与 screenpop 相关联的呼叫。<br>
	 * 要由座席通知 CenterCord 他们想要结束该呼叫。
	 *
	 * 返回对象:  UDPreview
	 * @see UDPreview
	 */
	public static final int EVENT_PREVIEW = 27;

	/**
	 * 此事件用于通知咨询座席他当前处于 ConsultActive 状态。
	 *
	 * 返回对象:  UDConsult
	 * @see UDConsult
	 */
	public static final int EVENT_CONSULT = 28;

	/**
	 * 此事件用于通知被咨询座席转接呼叫已完成，他现在正在与客户通话。<br>
	 * 此转接呼叫的呼叫索引列在字段 nNewCallID 中。
	 *
	 * 返回对象:  UDCallXferred
	 * @see UDCallXferred
	 */
	public static final int EVENT_CALLXFERRED = 29;

	/**
	 * 此事件用于通知发起座席转接操作已完成。<br>
	 * 如果转接操作从会议模式发起，则参数 nCallID 的值将为会议呼叫索引，之后会接收到事件 EvtCallClear （nCallID 的值等于咨询呼叫索引）。<br>
	 * 否则，参数 nCallID 的值将为咨询呼叫索引。
	 *
	 * 返回对象:  UDCallReleasedXferred
	 * @see UDCallReleasedXferred
	 */
	public static final int EVENT_CALLRELEASEDXFERRED = 30;

	/**
	 * 此事件用于通知座席呼叫已被重新路由。
	 *
	 * 返回对象:  UDCallReRouted
	 * @see UDCallReRouted
	 */
	public static final int EVENT_CALLREROUTED = 31;

	/**
	 * 此事件用于通知座席他处于会议模式。
	 *
	 * 返回对象:  UDConference
	 * @see UDConference
	 */
	public static final int EVENT_CONFERENCE = 32;

	/**
	 * 此事件用于通知座席录音操作的状态。
	 *
	 * 返回对象:  UDRecordState
	 * @see UDRecordState
	 */
	public static final int EVENT_RECORDINGSTATE = 33;

	/**
	 * 此事件用于通知座席监控操作的状态。
	 *
	 * 返回对象:  UDMonitorState
	 * @see UDMonitorState
	 */
	public static final int EVENT_MONITORSTATE = 34;

	/**
	 * 此事件用于通知座席他当前登录的网关服务器已关闭。
	 *
	 * 返回对象:  UDSwitch
	 * @see UDSwitch
	 */
	public static final int EVENT_GATEWAYDOWN = 35;

	/**
	 * 此事件用于通知座席他当前登录的网关服务器已重新进入运行状态。
	 *
	 * 返回对象:  UDSwitch
	 * @see UDSwitch
	 */
	public static final int EVENT_GATEWAYUP = 36;

	/**
	 * 此事件用于通知座席他当前登录的 CTI 服务器已关闭。
	 *
	 * 返回对象:  UDSwitch
	 * @see UDSwitch
	 */
	public static final int EVENT_CTIDOWN = 37;

	/**
	 * 此事件用于通知座席他当前登录的 CTI 服务器已重新进入运行状态。
	 *
	 * 返回对象:  UDSwitch
	 * @see UDSwitch
	 */
	public static final int EVENT_CTIUP = 38;

	/**
	 * 此事件用于通知座席门户座席处于 "注销搁置" 状态。<br>
	 * 在发生以下情况时， CenterCord 会将此事件发送给座席：
	 * 	• 座席在并非处于 "空闲" 状态时请求注销。<br>
	 *  • CenterCord 想要强制座席注销，不管在任何时候。对于仅适用于 CTI 的座席来说，在 CTI 服务器关闭时，可能发生此情况。座席一旦处于此状态，就仅能执行注销操作。<br>
	 *
	 * 返回对象:  UDLogout
	 * @see UDLogout
	 */
	public static final int EVENT_LOGOUTPENDING = 39;

	/**
	 * 在发出 Not Ready 命令且座席状态不正确时，此事件将返回给座席。<br>
	 * 一旦座席处于正确的状态，CenterCord 就会发出 Not Ready 事件。
	 *
	 * 返回对象:  UDAgentIndex
	 * @see UDAgentIndex
	 */
	public static final int EVENT_NOTREADYPENDING = 40;

	/**
	 * 此命令通知门户字段 agentIndex 中指定的座席现在处于"未就绪"状态。参数 parkState 指定座席是否处于"暂放"状态。
	 *
	 * 返回对象:  UDNotReady
	 * @see UDNotReady
	 */
	public static final int EVENT_NOTREADY = 41;

	/**
	 * 此事件用于通知座席他到网关服务器的音频路径已重新建立。
	 *
	 * 返回对象:  UDAgentIndex
	 * @see UDAgentIndex
	 */
	public static final int EVENT_PHONERECONNECT = 42;

	/**
	 * 此事件包含参与和座席的聊天会话的聊天实体列表。
	 *
	 * 返回对象:  UDEntityListt
	 * @see UDEntityListt
	 */
	public static final int EVENT_LISTCHATENTITIES = 43;

	/**
	 * 此事件用于通知座席聊天服务器中有一条关于座席参与的聊天会话的消息。
	 *
	 * 返回对象:  UDEntitySendText
	 * @see UDEntitySendText
	 */
	public static final int EVENT_CHATMESSAGE = 44;

	/**
	 * 此事件将被发送给座席并且它包含客户想要座席与其合作所使用的 URL。客户与座席都参与一个聊天会话。
	 *
	 * 返回对象:  UDEntityPushUrl
	 * @see UDEntityPushUrl
	 */
	public static final int EVENT_CHATURL = 45;

	/**
	 * 此事件用于通知座席正在与其进行聊天会话的客户想要座席进行电话呼叫。<br>
	 * 座席然后应该使用事件中传递的号码向客户发起人工拨打呼叫。
	 *
	 * 返回对象:  UDDialRequest
	 * @see UDDialRequest
	 */
	public static final int EVENT_CHATDIALREQUEST = 46;

	/**
	 * 此事件用于通知座席门户在字段 CallID 中指定的呼叫现在是 agentIndex 中的座席的活动呼叫。<br>
	 * 在座席给呼叫发送配置代码并且 Disposition 命令中指定的参数 nextCallId 无效时， CenterCord将发送此事件。<br>
	 * 门户应将座席的窗口焦点转移到与该呼叫相关的任务。在座席有多个呼叫时使用此事件。
	 *
	 * 返回对象:  UDActivateCall
	 * @see UDActivateCall
	 */
	public static final int EVENT_ACTIVATECALL = 47;

	/**
	 * 每当 CenterCord 需要对 DirectorUpdate 命令作出响应时，就将 DirectorAck 事件发送回给门户。
	 * 此事件不由座席使用。
	 *
	 * 返回对象:  UDDirectorAck
	 * @see UDDirectorAck
	 */
	public static final int EVENT_DIRECTORACK = 48;

	/**
	 * 如果用户输入了无效的 PassCode，则 CenterCord 会将此事件发送给座席并通过 payload 对象传递正确的 PassCode。
	 *
	 * 返回对象:  UDBadPassCode
	 * @see UDBadPassCode
	 */
	public static final int EVENT_BADPASSCODE = 49;

	/**
	 * 此事件用于通知座席应将某实体添加到当前聊天会话。
	 *
	 * 返回对象:  UDEntity
	 * @see UDEntity
	 */
	public static final int EVENT_CHATENTITYADDED = 50;

	/**
	 * 此事用于通知座席应将某实体从当前聊天会话中删除。
	 *
	 * 返回对象:  UDEntity
	 * @see UDEntity
	 */
	public static final int EVENT_CHATENTITYREMOVED = 51;

	/**
	 * 此事件用于通知主管 (Director) 他当前处于 AdvanceFeature 状态。<br>
	 * 处于此状态时，主管是监控座席，不进行咨询和任何其它呼叫。这不是座席的典型状态。
	 *
	 * 返回对象:  UDAgentIndex
	 * @see UDAgentIndex
	 */
	public static final int EVENT_ADVANCEFEATURE = 52;

	/**
	 * 此事件用于请求门户开始或停止屏幕捕获录音。此事件在内部传递给门户。
	 *
	 * 返回对象:  UDScreenCapture
	 * @see UDScreenCapture
	 */
	public static final int EVENT_SCREENCAPTURE = 53;

	/**
	 * CenterCord 答复此 AgentStatistics 事件以作为对 AgentStatistics 命令的响应。<br>
	 * 该事件附带数据包向量，附带有字段列表。<br>
	 * 如果座席请求特定服务的数据，则该事件将仅包含针对指定服务的一个数据包。<br>
	 * 如果座席请求座席所属的所有服务的数据，则事件中将包含每个服务对应的数据包。<br>
	 * 事件中还将包含座席汇总统计数据的数据包。
	 *
	 * 返回对象:  UDAgentStatsCollection
	 * @see UDAgentStatsCollection
	 */
	public static final int EVENT_AGENTSTATS = 54;

	/**
	 * 当座席参与的 HTTP 会话由于某原因超时时，将把此事件发表到座席。
	 * 一旦发生此超时，与CenterCord 的会话将被终止。
	 *
	 */
	public static final int EVENT_SESSIONTIMEOUT = 55;

	/**
	 *
	 * 当座席门户检测到它与 CenterCord 的连接已被中止时，将把此事件发送给座席。这将结束与 CenterCord 的会话。
	 *
	 */
	public static final int EVENT_CCDOWN = 56;

	/**
	 * 当座席门户检测到它与 CenterCord 的连接已被启动时，将把此事件发送给座席。这将启动与 CenterCord 的会话。
	 *
	 */
	public static final int EVENT_CCUP = 57;


	public static final int EVENT_WPAMESSAGE = 58;

	/**
	 * CenterCord 答复此 EMailServiceStatistics 事件以作为对 EMailServiceStatistics 命令的响应。<br>
	 * 该事件附带数据包向量，带有列出的字段。<br>
	 * 如果座席请求特定服务的数据，则该事件将仅包含针对指定服务的一个数据包。<br>
	 * 如果座席请求座席所属的所有服务的数据，则事件中将包含每个服务对应的数据包。
	 *
	 * 返回对象:  UDEMailServiceStatisticsColl
	 * @see UDEMailServiceStatisticsColl
	 */
	public static final int EVENT_EMAILSTATS = 59;

	/**
	 * 此事件用于在 audioPath 字段为 TRUE 时通知座席已代表它建立了音频路径，或者在 audioPath字段为 FALSE 时通知座席音频路径已拆除。
	 *
	 * 返回对象:  UDAudioPathConnection
	 * @see UDAudioPathConnection
	 */
	public static final int EVENT_AUDIOPATHCONNECTION = 60;

	/**
	 * 如果门户中发生了接受呼叫超时，则将触发此事件。这将通知座席发生了超时。无有效载荷。
	 *
	 */
	public static final int EVENT_ACCEPT_CALL_TIMEOUT = 61;


	public static final int EVENT_EMAIL_FORWARD_ACK = 62;


	//IVR 结束事件 自定义事件
	public static final int EVENT_IVR_ACW_FINISH = 1000;
	//IVR 结束事件 自定义事件（客户挂断）
	public static final int EVENT_IVR_HanUP_FINISH = 2000;
	//IVR 结束事件 自定义事件（客户挂断）
		public static final int EVENT_IVR_HanUP = 3000;

	public static String toString(int eventType) {
		String eventArray[] = { "unset", "EVENT_ERROR", "EVENT_PHONEFOUL",
				"EVENT_PHONEOFFHOOK", "EVENT_UPDATESERVICE",
				"EVENT_REMOVESERVICE", "EVENT_UPDATEDISPOSITION",
				"EVENT_UPDATEDISPOSTIONPLAN", "EVENT_NEWVOICEMAIL",
				"EVENT_UPDATECALLDATADEF", "EVENT_UPDATEAGENTSTATUSREASONS",
				"EVENT_UPDATEEXTERNALROUTE", "EVENT_UPDATEREALTIMEPORT",
				"EVENT_LOGINCONF", "EVENT_SYSTEMWAIT", "EVENT_LOGGINGIN",
				"EVENT_PASSCODE", "EVENT_NEWSERVICE", "EVENT_LOGIN",
				"EVENT_IDLE", "EVENT_LOGOUT", "EVENT_SCREENPOP",
				"EVENT_ACTIVE", "EVENT_HOLD", "EVENT_WRAP", "EVENT_DIALING",
				"EVENT_CALLCLEAR", "EVENT_PREVIEW", "EVENT_CONSULT",
				"EVENT_CALLXFERRED", "EVENT_CALLRELEASEDXFERRED",
				"EVENT_CALLREROUTED", "EVENT_CONFERENCE",
				"EVENT_RECORDINGSTATE", "EVENT_MONITORSTATE",
				"EVENT_GATEWAYDOWN", "EVENT_GATEWAYUP", "EVENT_CTIDOWN",
				"EVENT_CTIUP", "EVENT_LOGOUTPENDING", "EVENT_NOTREADYPENDING",
				"EVENT_NOTREADY", "EVENT_PHONERECONNECT",
				"EVENT_LISTCHATENTITIES", "EVENT_CHATMESSAGE", "EVENT_CHATURL",
				"EVENT_CHATDIALREQUEST", "EVENT_ACTIVATECALL",
				"EVENT_DIRECTORACK", "EVENT_BADPASSCODE",
				"EVENT_CHATENTITYADDED", "EVENT_CHATENTITYREMOVED",
				"EVENT_ADVANCEFEATURE", "EVENT_SCREENCAPTURe",
				"EVENT_AGENTSTATS", "EVENT_SESSIONTIMEOUT", "EVENT_CCDOWN",
				"EVENT_CCUP", "EVENT_WPAMESSAGE", "EVENT_EMAILSTATS",
				"EVENT_AUDIOPATHCONNECTION", "EVENT_ACCEPT_CALL_TIMEOUT",
				"EVENT_EMAIL_FORWARD_ACK" };
		if (eventType >= 0 && eventType < eventArray.length)
			return eventArray[eventType];
		else
			return "Unknown Event";
	}

}
