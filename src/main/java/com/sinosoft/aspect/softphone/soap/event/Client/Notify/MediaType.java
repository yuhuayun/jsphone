package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

/**
 * Media Type
 * @author Believe
 *
 */
public class MediaType {

	/**
	 *
	 */
	public static final int CCProMediaNone = 0;

	/**
	 *
	 */
	public static final int CCProMediaInboundAcd = 1;
	public static final int CCProMediaInboundDid = 2;
	public static final int CCProMediaInboundIvr = 3;
	public static final int CCProMediaInboundUndefined = 4;
	public static final int CCProMediaInboundExternal = 5;
	public static final int CCProMediaInboundFax = 6;
	public static final int CCProMediaInboundVoiceMail = 7;

	/**
	 * 预览呼叫
	 */
	public static final int CCProMediaOutboundAod = 8;
	public static final int CCProMediaOutboundConference = 9;
	public static final int CCProMediaOutboundConsult = 10;
	public static final int CCProMediaOutboundConsultXfer = 11;
	public static final int CCProMediaOutboundFax = 12;
	public static final int CCProMediaOutboundExternal = 13;

	/**
	 * 外拨人工
	 */
	public static final int CCProMediaOutboundManual = 14;

	/**
	 *
	 */
	public static final int CCProMediaOutboundTPConXfer = 15;
	public static final int CCProMediaInternalConference = 16;

	/**
	 * 咨询呼叫
	 */
	public static final int CCProMediaInternalConsult = 17;
	public static final int CCProMediaInternalConsultxfer = 18;

	/**
	 * 内部人工
	 */
	public static final int CCProMediaInternalManual = 19;
	public static final int CCProMediaOutboundMessage = 20;
	public static final int CCProMediaInternalMonitoring = 21;
	public static final int CCProMediaInternalRecording = 22;
	public static final int CCProMediaInboundChat = 23;
	public static final int CCProMediaInboundNLPEMail = 24;
	public static final int CCProMediaInboundAgentSelEMail = 25;
	public static final int CCProMediaInboundSelfServEMail = 26;
	public static final int CCProMediaInboundCDReviewEMail = 27;
	public static final int CCProMediaInboundSendEMail = 28;
	public static final int CCProMediaOutboundEMail = 29;
	public static final int CCProMediaInboundAgd = 30;
	public static final int CCProMediaInboundCti = 31;
}
