package com.sinosoft.aspect.softphone.soap.agent.Client;

/**
 * 录音请求类型
 * @author Believe
 *
 */
public class RecordRequestType {

	public static final int ccpStartRecord = 100;
	public static final int ccpStopRecord = 200;
	public static final int ccpPauseRecord = 207;
	public static final int ccpResumeRecord = 208;
	public static final int ccpGetChatRecording = 217;

	public static final int ccpPlayRecord = 101;
	public static final int ccpStopPlay = 201;
	public static final int ccpIncreasePlaySpeed = 221;
	public static final int ccpDecreasePlaySpeed = 222;
	public static final int ccpNormalPlaySpeed = 223;
	public static final int ccpFastFwdPlay = 224;
	public static final int ccpRewindPlay = 225;
	public static final int ccpPausePlay = 226;
	public static final int ccpResumePlay = 227;

	public static final int ccpIncreaseVolume = 218;
	public static final int ccpDecreaseVolume = 219;
	public static final int ccpNormalVolume = 220;
}
