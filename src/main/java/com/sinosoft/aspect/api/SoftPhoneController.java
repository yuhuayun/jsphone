package com.sinosoft.aspect.api;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

import com.sinosoft.aspect.softphone.facade.SoftPhoneFacade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 软电话接口
 * @author wangjunhua
 * @since 1.0
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SoftPhoneController {

    /** 软电话接口 **/
	private final SoftPhoneFacade facade;

    /**
     * 签入<p>
     * 对应平安接口1<br>
     * @param request 座席工号
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
	@OnEvent("phone:login")
	public void onEvent(SocketIOClient client, Request request, AckRequest ack){
		try {
			log.info(request.toString());
			facade.login(request.getAgentId(),request.getPassword(),request.getStation());
		}catch (Exception e){
			ack.sendAckData("fs===========");
		}

	}

    /**
     * 示忙
     * @param request 座席工号
     */
    @OnEvent("phone:busy")
    public void onBusyEvent(SocketIOClient client, Request request, AckRequest ack){
        facade.sayBusy(request.getAgentId(),request.getNotreadyreason());
    }

    /**
     * 示闲
     * @param agentId 座席工号
     */
    @OnEvent("phone:idle")
    public void onIdleEvent(SocketIOClient client, String agentId, AckRequest ack){
        facade.sayIdle(agentId);
    }

    /**
     * 外呼
     * @param request 座席工号
     */
    @OnEvent("phone:dialCall")
    public void onDialCallEvent(SocketIOClient client, Request request, AckRequest ack) throws IOException {
        facade.dialCall(request.getAgentId(),request.getPhoneNumber());
    }

    /**
     * 挂断
     * @param agentId 座席工号
     */
    @OnEvent("phone:hangup")
    public void onHangupEvent(SocketIOClient client, String agentId, AckRequest ack){
        facade.releaseCall(agentId);
    }

    /**
     * 结束后处理
     * @param agentId 座席工号
     */
    @OnEvent("phone:acw")
    public void onAcwEvent(SocketIOClient client, String agentId, AckRequest ack){
        facade.acwFinish(agentId);
    }

    /**
     * 保持
     * @param agentId 座席工号
     */
    @OnEvent("phone:hold")
    public void onHoldEvent(SocketIOClient client, String agentId, AckRequest ack){
        facade.hold(agentId);
    }

    /**
     * 取消保持
     * @param agentId 座席工号
     */
    @OnEvent("phone:unHold")
    public void onUnHoldEvent(SocketIOClient client, String agentId, AckRequest ack){
        facade.unHold(agentId);
    }

    /**
     * 咨询-双步转接
     */
    @OnEvent("phone:consult")
    public void onConsultEvent(SocketIOClient client, Request request, AckRequest ack){
        facade.doubleStepTransfer(request.getAgentId(),request.getNumber(),request.getTransferWay());
    }

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
	@OnEvent("phone:answer")
	public void onAnswerEvent(SocketIOClient client, String agentId, AckRequest ack){
		facade.answer(agentId);
	}

	/**
	 * 拒接电话
	 * @return
	 */
	@OnEvent("phone:answerCall")
	public void onAnswerCallEvent(SocketIOClient client, Request request, AckRequest ack){
		facade.answerCall(request.getAgentId(),request.getReasonId());
	}

	/**
	 * 二次拨号
	 */
	@OnEvent("phone:secondDial")
	public void onSendDigitEvent(SocketIOClient client, Request request, AckRequest ack){
		facade.secondDial(request.getAgentId(),request.getNumber());
	}


	/**
	 * 转接<p>
	 * 转IVR时可调用此接口。
	 * 转接IVR<p>
	 * 转接类型 transferWay 中，1：转座席、2：转技能组、3：转外部号码；<br>
	 * 转接模式 transferMode 中，1：释放转、2：成功转<br>
	 * 具体到转IVR，transferWay传入2<br>
	 * transferMode  如果转接后要求转回来，传2，如验密；如不要求转回来，传1，如满意度
	 *
	 *  agentId     座席工号
	 *  number      转接号码
	 *  transferWay 转接类型：转坐席、技能组、转外部号码  值为1代表咨询内线，值为2代表咨询外线，值为3代表咨询服务
	 *  transferMode 转接模式：转接模式：1-成功转、2-释放转
	 */
	@OnEvent("phone:transfer")
	public void onTransferEvent(SocketIOClient client, Request request, AckRequest ack){
		facade.transfer(request.getAgentId(),request.getNumber(),request.getTransferWay(),request.getTransferMode());
	}

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
	@OnEvent("phone:completeConference")
	public void onompleteConferenceEvent(SocketIOClient client, String agentId, AckRequest ack){
		facade.completeConference(agentId);
	}

    /**
     * 签出
     * @param agentId 座席工号
     */
    @OnEvent("phone:logout")
    public void onLogoutEvent(SocketIOClient client, String agentId, AckRequest ack){
        facade.logout(agentId);
    }


	/**
	 * 监听
	 *   monitored 监听者自己的工号
	 *   monitoredAgentId 要监听的工号
	 */
	@OnEvent("phone:supervise")
	public void onSuperviseEvent(SocketIOClient client, Request request, AckRequest ack){
		facade.supervise(request.getAgentId(),request.getMonitoredAgentId());
	}


	/**
	 * 强插<p>
	 * agentId 监听者自己的工号
	 * targetnumber  要监听的分机/要监听的工号
	 */
	@OnEvent("phone:insert")
	public void onInsertEvent(SocketIOClient client, Request request, AckRequest ack){
		facade.insert(request.getAgentId(),request.getMonitoredAgentId());
	}

}
