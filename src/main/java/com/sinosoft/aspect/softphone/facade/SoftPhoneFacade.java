package com.sinosoft.aspect.softphone.facade;


import com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.StatusReasons;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface SoftPhoneFacade {


	/**
	 * 签入<p>
	 * 对应平安接口1<br>
	 * @param agentId 座席工号
	 * @param password 密码
	 * @param number 签入的分机(电话号码)
	 * @return
	 * 1.成功返回"000"<BR>
	 * 2.失败返回失败信息<BR>
	 * 001:工号已登录<BR>
	 * 002:该分机已被xx工号注册<BR>
	 * 003:该工号已被注册到其他分机<BR>
	 * 004:交换机与cti状态不同步<BR>
	 * 005:工号不存在（无效的CTI登录号)<BR>
	 * 006:工号属于多个平台<BR>
	 * 007:分机不存在<BR>
	 * 008:密码校验失败<BR>
	 * 009：登录失败，服务器达到最大登录数<BR>
	 * 010：连接服务器异常<BR>
	 * 011：其它错误
	 */
	String login(String agentId, String password, String number);

	/**
	 * 签出<p>
	 * @param agentId 座席工号
	 * @return
	 * 1.成功返回"000"<BR>
	 * 2.失败返回失败信息<BR>
	 * fail:失败信息 <BR>
	 *      001:注销分机失败<BR>
	 *      002：连接服务器异常<BR>
	 *      003：其它错误
	 */
	String logout(String agentId);

	/**
	 * 外呼<p>
	 * 对应平安接口3<br>
	 * @param agentId 座席工号
	 * @param number  外呼号码
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001:中继资源满<BR>
	 *    002:外呼号码与分机号相同<BR>
	 *    003:座席挂机<BR>
	 *    004:连接服务器异常<BR>
	 *    005:其它错误
	 */
	String dialCall(String agentId, String number) throws IOException;

	/**
	 * 挂断<p>
	 * 对应平安接口4<br>
	 * @param agentId 座席工号
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001:连接服务器异常<BR>
	 *    002:其它错误
	 */
	String releaseCall(String agentId);

	/**
	 * 结束后处理<p>
	 * 对应平安接口39<br>
	 * @return
	 */
	String acwFinish(String agentId);

	/**
	 * 应答<p>
	 * @param agentId 座席工号
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001:连接服务器异常<BR>
	 *    002:其它错误
	 */
	String answer(String agentId);

	/**
	 * 拒接
	 * @param reasonId
	 * @return
	 */
	String answerCall(String agentId,int reasonId);

	/**
	 * 示忙<p>
	 * @param agentId 座席工号
	 * @param notreadyreason 原因
	 * @return 操作结果
	 */
	String sayBusy(String agentId,String notreadyreason);

	/**
	 * 示忙<p>
	 * @param toParkState 暂放
	 * @param reasonId 示忙原因
	 * @return 操作结果
	 */
	String sayBusy(String agentId,boolean toParkState, int reasonId);

	/**
	 * 示闲<p>
	 * @param agentId 座席工号
	 * @return
	 */
	String sayIdle(String agentId);

	/**
	 * 二次拨号<p>
	 * @param agentId 座席工号
	 * @param number  外呼号码
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001:连接服务器异常<BR>
	 *    002:其它错误
	 */
	String secondDial(String agentId, String number);

	/**
	 * 保持<p>
	 * @param agentId
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001:没有可用呼叫<BR>
	 *    002:保持数量限制<BR>
	 *    003:连接服务器异常<BR>
	 *    004:保持失败
	 */
	String hold(String agentId);

	/**
	 * 取消保持<p>
	 * @param agentId
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001:没有可用呼叫<BR>
	 *    002:连接服务器异常<BR>
	 *    003:取消保持失败
	 */
	String unHold(String agentId);

	/**
	 * 转接<p>
	 * 转IVR时可调用此接口。
	 * 转接IVR<p>
	 * 转接类型 transferWay 中，1：转座席、2：转技能组、3：转外部号码；<br>
     * 转接模式 transferMode 中，1：释放转、2：成功转<br>
     * 具体到转IVR，transferWay传入2<br>
     * transferMode  如果转接后要求转回来，传2，如验密；如不要求转回来，传1，如满意度
	 *
	 * @param agentId     座席工号
	 * @param number      转接号码
	 * @param transferWay 转接类型：转坐席、技能组、转外部号码
	 * @param transferMode 转接模式：1-成功转、2-释放转
	 *                     如果转接后要求转回来，传2，如验密；如不要求转回来，传1，如满意度
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：没建立话路<BR>
	 *    002：没有空闲话路<BR>
	 *    003：错误的转移方式<BR>
	 *    004:连接服务器异常<BR>
	 *    005：其它错误
	 */
	String transfer(String agentId, String number, String transferWay, String transferMode);

	/**
	 * 双步转接<p>
	 * @param agentId     座席工号
	 * @param number      转接号码
	 * @param transferWay 转接类型：0-转坐席、2-技能组、1-转外部号码
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：没建立话路<BR>
	 *    002：没有空闲话路<BR>
	 *    003：错误的转移方式<BR>
	 *    004：保持失败
	 *    005：呼叫失败
	 *    006:连接服务器异常<BR>
	 *    007：其它错误
	 */
	String doubleStepTransfer(String agentId, String number, String transferWay);

	/**
	 * 收回咨询<p>
	 * 此接口是咨询方咨询完毕后，可以收回与客户继续通话。<BR>
	 * @return
	 */
	String hangupConsult(String agentid);


	/**
	 * 单步转接<p>
	 * @param agentId     座席工号
	 * @param number      转接号码
	 * @param transferWay 转接类型：转坐席、技能组、转外部号码
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：没建立话路<BR>
	 *    002：没有空闲话路<BR>
	 *    003：错误的转移方式<BR>
	 *    004:连接服务器异常<BR>
	 *    005：其它错误
	 */
	String singleStepTransfer(String agentId, String number, String transferWay);

	/**
	 * 完成转接<p>
	 * 在需要咨询和转接分二步进行时，先调用双步转接的接口，咨询成功后，再调用完成转接的接口<br>
	 * @param agentId     座席工号
	 * @param number      转接号码
	 * @param transferWay 转接类型：转坐席、技能组、转外部号码
	 * @param transferMode 转接模式：成功转、释放转
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：错误的转移方式<BR>
	 *    002：取保持失败<BR>
	 *    003:连接服务器异常<BR>
	 *    004：其它错误
	 */
	String completeTransfer(String agentId, String number, String transferWay, String transferMode);

	/**
	 * 双步会议<p>
	 * 调用时，先调用双步转接进入咨询状态，咨询通话后，调用双步会议进入三方会议状态<br>
	 * @param agentId     座席工号
	 * @param number      转接号码
	 * @param transferWay 转接类型：转坐席、技能组、转外部号码
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：没建立话路<BR>
	 *    002：没有空闲话路<BR>
	 *    003：保持失败<BR>
	 *    004：呼叫失败<BR>
	 *    005：错误的转移方式<BR>
	 *    006：连接服务器异常<BR>
	 *    007：其他错误<BR>
	 */
	String doubleStepConference(String agentId, String number, String transferWay);

	/**
	 * 完成会议<p>
	 * 即座席需要退出会议时，调用此方法，客户和被咨询方继续通话，座席自已退出通话进入后处理。<br>
	 * @param agentId 座席工号
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：三方通话有用户挂断<BR>
	 *    002：连接服务器异常<BR>
	 *    003：其他错误<BR>
	 */
	String completeConference(String agentId);


	/**
	 * 监听<p>
	 * @param monitored  监听者自己的工号
	 * @param monitoredAgentId 要监听的工号
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：错误状态<BR>
	 *    002：连接服务器异常<BR>
	 *    003：其它错误
	 */
	String supervise(String monitored, String monitoredAgentId);


	/**
	 * 强插<p>
	 * @param monitored 监听者自己的工号
	 * @param monitoredAgentId 要监听的工号
	 * @return
	 *    1.成功返回"000" <BR>
     *    2.失败返回失败信息 <BR>
     *    fail:失败信息 <BR>
	 *    001：错误状态<BR>
	 *    002：连接服务器异常<BR>
	 *    003：其它错误
	 */
	String insert(String monitored, String monitoredAgentId);

	/**
	 * 管理功能中的指导
	 * @param agentId 监听者自己的工号
	 * @param monitoredAgentId 指导的坐席工号
	 * @return
	 */
	String coaching(String agentId,String monitoredAgentId);


	/**
	 * 获取后处理结果列表
	 * @return 后处理结果列表
	 */
	Map getDispositions();

	/**
	 * 获取原因列表
	 *@param statusReasonsType 5=notready 20=logoff 29=rejectCall
	 *
	 * @return 原因列表
	 */
	List<StatusReasons> getStatusReasons(String agentId, int statusReasonsType);




}
