package com.sinosoft.aspect.softphone.soap.agent.Client;

/**
 * 管理员管理功能类型
 * @author Believe
 *
 */
public class MonitorType {

	/**
	 * 监听/随机
	 */
	public static final int MON_SILENT = 102;

	/**
	 * 指导
	 */
	public static final int MON_COACHING = 103;

	/**
	 * 干预
	 */
	public static final int MON_BARGEIN = 104;

	/**
	 * 结束监听/随机
	 */
	public static final int MON_SILENT_STOP = 202;

	/**
	 * 结束指导
	 */
	public static final int MON_COACHING_STOP = 203;

	/**
	 * 结束干预
	 */
	public static final int MON_BARGEIN_STOP = 204;


	/**
	 * 从监听或干预到指导
	 */
	public static final int TO_MON_COACHING = 205;


	/**
	 *从指导或监听到干预
	 */
	public static final int TO_MON_BARGEIN = 206;


	/**
	 * 从干预或指导到监听
	 */
	public static final int TO_MON_SILENT = 216;
}
