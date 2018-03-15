package com.sinosoft.aspect.softphone.soap.agent.Client;

/**
 * 获取信息类型列表
 * @author Believe
 *
 */
public class ListType {

	/**
	 * 返回 LoggedInUsers 数组，该数组包含关于每个用户的一般信息。<br>
	 *
	 * info.setListType(100) <br>
	 * info.setUserType()<br>
	 * @see User
	 */
	public static final int GET_LOGGED_IN_USERS = 100;

	/**
	 * 返回 Externals 数组，该数组包含关于每个外部路由的一般信息<br>
	 * info.setListType(101) <br>
	 * @see Externals
	 */
	public static final int GET_SPEED_DIALS = 101;

	/**
	 * 返回 StatusReasons 数组，该数组包含关于每个状态原因的一般信息。<br>
	 *
	 * info.setListType(102)<br>
	 * info.setStatusReasonsType(5,20,29) 5=notready 20=logoff 29=rejectCall<br>
	 *
	 * @see StatusReasons
	 */
	public static final int GET_STATUS_REASONS = 102;

	/**
	 * 返回 AgentXfer 对象数组，该数组包含关于可向其进行转接的座席的一般信息。<br>
	 *
	 * info.setListType(103)<br>
	 * info.setServiceId();<br>
	 *
	 * @see AgentXfer
	 */
	public static final int GET_XFER_TO_AGENT_LIST = 103;

	/**
	 * 返回 DirectorXfer 对象数组，该数组包含关于可向其进行转接的 director 的一般信息。<br>
	 * info.setListType(104)<br>
	 * info.setServiceId()<br>
	 *
	 * @see  DirectorXfer
	 */
	public static final int GET_XFER_TO_DIRECTOR_LIST = 104;

	/**
	 * 返回 ServiceXfer 对象数组，该数组包含关于可向其进行转接的服务的一般信息。<br>
	 *
	 * info.setListType(105)<br>
	 * info.setServiceType()<br>
	 *
	 * @see ServiceXfer
	 */
	public static final int GET_XFER_TO_SERVICE_LIST = 105;

	/**
	 * 返回 Service 对象数组，该数组包含关于每个服务的一般信息。<br>
	 *
	 * info.setListType(106)<br>
	 * info.setServiceId()<br>
	 *
	 * @see Service
	 */
	public static final int GET_SERVICE = 106;

	/**
	 * 返回 ServiceSetting 对象，该对象包含关于服务的座席设置功能的详细信息。<br>
	 *
	 * info.setListType(107)<br>
	 *
	 * @see ServiceSetting
	 */
	public static final int GET_SERVICE_SETTINGS = 107;

	/**
	 * 返回 ServiceEmailQueues 对象数组，该数组包含关于每个电子邮件队列的一般信息。<br>
	 *
	 * info.setListType(108)<br>
	 * info.setServiceId()<br>
	 *
	 * @see ServiceEmailQueues
	 */
	public static final int GET_SERVICE_EMAIL_QUEUES = 108;

	/**
	 * 返回 DispositionPlan 对象，该对象包含关于请求的配置代码计划的一般信息。<br>
	 *
	 * info.setListType(109)<br>
	 * info.setServiceId()<br>
	 *
	 * @see DispositionPlan
	 */
	public static final int GET_DISPOSTION_PLAN = 109;

	/**
	 * 返回 Dispositions 对象的数组，该数组包含关于每个配置代码及其功能设置的信息。<br>
	 *
	 * info.setListType(110)<br>
	 * info.setServiceId()<br>
	 *
	 * @see Dispositions
	 */
	public static final int GET_DISPOSITIONS = 110;

	/**
	 * 返回配置代码对象，该对象包含关于配置代码及其功能设置的信息。
	 *
	 * info.setListType(111)<br>
	 * info.setDispostionId()<br>
	 *
	 */
	public static final int GET_DISPOSTION = 111;

	/**
	 * 返回 CallDataDef 对象，该对象包含关于它的一般信息。<br>
	 *
	 * info.setListType(112) <br>
	 * info.setCallDataDefId()<br>
	 *
	 * @see CallDataDef
	 */
	public static final int GET_CALL_DATA_DEF = 112;

	/**
	 * 返回 CallDataDefFields 对象数组，该数组包含关于为请求的呼叫数据定义所定义的每个 CallDataDefField 的信息。<br>
	 *
	 * info.setListType(113)<br>
	 * info.setCallDataDefId()<br>
	 *
	 * @see CallDataDefFields
	 */
	public static final int GET_CALL_DATA_DEF_FIELDS = 113;

	/**
	 * 返回 CallBackService 对象数组，该数组包含关于允许请求的用户代号进行的回拨的每个服务的信息。<br>
	 *
	 * info.setListType(114)<br>
	 *
	 * @see CallBackService
	 */
	public static final int GET_CALLBACK_SERVICE_LIST = 114;

	/**
	 * 返回 URL 对象，该对象包含关于它的一般信息。<br>
	 *
	 * info.setListType(115)<br>
	 * info.setUrlId()
	 *
	 * @see URL
	 */
	public static final int GET_URL = 115;

	/**
	 * 返回 VoiceMail 对象，该对象包含请求的录音的关于它的一般信息。<br>
	 *
	 * info.setListType(117)<br>
	 * info.setMessageId()<br>
	 *
	 * @see VoiceMail
	 *
	 */
	public static final int GET_VOICE_MAIL_RECORDING = 117;

	/**
	 *
	 * 返回更新状态字符串（例如，"OK”）。<br>
	 *
	 * info.setListType(118)<br>
	 * info.setStatusId(),<br>
	 * info.setServiceType()
	 *
	 */
	public static final int UPDATE_VOICE_MAIL_RECORDING_STATUS = 118;

	/**
	 * 返回更新状态字符串（例如，“OK”）。<br>
	 *
	 * info.setListType(119)<br>
	 * info.setServiceType(),<br>
	 * info.setSeqNum()
	 */
	public static final int UPDATE_MEDIA_RECORDING_STATUS = 119;

	/**
	 * Returns 返回更新状态字符串（例如，“OK”）。<br>
	 *
	 * info.setListType(120)<br>
	 * info.setSeqNum(),<br>
	 * info.setClassId(),<br>
	 * info.setRecordingComment()
	 */
	public static final int UPDATE_RECORDING_COMMENT = 120;

	/**
	 * 返回 VoiceMail 对象，该对象包含请求的录音的关于它的一般信息。<br>
	 *
	 * info.setListType(121)<br>
	 * info.setServiceId(),<br>
	 * info.setStatusId(),
	 *
	 * @see VoiceMail
	 */
	public static final int GET_CHECK_VOICE_MAIL = 121;

	/**
	 * 返回通用 GetIdDesc 对象，该对象包含 id 和description 字段（如果为 null，则服务不存在）。<br>
	 *
	 * info.setListType(122)<br>
	 * info.setServiceId()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_CHECK_SERVICE_EXISTS = 122;

	/**
	 * 返回通用 GetIdDesc 对象，该对象包含 id 和 description 字段（如果为 null，则 centercord 信息不存在）。<br>
	 *
	 * info.setListType(123)<br>
	 * info.setIpAddress1(),<br>
	 * info.setIpAddress2()
	 */
	public static final int GET_CENTERCORD_INFO = 123;

	/**
	 * 返回 CallLogger 对象，该对象包含关于它的一般信息。<br>
	 *
	 * info.setListType(124)
	 */
	public static final int GET_CALL_LOGGER_INFO = 124;

	/**
	 * 返回通用 GetIdDesc 对象的数组，该对象包含系统定义的应用程序的 id 和 description 字段。<br>
	 *
	 * info.setListType(125)
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_APPLICATIONS = 125;

	/**
	 * 返回通用 GetIdDesc 对象（该对象包含 id 和 description 字段），已用请求的配置代码的配置类型的代号填充该对象的对应字段。<br>
	 *
	 * info.setListType(126)<br>
	 * info.setDispostionId()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_DISPOSTION_TYPE = 126;

	/**
	 * 返回 Service 对象数组，该数组包含关于每个数组项的一般信息。<br>
	 *
	 * info.getListType(127)<br>
	 * info.getServiceType()
	 *
	 * @see Service
	 */
	public static final int GET_SERVICE_LIST = 127;

	/**
	 * 返回通用 GetIdDesc 对象，该对象包含 id 和 description 字段，已用录音描述和监控代号填充这些字段。<br>
	 *
	 * info.setListType(128)<br>
	 * info.setSeqNum()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_RECORDING_COMMENT = 128;

	/**
	 *
	 * 返回通用 GetIdDesc 对象，该对象包含 id 和 description 字段，已用计数的值来填充 id 字段。<br>
	 *
	 * info.setListType(129)<br>
	 * info.setSeqNum()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_MEDIA_RECORDINGS_COUNT = 129;

	/**
	 * 返回 MediaRecordings 对象的数组，该数组包含关于每个数组项的信息。<br>
	 *
	 * info.setListType(130)<br>
	 * info.setFromDate()<br>
	 * info.setToDate()<br>
	 * info.setMinMsgId()<br>
	 * info.setRecordCount()<br>
	 * info.setRowCount()
	 *
	 * @see MediaRecordings
	 */
	public static final int GET_MEDIA_RECORDINGS = 130;

	/**
	 * 返回 MediaRecordings 对象的数组，该数组包含关于每个数组项的信息。<br>
	 *
	 * info.setListType(131)<br>
	 * info.setSearchField()<br>
	 * info.setSearchType()<br>
	 * info.setSearchText()<br>
	 * info.setUserTypeMask()
	 *
	 * @see MediaRecordings
	 */
	public static final int GET_SEARCH_MEDIA_RECORDINGS = 131;

	/**
	 * 返回通用 GetIdDesc 对象的数组，该对象包含 id 和 description 字段，已用 AttachmentId 的值填充 id 字段。<br>
	 *
	 * info.setListType(132)<br>
	 * info.setId()<br>
	 *
	 * info.setIndicator() <br>
	 * 如果 indicator =1(email)，则 id 为电子邮件代号<br>
	 * 如果 indicator =2(servicid)，则 id 为服务代号 <br>
	 * 如果 indicator =3(mailQid)，则 id 为邮件队列代号
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_EMAIL_ATTACHMENT_ID = 132;

	/**
	 * 返回 Attachments 对象，该对象包含关于它的信息。<br>
	 *
	 * info.setListType(133)<br>
	 * info.setAttachementId()
	 *
	 * @see Attachments
	 *
	 */
	public static final int GET_EMAIL_ATTACHMENT_FILE_INFO = 133;


	/**
	 * 返回通用 GetIdDesc 对象，该对象包含 id 和 description 字段，已用 EmailId 的值填充 id 字段。<br>
	 *
	 * info.setListType(136)<br>
	 * info.setServiceId()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_SERVICE_EMAIL = 136;

	/**
	 * 返回通用 GetIdDesc 对象，该对象包含 id 和 description 字段，已用类别计数的值来填充 id 字段。<br>
	 *
	 * info.setListType(137)<br>
	 * info.setServiceId()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_CATEGORY_COUNT = 137;

	/**
	 * 返回通用 GetIdDesc 对象的数组，该对象包含 id 和 description 字段，已用 categoryid 的值填充 id 字段并且已用类别名称填充 description字段。<br>
	 *
	 * info.setListType(138)<br>
	 * info.setServiceId()<br>
	 * info.setMailId()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_KBASE_CATEGORIES = 138;


	/**
	 * 返回通用 GetIdDesc 对象，该对象包含 id 和 description 字段，已用服务的服务设置的 allowKB 值填充了 id 字段。<br>
	 *
	 * info.setListType(141)<br>
	 * info.setServiceId()
	 *
	 * @see GetIdDesc
	 */
	public static final int GET_AGENTS_ALLOW_KB_SETTING = 141;

	/**
	 *
	 */
	public static final int GET_USER_SETTINGS = 142;

	/**
	 *
	 */
	public static final int GET_LOGIN_USER = 143;

	/**
	 *
	 */
	public static final int GET_SPEED_DIAL = 144;

	/**
	 *
	 */
	public static final int GET_EMAIL_BODY = 146;

	/**
	 *
	 */
	public static final int GET_EMAIL_SIGNATURE = 147;


	/**
	 *
	 */
	public static final int GET_NLP_CATEGORIES = 149;

	/**
	 *
	 */
	public static final int GET_SERVICE_URLS = 151;

	/**
	 *
	 */
	public static final int GET_STATION_BROADBAND_CHANNEL = 152;

	/**
	 *
	 */
	public static final int GET_USER_VOICE_MAIL_RECORDINGS = 153;

	/**
	 *
	 */
	public static final int GET_SERVICE_VOICE_MAIL_RECORDINGS = 154;

	/**
	 *
	 */
	public static final int GET_RECORDING_CLASSES = 155;

	/**
	 *
	 */
	public static final int GET_RECORDING_CLASS_DESCRIPTION = 156;

	/**
	 *
	 */
	public static final int GET_RECORDING_CLASS_ID = 157;

	/**
	 *
	 */
	public static final int GET_SITE_ID = 158;

	/**
	 *
	 */
	public static final int GET_VERSION = 159;

	/**
	 *
	 */
	public static final int GET_TENANT_SHORT_NAME = 160;

	/**
	 *
	 */
	public static final int GET_AGENT_SETTINGS = 161;

	/**
	 *
	 */
	public static final int GET_AGENT_SETTINGS_SIMPLE = 162;

	/**
	 *
	 */
	public static final int GET_OCS_CONFIGURATION = 164;

	/**
	 *
	 */
	public static final int GET_KW_SKILLS = 165;

	/**
	 *
	 */
	public static final int GET_KW_USER_LIST_BY_SKILLS = 166;

	/**
	 *
	 */
	public static final int GET_KW_USER = 167;

	/**
	 *
	 */
	public static final int GET_SERVER_ID = 168;

	/**
	 *
	 */
	public static final int GET_AGENT_TYPE = 169;

	/**
	 *
	 */
	public static final int GET_LOGGED_IN_USERS_IM = 170;

	/**
	 *
	 */
	public static final int GET_IM_AGENTS_BY_WORKGROUP_ID = 171;

	/**
	 *
	 */
	public static final int GET_LOAD_CACHE = 999;

	/**
	 *
	 */
	public static final int ARE_THERE_UNREAD_VOICEMESSAGES_EXISTS = 172;

}
