package com.sinosoft.aspect.softphone.facade;

import com.sinosoft.aspect.softphone.service.EventGetThreadGate;
import com.sinosoft.aspect.softphone.service.TokenBean;
import com.sinosoft.aspect.softphone.soap.agent.AgentWebService;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgent;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNewService;
import com.sinosoft.aspect.softphone.soap.provisioning.Client.Beans.Agent.User;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class UserSession {

    //座席工号
    private String agentId;
    //密码
    private String password;
    //座席分机
    private String station;
    //机构名称
    private String organName;
    //技能组
    private String skill;
    //机构code
    private String organCode;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 外拨号码
     */
    private String phoneNumber;

    private String ani;
    private String dnis;

    /**
     * 当前的通话ID
     */
    private int callId;

    /**
     * 当前的服务ID
     */
    private int serviceID = 0;

    /**
     * 咨询的呼叫ID
     */
    protected int consultCallId = 0;

    /**
     * 会议的呼叫ID
     */
    protected int conferenceCallId = 0;

    private UUID uuid;


    public boolean isConsult(){
        return consultCallId != 0;
    }

    public boolean isConference(){
        return conferenceCallId != 0;
    }

    /**
     * 登录的坐席
     */
    private UDAgent udAgent;

    /**
     * 坐席服务器配置信息
     */
    private User userInfo;

    private TokenBean token;

    //通话时长
    private long startTime;
    private long endTime;

    private Timer timer = null;

    //坐席配置的服务信息
    private Map<Integer,UDNewService> udNewServiceMap = new ConcurrentHashMap<>();
    private EventGetThreadGate eventGetThread;
    private AgentWebService msgDelegate;

    public void addNewService(UDNewService udNewService){
        udNewServiceMap.put(udNewService.getServiceID(),udNewService);
    }

    public UDNewService getNewService(int serviceId){
        return udNewServiceMap.get(serviceId);
    }

    public void remove(int serviceId){
        udNewServiceMap.remove(serviceId);
    }

    public void clear(){
        udNewServiceMap.clear();
    }

    public List<UDNewService> listNewService(){
        return (List<UDNewService>) udNewServiceMap.values();
    }

    public void replace(int serviceID,UDNewService udNewService){
        udNewServiceMap.replace(serviceID,udNewService);
    }

}
