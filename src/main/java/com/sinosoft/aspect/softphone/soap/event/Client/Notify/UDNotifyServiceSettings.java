package com.sinosoft.aspect.softphone.soap.event.Client.Notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class UDNotifyServiceSettings implements Serializable {

	private int serviceid;       //服务ID
	private int servicetypeid;   //服务类型
	private String userid;       //用户ID
	private int allowkb;  //
	private int allowphonechangeinpreview;
	private String appdesc;

	/**
	 * -用户是否可以盲目(w/o咨询)转接。
	 *  与默认//服务相关的调用。
	 */
	private int btnblindxferf;

	/**
	 *
	 */
	private int btncallbackf;

	/**
	 * -用户是否可以咨询他人。
	 * 在与默认服务相关联的调用中。
	 */
	private int btnconsultf;

	/**
	 * 用户是否可以手动外呼。
	 */
	private int btndialf;

	/**
	 * -用户是否可以挂电话。
	 * 与默认服务相关。0 = false;1 = true。
	 */
	private int btnhangupf;

	/**
	 * -用户是否可以保持与默认//-服务相关的调用。
	 */
	private int btnholdf;
	private int btnnextcall;
	private int btnplayf;

	/**
	 * 用户是否可以录音调用。
	 */
	private int btnrecordf;
	private int btnthreecusthangupf;
	private int btnthreewayf;
	private int btnxferf;
	private int callbackdays;
	private int calldatadefid;
	private int calldatadialogduration;
	private int canaddattachments;
	private int cbselfcallbackflag;
	private int cbservicecallbackflag;
	private int chatblindtransferallowed;
	private int chatdisconnectallowed;
	private int chatrecordallowed;
	private int chattransferallowed;
	private int displaycallbacktimewarningf;
	private int displayservicerecordingf;
	private int displayservicesf;
	private int lyricallscriptid;
	private String lyricallscriptname;
	private int mailclientid;
	private int nodispmaxwrap;
	private int pbxaodserviceid;
	private int previewtimeoutinsecs;
	private int reqdispositionf;
	private int requireservicef;
	private int reqwrapf;
	private String scriptclassname;
	private String scriptcommandline;
	private String scriptscreenpopmethod;
	private int scriptscreenpopnotifytype;
	private int showcalldatadialogf;
	private int timedpreviewflag;
	private int warmtransfer;
	private int webCollabAgentLedTypeId;
	private int hidePhoneOnSpeedDial;
	private int maxWrapTime;
	private int minWrapTime;
	private int replayAgentRecording;
	private int replayAgentVM;
	private int replayServiceVM;
	private int wrapExceedAction;
	private int wrapwarningdelay;

}
