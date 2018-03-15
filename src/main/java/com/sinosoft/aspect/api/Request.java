package com.sinosoft.aspect.api;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class Request implements Serializable {

    //座席工号
    private String agentId;
    private String password;
    //座席分机
    private String station;
    //机构名称
    private String organName;
    //技能组
    private String skill;
    //机构code
    private String organCode;
    private String sessionId;
    //外呼号码
    private String phoneNumber;
    private String notreadyreason;

    //转接号码
    private String number;
    //转接类型
    private String transferWay;
    //转接模式：1-成功转、2-释放转
    private String transferMode;
    //转接随录数据
    private String userData;

    private int reasonId;

    //要管理的工号
    private String monitoredAgentId;

}
