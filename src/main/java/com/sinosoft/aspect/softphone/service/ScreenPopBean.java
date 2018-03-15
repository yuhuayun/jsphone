package com.sinosoft.aspect.softphone.service;


import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfo;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDCallInfoUserDefinedItem;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDPreview;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDScreenPop;

public class ScreenPopBean {
  private int callfield_AgentIndex;
  private String callfield_ANI;
  private String callfield_CallerID;
  private int callfield_CallID;
  private int callfield_CallType;
  private String callfield_DNIS;
  private String callfield_FirstName;
  private String callfield_LastName;
  private String callfield_PhoneNumber;
  private int callfield_ServiceID;
  private String callfield_ServiceName;
  private int callfield_CallbackFlag;
  private int callfield_EmailMsgId;
  private int callfield_EmailQId;
  private String callfield_EmailForwardTo;
  private String callfield_CallbackMemo;
  private int callfield_CallCategory;
  private String callfield_CallbackName;
  private String callfield_TableName;
  private int callfield_StoreManagerID;
  private int callfield_TargetManagerID;
  private int callfield_CallTableRecordNum;
  private String callfield_UserDefined1;
  private String callfield_UserDefined1key;
  private String callfield_UserDefined2;
  private String callfield_UserDefined2key;
  private String callfield_UserDefined3;
  private String callfield_UserDefined3key;
  private String callfield_UserDefined4;
  private String callfield_UserDefined4key;
  private String callfield_UserDefined5;
  private String callfield_UserDefined5key;
  private String callfield_UserDefined6;
  private String callfield_UserDefined6key;
  private String callfield_UserDefined7;
  private String callfield_UserDefined7key;
  private String callfield_UserDefined8;
  private String callfield_UserDefined8key;
  private String callfield_UserDefined9;
  private String callfield_UserDefined9key;
  private String callfield_UserDefined10;
  private String callfield_UserDefined10key;
  private String callfield_UserDefined11;
  private String callfield_UserDefined11key;
  private String callfield_UserDefined12;
  private String callfield_UserDefined12key;
  private String callfield_UserDefined13;
  private String callfield_UserDefined13key;
  private String callfield_UserDefined14;
  private String callfield_UserDefined14key;
  private String callfield_UserDefined15;
  private String callfield_UserDefined15key;
  private String callfield_UserDefined16;
  private String callfield_UserDefined16key;
  private String callfield_UserDefined17;
  private String callfield_UserDefined17key;
  private String callfield_UserDefined18;
  private String callfield_UserDefined18key;
  private String callfield_UserDefined19;
  private String callfield_UserDefined19key;
  private String callfield_UserDefined20;
  private String callfield_UserDefined20key;
  private int callfield_SwitchCallID;
  private int callfield_Preemptive;
  private int callfield_StatusReason;
  private boolean callfield_RequiredRejectReason;
  private boolean callfield_RequiredResponse;
  private int callfield_DialModeCode;
  private int callfield_EventCode;
  private int callfield_OriginalServiceID;
  private int callfield_TimeoutInSeconds;
  private boolean callfield_PlayAudioAlert;
  private String navigation;
  private int pbxaodserviceid;

  public ScreenPopBean()
  {
    this.callfield_ANI = "";
    this.callfield_CallerID = "";
    this.callfield_CallID = 0;
    this.callfield_CallType = 0;
    this.callfield_DNIS = "";
    this.callfield_FirstName = "";
    this.callfield_LastName = "";
    this.callfield_PhoneNumber = "";
    this.callfield_ServiceID = 0;
    this.callfield_ServiceName = "";

    this.callfield_EmailMsgId = 0;
    this.callfield_EmailQId = 0;
    this.callfield_EmailForwardTo = "";
    this.callfield_CallbackMemo = "";
    this.callfield_CallCategory = -1;
    this.callfield_CallbackName = "";
    this.callfield_TableName = "";
    this.callfield_StoreManagerID = 0;
    this.callfield_TargetManagerID = 0;
    this.callfield_CallTableRecordNum = 0;

    this.callfield_UserDefined1 = "";
    this.callfield_UserDefined1key = "";
    this.callfield_UserDefined2 = "";
    this.callfield_UserDefined2key = "";
    this.callfield_UserDefined3 = "";
    this.callfield_UserDefined3key = "";
    this.callfield_UserDefined4 = "";
    this.callfield_UserDefined4key = "";
    this.callfield_UserDefined5 = "";
    this.callfield_UserDefined5key = "";
    this.callfield_UserDefined6 = "";
    this.callfield_UserDefined6key = "";
    this.callfield_UserDefined7 = "";
    this.callfield_UserDefined7key = "";
    this.callfield_UserDefined8 = "";
    this.callfield_UserDefined8key = "";
    this.callfield_UserDefined9 = "";
    this.callfield_UserDefined9key = "";
    this.callfield_UserDefined10 = "";
    this.callfield_UserDefined10key = "";
    this.callfield_UserDefined11 = "";
    this.callfield_UserDefined11key = "";
    this.callfield_UserDefined12 = "";
    this.callfield_UserDefined12key = "";
    this.callfield_UserDefined13 = "";
    this.callfield_UserDefined13key = "";
    this.callfield_UserDefined14 = "";
    this.callfield_UserDefined14key = "";
    this.callfield_UserDefined15 = "";
    this.callfield_UserDefined15key = "";
    this.callfield_UserDefined16 = "";
    this.callfield_UserDefined16key = "";
    this.callfield_UserDefined17 = "";
    this.callfield_UserDefined17key = "";
    this.callfield_UserDefined18 = "";
    this.callfield_UserDefined18key = "";
    this.callfield_UserDefined19 = "";
    this.callfield_UserDefined19key = "";
    this.callfield_UserDefined20 = "";
    this.callfield_UserDefined20key = "";

    this.callfield_SwitchCallID = 0;
    this.callfield_Preemptive = 0;
    this.callfield_StatusReason = 0;
    this.callfield_RequiredRejectReason = false;
    this.callfield_RequiredResponse = false;
    this.callfield_DialModeCode = 0;
    this.callfield_EventCode = 0;
    this.callfield_OriginalServiceID = 0;
    this.callfield_TimeoutInSeconds = 0;
    this.callfield_PlayAudioAlert = false;

    this.navigation = null;
  }

  public String getNavigation()
  {
    return this.navigation;
  }

  public String getCallfield_ANI()
  {
    return this.callfield_ANI;
  }

  public String getCallfield_CallerID()
  {
    return this.callfield_CallerID;
  }

  public int getCallfield_CallType()
  {
    return this.callfield_CallType;
  }

  public String getCallfield_DNIS()
  {
    return this.callfield_DNIS;
  }

  public String getCallfield_FirstName()
  {
    return this.callfield_FirstName;
  }

  public String getCallfield_LastName()
  {
    return this.callfield_LastName;
  }

  public String getCallfield_PhoneNumber()
  {
    return this.callfield_PhoneNumber;
  }

  public int getCallfield_ServiceID()
  {
    return this.callfield_ServiceID;
  }

  public String getCallfield_ServiceName()
  {
    return this.callfield_ServiceName;
  }

  public String getCallfield_TableName()
  {
    return this.callfield_TableName;
  }

  public int getCallfield_StoreManagerID()
  {
    return this.callfield_StoreManagerID;
  }

  public int getCallfield_TargetManagerID()
  {
    return this.callfield_TargetManagerID;
  }

  public String getCallfield_CallbackName()
  {
    return this.callfield_CallbackName;
  }

  public String getCallfield_UserDefined1()
  {
    return this.callfield_UserDefined1;
  }

  public String getCallfield_UserDefined2()
  {
    return this.callfield_UserDefined2;
  }

  public String getCallfield_UserDefined3()
  {
    return this.callfield_UserDefined3;
  }

  public String getCallfield_UserDefined4()
  {
    return this.callfield_UserDefined4;
  }

  public String getCallfield_UserDefined5()
  {
    return this.callfield_UserDefined5;
  }

  public String getCallfield_UserDefined6()
  {
    return this.callfield_UserDefined6;
  }

  public String getCallfield_UserDefined7()
  {
    return this.callfield_UserDefined7;
  }

  public String getCallfield_UserDefined8()
  {
    return this.callfield_UserDefined8;
  }

  public String getCallfield_UserDefined9()
  {
    return this.callfield_UserDefined9;
  }

  public String getCallfield_UserDefined10()
  {
    return this.callfield_UserDefined10;
  }

  public String getCallfield_UserDefined11()
  {
    return this.callfield_UserDefined11;
  }

  public String getCallfield_UserDefined12()
  {
    return this.callfield_UserDefined12;
  }

  public String getCallfield_UserDefined13()
  {
    return this.callfield_UserDefined13;
  }

  public String getCallfield_UserDefined14()
  {
    return this.callfield_UserDefined14;
  }

  public String getCallfield_UserDefined15()
  {
    return this.callfield_UserDefined15;
  }

  public String getCallfield_UserDefined16()
  {
    return this.callfield_UserDefined16;
  }

  public String getCallfield_UserDefined17()
  {
    return this.callfield_UserDefined17;
  }

  public String getCallfield_UserDefined18()
  {
    return this.callfield_UserDefined18;
  }

  public String getCallfield_UserDefined19()
  {
    return this.callfield_UserDefined19;
  }

  public String getCallfield_UserDefined20()
  {
    return this.callfield_UserDefined20;
  }

  public int getCallfield_AgentIndex() {
    return this.callfield_AgentIndex;
  }

  public String getCallfield_CallbackMemo() {
    return this.callfield_CallbackMemo;
  }

  public int getCallfield_CallCategory() {
    return this.callfield_CallCategory;
  }

  public int getCallfield_CallbackFlag() {
    return this.callfield_CallbackFlag;
  }

  public String getCallfield_UserDefined1key() {
    return this.callfield_UserDefined1key;
  }

  public String getCallfield_UserDefined2key() {
    return this.callfield_UserDefined2key;
  }

  public String getCallfield_UserDefined3key() {
    return this.callfield_UserDefined3key;
  }

  public String getCallfield_UserDefined4key() {
    return this.callfield_UserDefined4key;
  }

  public String getCallfield_UserDefined5key() {
    return this.callfield_UserDefined5key;
  }

  public String getCallfield_UserDefined6key() {
    return this.callfield_UserDefined6key;
  }

  public String getCallfield_UserDefined7key() {
    return this.callfield_UserDefined7key;
  }

  public String getCallfield_UserDefined8key() {
    return this.callfield_UserDefined8key;
  }

  public String getCallfield_UserDefined9key() {
    return this.callfield_UserDefined9key;
  }

  public String getCallfield_UserDefined10key() {
    return this.callfield_UserDefined10key;
  }

  public String getCallfield_UserDefined11key() {
    return this.callfield_UserDefined11key;
  }

  public String getCallfield_UserDefined12key() {
    return this.callfield_UserDefined12key;
  }

  public String getCallfield_UserDefined13key() {
    return this.callfield_UserDefined13key;
  }

  public String getCallfield_UserDefined14key() {
    return this.callfield_UserDefined14key;
  }

  public String getCallfield_UserDefined15key() {
    return this.callfield_UserDefined15key;
  }

  public String getCallfield_UserDefined16key() {
    return this.callfield_UserDefined16key;
  }

  public String getCallfield_UserDefined17key() {
    return this.callfield_UserDefined17key;
  }

  public String getCallfield_UserDefined18key() {
    return this.callfield_UserDefined18key;
  }

  public String getCallfield_UserDefined19key() {
    return this.callfield_UserDefined19key;
  }

  public String getCallfield_UserDefined20key() {
    return this.callfield_UserDefined20key;
  }

  public int getCallfield_CallID() {
    return this.callfield_CallID;
  }

  public int getCallfield_EmailMsgId() {
    return this.callfield_EmailMsgId;
  }

  public int getCallfield_EmailQId() {
    return this.callfield_EmailQId;
  }

  public String getCallfield_EmailForwardTo() {
    return this.callfield_EmailForwardTo;
  }

  public int getCallfield_CallTableRecordNum() {
    return this.callfield_CallTableRecordNum;
  }

  public int getCallfield_SwitchCallID() {
    return this.callfield_SwitchCallID;
  }

  public int getCallfield_Preemptive() {
    return this.callfield_Preemptive;
  }

  public int getCallfield_StatusReason() {
    return this.callfield_StatusReason;
  }

  public boolean isCallfield_RequiredRejectReason() {
    return this.callfield_RequiredRejectReason;
  }

  public boolean isCallfield_RequiredResponse() {
    return this.callfield_RequiredResponse;
  }

  public int getCallfield_DialModeCode() {
    return this.callfield_DialModeCode;
  }

  public int getCallfield_EventCode() {
    return this.callfield_EventCode;
  }

  public int getCallfield_OriginalServiceID() {
    return this.callfield_OriginalServiceID;
  }

  public int getCallfield_TimeoutInSeconds() {
    return this.callfield_TimeoutInSeconds;
  }

  public boolean isCallfield_PlayAudioAlert() {
    return this.callfield_PlayAudioAlert;
  }

  public void setNavigation(String inString)
  {
    this.navigation = inString;
  }

  public void setCallfield_ANI(String inString)
  {
    this.callfield_ANI = inString;
  }

  public void setCallfield_CallerID(String inString)
  {
    this.callfield_CallerID = inString;
  }

  public void setCallfield_CallType(int inInt)
  {
    this.callfield_CallType = inInt;
  }

  public void setCallfield_DNIS(String inString)
  {
    this.callfield_DNIS = inString;
  }

  public void setCallfield_FirstName(String inString)
  {
    this.callfield_FirstName = inString;
  }

  public void setCallfield_LastName(String inString)
  {
    this.callfield_LastName = inString;
  }

  public void setCallfield_PhoneNumber(String inString)
  {
    this.callfield_PhoneNumber = inString;
  }

  public void setCallfield_ServiceID(int inInt)
  {
    this.callfield_ServiceID = inInt;
  }

  public void setCallfield_ServiceName(String inString)
  {
    this.callfield_ServiceName = inString;
  }

  public void setCallfield_TableName(String inString)
  {
    this.callfield_TableName = inString;
  }

  public void setCallfield_StoreManagerID(int inInt)
  {
    this.callfield_StoreManagerID = inInt;
  }

  public void setCallfield_TargetManagerID(int inInt)
  {
    this.callfield_TargetManagerID = inInt;
  }

  public void setCallfield_CallbackName(String inString)
  {
    this.callfield_CallbackName = inString;
  }

  public void setCallfield_UserDefined1(String inString)
  {
    this.callfield_UserDefined1 = inString;
  }

  public void setCallfield_UserDefined2(String inString)
  {
    this.callfield_UserDefined2 = inString;
  }

  public void setCallfield_UserDefined3(String inString)
  {
    this.callfield_UserDefined3 = inString;
  }

  public void setCallfield_UserDefined4(String inString)
  {
    this.callfield_UserDefined4 = inString;
  }

  public void setCallfield_UserDefined5(String inString)
  {
    this.callfield_UserDefined5 = inString;
  }

  public void setCallfield_UserDefined6(String inString)
  {
    this.callfield_UserDefined6 = inString;
  }

  public void setCallfield_UserDefined7(String inString)
  {
    this.callfield_UserDefined7 = inString;
  }

  public void setCallfield_UserDefined8(String inString)
  {
    this.callfield_UserDefined8 = inString;
  }

  public void setCallfield_UserDefined9(String inString)
  {
    this.callfield_UserDefined9 = inString;
  }

  public void setCallfield_UserDefined10(String inString)
  {
    this.callfield_UserDefined10 = inString;
  }

  public void setCallfield_UserDefined11(String inString)
  {
    this.callfield_UserDefined11 = inString;
  }

  public void setCallfield_UserDefined12(String inString)
  {
    this.callfield_UserDefined12 = inString;
  }

  public void setCallfield_UserDefined13(String inString)
  {
    this.callfield_UserDefined13 = inString;
  }

  public void setCallfield_UserDefined14(String inString)
  {
    this.callfield_UserDefined14 = inString;
  }

  public void setCallfield_UserDefined15(String inString)
  {
    this.callfield_UserDefined15 = inString;
  }

  public void setCallfield_UserDefined16(String inString)
  {
    this.callfield_UserDefined16 = inString;
  }

  public void setCallfield_UserDefined17(String inString)
  {
    this.callfield_UserDefined17 = inString;
  }

  public void setCallfield_UserDefined18(String inString)
  {
    this.callfield_UserDefined18 = inString;
  }

  public void setCallfield_UserDefined19(String inString)
  {
    this.callfield_UserDefined19 = inString;
  }

  public void setCallfield_UserDefined20(String inString)
  {
    this.callfield_UserDefined20 = inString;
  }

  public void setCallfield_AgentIndex(int callfield_AgentIndex) {
    this.callfield_AgentIndex = callfield_AgentIndex;
  }

  public void setCallfield_CallbackMemo(String callfield_CallbackMemo) {
    this.callfield_CallbackMemo = callfield_CallbackMemo;
  }

  public void setCallfield_CallCategory(int callfield_CallCategory) {
    this.callfield_CallCategory = callfield_CallCategory;
  }

  public void setCallfield_CallbackFlag(int callfield_CallbackFlag) {
    this.callfield_CallbackFlag = callfield_CallbackFlag;
  }

  public void setCallfield_UserDefined1key(String callfield_UserDefined1key) {
    this.callfield_UserDefined1key = callfield_UserDefined1key;
  }

  public void setCallfield_UserDefined2key(String callfield_UserDefined2key) {
    this.callfield_UserDefined2key = callfield_UserDefined2key;
  }

  public void setCallfield_UserDefined3key(String callfield_UserDefined3key) {
    this.callfield_UserDefined3key = callfield_UserDefined3key;
  }

  public void setCallfield_UserDefined4key(String callfield_UserDefined4key) {
    this.callfield_UserDefined4key = callfield_UserDefined4key;
  }

  public void setCallfield_UserDefined5key(String callfield_UserDefined5key) {
    this.callfield_UserDefined5key = callfield_UserDefined5key;
  }

  public void setCallfield_UserDefined6key(String callfield_UserDefined6key) {
    this.callfield_UserDefined6key = callfield_UserDefined6key;
  }

  public void setCallfield_UserDefined7key(String callfield_UserDefined7key) {
    this.callfield_UserDefined7key = callfield_UserDefined7key;
  }

  public void setCallfield_UserDefined8key(String callfield_UserDefined8key) {
    this.callfield_UserDefined8key = callfield_UserDefined8key;
  }

  public void setCallfield_UserDefined9key(String callfield_UserDefined9key) {
    this.callfield_UserDefined9key = callfield_UserDefined9key;
  }

  public void setCallfield_UserDefined10key(String callfield_UserDefined10key) {
    this.callfield_UserDefined10key = callfield_UserDefined10key;
  }

  public void setCallfield_UserDefined11key(String callfield_UserDefined11key) {
    this.callfield_UserDefined11key = callfield_UserDefined11key;
  }

  public void setCallfield_UserDefined12key(String callfield_UserDefined12key) {
    this.callfield_UserDefined12key = callfield_UserDefined12key;
  }

  public void setCallfield_UserDefined13key(String callfield_UserDefined13key) {
    this.callfield_UserDefined13key = callfield_UserDefined13key;
  }

  public void setCallfield_UserDefined14key(String callfield_UserDefined14key) {
    this.callfield_UserDefined14key = callfield_UserDefined14key;
  }

  public void setCallfield_UserDefined15key(String callfield_UserDefined15key) {
    this.callfield_UserDefined15key = callfield_UserDefined15key;
  }

  public void setCallfield_UserDefined16key(String callfield_UserDefined16key) {
    this.callfield_UserDefined16key = callfield_UserDefined16key;
  }

  public void setCallfield_UserDefined17key(String callfield_UserDefined17key) {
    this.callfield_UserDefined17key = callfield_UserDefined17key;
  }

  public void setCallfield_UserDefined18key(String callfield_UserDefined18key) {
    this.callfield_UserDefined18key = callfield_UserDefined18key;
  }

  public void setCallfield_UserDefined19key(String callfield_UserDefined19key) {
    this.callfield_UserDefined19key = callfield_UserDefined19key;
  }

  public void setCallfield_UserDefined20key(String callfield_UserDefined20key) {
    this.callfield_UserDefined20key = callfield_UserDefined20key;
  }

  public void setCallfield_CallID(int callfield_CallID) {
    this.callfield_CallID = callfield_CallID;
  }

  public void setCallfield_EmailMsgId(int callfield_EmailMsgId) {
    this.callfield_EmailMsgId = callfield_EmailMsgId;
  }

  public void setCallfield_CallTableRecordNum(int callfield_CallTableRecordNum) {
    this.callfield_CallTableRecordNum = callfield_CallTableRecordNum;
  }

  public void setCallfield_SwitchCallID(int callfield_SwitchCallID) {
    this.callfield_SwitchCallID = callfield_SwitchCallID;
  }

  public void setCallfield_EmailQId(int callfield_EmailQId) {
    this.callfield_EmailQId = callfield_EmailQId;
  }

  public void setCallfield_EmailForwardTo(String callfield_EmailForwardTo) {
    this.callfield_EmailForwardTo = callfield_EmailForwardTo;
  }

  public void setCallfield_Preemptive(int callfield_Preemptive) {
    this.callfield_Preemptive = callfield_Preemptive;
  }

  public void setCallfield_StatusReason(int callfield_StatusReason) {
    this.callfield_StatusReason = callfield_StatusReason;
  }

  public void setCallfield_RequiredRejectReason(boolean callfield_RequiredRejectReason) {
    this.callfield_RequiredRejectReason = callfield_RequiredRejectReason;
  }

  public void setCallfield_RequiredResponse(boolean callfield_RequiredResponse) {
    this.callfield_RequiredResponse = callfield_RequiredResponse;
  }

  public void setCallfield_DialModeCode(int callfield_DialModeCode) {
    this.callfield_DialModeCode = callfield_DialModeCode;
  }

  public void setCallfield_EventCode(int callfield_EventCode) {
    this.callfield_EventCode = callfield_EventCode;
  }

  public void setCallfield_OriginalServiceID(int callfield_OriginalServiceID) {
    this.callfield_OriginalServiceID = callfield_OriginalServiceID;
  }

  public void setCallfield_TimeoutInSeconds(int callfield_TimeoutInSeconds) {
    this.callfield_TimeoutInSeconds = callfield_TimeoutInSeconds;
  }

  public void setCallfield_PlayAudioAlert(boolean callfield_PlayAudioAlert) {
    this.callfield_PlayAudioAlert = callfield_PlayAudioAlert;
  }

  public int getPbxaodserviceid()
  {
    return this.pbxaodserviceid;
  }
  public void setPbxaodserviceid(int pbxaodserviceid) {
	    this.pbxaodserviceid = pbxaodserviceid;
  }

  public String getString(String key) {
    if (key == null) {
      return "";
    }

    String name = key.trim();
    if (name.equalsIgnoreCase("Callfield_ANI"))
      return getCallfield_ANI();
    if (name.equalsIgnoreCase("Callfield_CallerID"))
      return getCallfield_CallerID();
    if (name.equalsIgnoreCase("Callfield_DNIS"))
      return getCallfield_DNIS();
    if (name.equalsIgnoreCase("Callfield_FirstName"))
      return getCallfield_FirstName();
    if (name.equalsIgnoreCase("Callfield_LastName"))
      return getCallfield_LastName();
    if (name.equalsIgnoreCase("Callfield_PhoneNumber"))
      return getCallfield_PhoneNumber();
    if (name.equalsIgnoreCase("Callfield_ServiceName"))
      return getCallfield_ServiceName();
    if (name.equalsIgnoreCase("Callfield_EmailForwardTo"))
      return getCallfield_EmailForwardTo();
    if (name.equalsIgnoreCase("Callfield_CallbackMemo"))
      return getCallfield_CallbackMemo();
    if (name.equalsIgnoreCase("Callfield_CallbackName"))
      return getCallfield_CallbackName();
    if (name.equalsIgnoreCase("Callfield_TableName"))
      return getCallfield_TableName();
    if (name.equalsIgnoreCase("Callfield_UserDefined1"))
      return getCallfield_UserDefined1();
    if (name.equalsIgnoreCase("Callfield_UserDefined1key"))
      return getCallfield_UserDefined1key();
    if (name.equalsIgnoreCase("Callfield_UserDefined2"))
      return getCallfield_UserDefined2();
    if (name.equalsIgnoreCase("Callfield_UserDefined2key"))
      return getCallfield_UserDefined2key();
    if (name.equalsIgnoreCase("Callfield_UserDefined3"))
      return getCallfield_UserDefined3();
    if (name.equalsIgnoreCase("Callfield_UserDefined3key"))
      return getCallfield_UserDefined3key();
    if (name.equalsIgnoreCase("Callfield_UserDefined4"))
      return getCallfield_UserDefined4();
    if (name.equalsIgnoreCase("Callfield_UserDefined4key"))
      return getCallfield_UserDefined4key();
    if (name.equalsIgnoreCase("Callfield_UserDefined5"))
      return getCallfield_UserDefined5();
    if (name.equalsIgnoreCase("Callfield_UserDefined5key"))
      return getCallfield_UserDefined5key();
    if (name.equalsIgnoreCase("Callfield_UserDefined6"))
      return getCallfield_UserDefined6();
    if (name.equalsIgnoreCase("Callfield_UserDefined6key"))
      return getCallfield_UserDefined6key();
    if (name.equalsIgnoreCase("Callfield_UserDefined7"))
      return getCallfield_UserDefined7();
    if (name.equalsIgnoreCase("Callfield_UserDefined7key"))
      return getCallfield_UserDefined7key();
    if (name.equalsIgnoreCase("Callfield_UserDefined8"))
      return getCallfield_UserDefined8();
    if (name.equalsIgnoreCase("Callfield_UserDefined8key"))
      return getCallfield_UserDefined8key();
    if (name.equalsIgnoreCase("Callfield_UserDefined9"))
      return getCallfield_UserDefined9();
    if (name.equalsIgnoreCase("Callfield_UserDefined9key"))
      return getCallfield_UserDefined9key();
    if (name.equalsIgnoreCase("Callfield_UserDefined10"))
      return getCallfield_UserDefined10();
    if (name.equalsIgnoreCase("Callfield_UserDefined10key"))
      return getCallfield_UserDefined10key();
    if (name.equalsIgnoreCase("Callfield_UserDefined11"))
      return getCallfield_UserDefined11();
    if (name.equalsIgnoreCase("Callfield_UserDefined11key"))
      return getCallfield_UserDefined11key();
    if (name.equalsIgnoreCase("Callfield_UserDefined12"))
      return getCallfield_UserDefined12();
    if (name.equalsIgnoreCase("Callfield_UserDefined12key"))
      return getCallfield_UserDefined12key();
    if (name.equalsIgnoreCase("Callfield_UserDefined13"))
      return getCallfield_UserDefined13();
    if (name.equalsIgnoreCase("Callfield_UserDefined13key"))
      return getCallfield_UserDefined13key();
    if (name.equalsIgnoreCase("Callfield_UserDefined14"))
      return getCallfield_UserDefined14();
    if (name.equalsIgnoreCase("Callfield_UserDefined14key"))
      return getCallfield_UserDefined14key();
    if (name.equalsIgnoreCase("Callfield_UserDefined15"))
      return getCallfield_UserDefined15();
    if (name.equalsIgnoreCase("Callfield_UserDefined15key"))
      return getCallfield_UserDefined15key();
    if (name.equalsIgnoreCase("Callfield_UserDefined16"))
      return getCallfield_UserDefined16();
    if (name.equalsIgnoreCase("Callfield_UserDefined16key"))
      return getCallfield_UserDefined16key();
    if (name.equalsIgnoreCase("Callfield_UserDefined17"))
      return getCallfield_UserDefined17();
    if (name.equalsIgnoreCase("Callfield_UserDefined17key"))
      return getCallfield_UserDefined17key();
    if (name.equalsIgnoreCase("Callfield_UserDefined18"))
      return getCallfield_UserDefined18();
    if (name.equalsIgnoreCase("Callfield_UserDefined18key"))
      return getCallfield_UserDefined18key();
    if (name.equalsIgnoreCase("Callfield_UserDefined19"))
      return getCallfield_UserDefined19();
    if (name.equalsIgnoreCase("Callfield_UserDefined19key"))
      return getCallfield_UserDefined19key();
    if (name.equalsIgnoreCase("Callfield_UserDefined20"))
      return getCallfield_UserDefined20();
    if (name.equalsIgnoreCase("Callfield_UserDefined20key")) {
      return getCallfield_UserDefined20key();
    }

    return "";
  }

  public int getInt(String key) {
    if (key == null) {
      return 0;
    }

    String name = key.trim();
    if (name.equalsIgnoreCase("Callfield_AgentIndex"))
      return getCallfield_AgentIndex();
    if (name.equalsIgnoreCase("Callfield_StoreManagerID"))
      return getCallfield_StoreManagerID();
    if (name.equalsIgnoreCase("Callfield_TargetManagerID"))
      return getCallfield_TargetManagerID();
    if (name.equalsIgnoreCase("Callfield_CallTableRecordNum"))
      return getCallfield_CallTableRecordNum();
    if (name.equalsIgnoreCase("Callfield_CallCategory"))
      return getCallfield_CallCategory();
    if (name.equalsIgnoreCase("Callfield_CallID"))
      return getCallfield_CallID();
    if (name.equalsIgnoreCase("Callfield_CallType"))
      return getCallfield_CallType();
    if (name.equalsIgnoreCase("Callfield_CallbackFlag"))
      return getCallfield_CallbackFlag();
    if (name.equalsIgnoreCase("Callfield_EmailMsgId"))
      return getCallfield_EmailMsgId();
    if (name.equalsIgnoreCase("Callfield_EmailQId"))
      return getCallfield_EmailQId();
    if (name.equalsIgnoreCase("Callfield_ServiceID"))
      return getCallfield_ServiceID();
    if (name.equalsIgnoreCase("Callfield_SwitchCallID"))
      return getCallfield_SwitchCallID();
    if (name.equalsIgnoreCase("Callfield_Preemptive"))
      return getCallfield_Preemptive();
    if (name.equalsIgnoreCase("Callfield_StatusReason"))
      return getCallfield_StatusReason();
    if (name.equalsIgnoreCase("Callfield_DialModeCode"))
      return getCallfield_DialModeCode();
    if (name.equalsIgnoreCase("Callfield_EventCode"))
      return getCallfield_EventCode();
    if (name.equalsIgnoreCase("Callfield_OriginalServiceID"))
      return getCallfield_OriginalServiceID();
    if (name.equalsIgnoreCase("Callfield_TimeoutInSeconds")) {
      return getCallfield_TimeoutInSeconds();
    }

    return 0;
  }

  public boolean getBoolean(String key) {
    if (key == null) {
      return false;
    }

    String name = key.trim();
    if (name.equalsIgnoreCase("Callfield_RequiredRejectReason"))
      return isCallfield_RequiredRejectReason();
    if (name.equalsIgnoreCase("Callfield_RequiredResponse"))
      return isCallfield_RequiredResponse();
    if (name.equalsIgnoreCase("Callfield_PlayAudioAlert")) {
      return isCallfield_PlayAudioAlert();
    }
    return false;
  }

  public void setString(String key, String value) {
    if (key == null) {
      return;
    }

    String name = key.trim();
    if (name.equalsIgnoreCase("Callfield_ANI"))
      setCallfield_ANI(value);
    else if (name.equalsIgnoreCase("Callfield_CallerID"))
      setCallfield_CallerID(value);
    else if (name.equalsIgnoreCase("Callfield_DNIS"))
      setCallfield_DNIS(value);
    else if (name.equalsIgnoreCase("Callfield_FirstName"))
      setCallfield_FirstName(value);
    else if (name.equalsIgnoreCase("Callfield_LastName"))
      setCallfield_LastName(value);
    else if (name.equalsIgnoreCase("Callfield_PhoneNumber"))
      setCallfield_PhoneNumber(value);
    else if (name.equalsIgnoreCase("Callfield_ServiceName"))
      setCallfield_ServiceName(value);
    else if (name.equalsIgnoreCase("Callfield_EmailForwardTo"))
      setCallfield_EmailForwardTo(value);
    else if (name.equalsIgnoreCase("Callfield_CallbackMemo"))
      setCallfield_CallbackMemo(value);
    else if (name.equalsIgnoreCase("Callfield_CallbackName"))
      setCallfield_CallbackName(value);
    else if (name.equalsIgnoreCase("Callfield_TableName"))
      setCallfield_TableName(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined1"))
      setCallfield_UserDefined1(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined1key"))
      setCallfield_UserDefined1key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined2"))
      setCallfield_UserDefined2(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined2key"))
      setCallfield_UserDefined2key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined3"))
      setCallfield_UserDefined3(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined3key"))
      setCallfield_UserDefined3key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined4"))
      setCallfield_UserDefined4(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined4key"))
      setCallfield_UserDefined4key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined5"))
      setCallfield_UserDefined5(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined5key"))
      setCallfield_UserDefined5key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined6"))
      setCallfield_UserDefined6(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined6key"))
      setCallfield_UserDefined6key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined7"))
      setCallfield_UserDefined7(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined7key"))
      setCallfield_UserDefined7key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined8"))
      setCallfield_UserDefined8(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined8key"))
      setCallfield_UserDefined8key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined9"))
      setCallfield_UserDefined9(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined9key"))
      setCallfield_UserDefined9key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined10"))
      setCallfield_UserDefined10(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined10key"))
      setCallfield_UserDefined10key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined11"))
      setCallfield_UserDefined11(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined11key"))
      setCallfield_UserDefined11key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined12"))
      setCallfield_UserDefined12(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined12key"))
      setCallfield_UserDefined12key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined13"))
      setCallfield_UserDefined13(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined13key"))
      setCallfield_UserDefined13key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined14"))
      setCallfield_UserDefined14(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined14key"))
      setCallfield_UserDefined14key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined15"))
      setCallfield_UserDefined15(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined15key"))
      setCallfield_UserDefined15key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined16"))
      setCallfield_UserDefined16(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined16key"))
      setCallfield_UserDefined16key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined17"))
      setCallfield_UserDefined17(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined17key"))
      setCallfield_UserDefined17key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined18"))
      setCallfield_UserDefined18(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined18key"))
      setCallfield_UserDefined18key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined19"))
      setCallfield_UserDefined19(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined19key"))
      setCallfield_UserDefined19key(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined20"))
      setCallfield_UserDefined20(value);
    else if (name.equalsIgnoreCase("Callfield_UserDefined20key"))
      setCallfield_UserDefined20key(value);
  }

  public void setInt(String key, int value)
  {
    if (key == null) {
      return;
    }

    String name = key.trim();
    if (name.equalsIgnoreCase("Callfield_AgentIndex"))
      setCallfield_AgentIndex(value);
    else if (name.equalsIgnoreCase("Callfield_CallCategory"))
      setCallfield_CallCategory(value);
    else if (name.equalsIgnoreCase("Callfield_CallID"))
      setCallfield_CallID(value);
    else if (name.equalsIgnoreCase("Callfield_CallType"))
      setCallfield_CallType(value);
    else if (name.equalsIgnoreCase("Callfield_CallbackFlag"))
      setCallfield_CallbackFlag(value);
    else if (name.equalsIgnoreCase("Callfield_EmailMsgId"))
      setCallfield_EmailMsgId(value);
    else if (name.equalsIgnoreCase("Callfield_EmailQId"))
      setCallfield_EmailQId(value);
    else if (name.equalsIgnoreCase("Callfield_ServiceID"))
      setCallfield_ServiceID(value);
    else if (name.equalsIgnoreCase("Callfield_StoreManagerID"))
      setCallfield_StoreManagerID(value);
    else if (name.equalsIgnoreCase("Callfield_TargetManagerID"))
      setCallfield_TargetManagerID(value);
    else if (name.equalsIgnoreCase("Callfield_CallTableRecordNum"))
      setCallfield_CallTableRecordNum(value);
    else if (name.equalsIgnoreCase("Callfield_SwitchCallID"))
      setCallfield_SwitchCallID(value);
    else if (name.equalsIgnoreCase("Callfield_Preemptive"))
      setCallfield_Preemptive(value);
    else if (name.equalsIgnoreCase("Callfield_StatusReason"))
      setCallfield_StatusReason(value);
    else if (name.equalsIgnoreCase("Callfield_DialModeCode"))
      setCallfield_DialModeCode(value);
    else if (name.equalsIgnoreCase("Callfield_EventCode"))
      setCallfield_EventCode(value);
    else if (name.equalsIgnoreCase("Callfield_OriginalServiceID"))
      setCallfield_OriginalServiceID(value);
    else if (name.equalsIgnoreCase("Callfield_TimeoutInSeconds"))
      setCallfield_TimeoutInSeconds(value);
  }

  public void setBoolean(String key, boolean value)
  {
    if (key == null) {
      return;
    }

    String name = key.trim();
    if (name.equalsIgnoreCase("Callfield_RequiredRejectReason"))
      setCallfield_RequiredRejectReason(value);
    else if (name.equalsIgnoreCase("Callfield_RequiredResponse"))
      setCallfield_RequiredResponse(value);
    else if (name.equalsIgnoreCase("Callfield_PlayAudioAlert"))
      setCallfield_PlayAudioAlert(value);
  }

  public void update(String ani, String callerID, String dnis, String firstName, String lastName, String phoneNumber, String serviceName, String emailForwardTo, String callbackMemo, String callbackName, String tableName, String userDefined1, String userDefined1key, String userDefined2, String userDefined2key, String userDefined3, String userDefined3key, String userDefined4, String userDefined4key, String userDefined5, String userDefined5key, String userDefined6, String userDefined6key, String userDefined7, String userDefined7key, String userDefined8, String userDefined8key, String userDefined9, String userDefined9key, String userDefined10, String userDefined10key, String userDefined11, String userDefined11key, String userDefined12, String userDefined12key, String userDefined13, String userDefined13key, String userDefined14, String userDefined14key, String userDefined15, String userDefined15key, String userDefined16, String userDefined16key, String userDefined17, String userDefined17key, String userDefined18, String userDefined18key, String userDefined19, String userDefined19key, String userDefined20, String userDefined20key, int agentIndex, int callCategory, int callID, int callType, int callbackFlag, int emailMsgId, int emailQId, int serviceID, int storeManagerID, int targetManagerID, int callTableRecordNum, int switchCallID, int preemptive, int statusReason, boolean requiredRejectReason, boolean requiredResponse, int dialModeCode, int eventCode, int originalServiceID, int timeoutInSeconds, boolean playAudioAlert)
  {
    setCallfield_ANI(ani);
    setCallfield_CallerID(callerID);
    setCallfield_DNIS(dnis);
    setCallfield_FirstName(firstName);
    setCallfield_LastName(lastName);
    setCallfield_PhoneNumber(phoneNumber);
    setCallfield_ServiceName(serviceName);
    setCallfield_EmailForwardTo(emailForwardTo);
    setCallfield_CallbackMemo(callbackMemo);
    setCallfield_CallbackName(callbackName);
    setCallfield_TableName(tableName);
    setCallfield_UserDefined1(userDefined1);
    setCallfield_UserDefined1key(userDefined1key);
    setCallfield_UserDefined2(userDefined2);
    setCallfield_UserDefined2key(userDefined2key);
    setCallfield_UserDefined3(userDefined3);
    setCallfield_UserDefined3key(userDefined3key);
    setCallfield_UserDefined4(userDefined4);
    setCallfield_UserDefined4key(userDefined4key);
    setCallfield_UserDefined5(userDefined5);
    setCallfield_UserDefined5key(userDefined5key);
    setCallfield_UserDefined6(userDefined6);
    setCallfield_UserDefined6key(userDefined6key);
    setCallfield_UserDefined7(userDefined7);
    setCallfield_UserDefined7key(userDefined7key);
    setCallfield_UserDefined8(userDefined8);
    setCallfield_UserDefined8key(userDefined8key);
    setCallfield_UserDefined9(userDefined9);
    setCallfield_UserDefined9key(userDefined9key);
    setCallfield_UserDefined10(userDefined10);
    setCallfield_UserDefined10key(userDefined10key);
    setCallfield_UserDefined11(userDefined11);
    setCallfield_UserDefined11key(userDefined11key);
    setCallfield_UserDefined12(userDefined12);
    setCallfield_UserDefined12key(userDefined12key);
    setCallfield_UserDefined13(userDefined13);
    setCallfield_UserDefined13key(userDefined13key);
    setCallfield_UserDefined14(userDefined14);
    setCallfield_UserDefined14key(userDefined14key);
    setCallfield_UserDefined15(userDefined15);
    setCallfield_UserDefined15key(userDefined15key);
    setCallfield_UserDefined16(userDefined16);
    setCallfield_UserDefined16key(userDefined16key);
    setCallfield_UserDefined17(userDefined17);
    setCallfield_UserDefined17key(userDefined17key);
    setCallfield_UserDefined18(userDefined18);
    setCallfield_UserDefined18key(userDefined18key);
    setCallfield_UserDefined19(userDefined19);
    setCallfield_UserDefined19key(userDefined19key);
    setCallfield_UserDefined20(userDefined20);
    setCallfield_UserDefined20key(userDefined20key);

    setCallfield_AgentIndex(agentIndex);
    setCallfield_CallCategory(callCategory);
    setCallfield_CallID(callID);
    setCallfield_CallType(callType);
    setCallfield_CallbackFlag(callbackFlag);
    setCallfield_EmailMsgId(emailMsgId);
    setCallfield_EmailQId(emailQId);
    setCallfield_ServiceID(serviceID);
    setCallfield_StoreManagerID(storeManagerID);
    setCallfield_TargetManagerID(targetManagerID);
    setCallfield_CallTableRecordNum(callTableRecordNum);
    setCallfield_SwitchCallID(switchCallID);
    setCallfield_Preemptive(preemptive);
    setCallfield_StatusReason(statusReason);
    setCallfield_RequiredRejectReason(requiredRejectReason);
    setCallfield_RequiredResponse(requiredResponse);
    setCallfield_DialModeCode(dialModeCode);
    setCallfield_EventCode(eventCode);
    setCallfield_OriginalServiceID(originalServiceID);
    setCallfield_TimeoutInSeconds(timeoutInSeconds);
    setCallfield_PlayAudioAlert(playAudioAlert);
  }

  public void update(UDScreenPop udScreenPop) {
	    UDCallInfo callInfo = udScreenPop.getCallInfo();
	    setCallfield_AgentIndex(udScreenPop.getAgentIndex());
	    setCallfield_Preemptive(udScreenPop.getPreemptive());
	    setCallfield_StatusReason(udScreenPop.getStatusReason());
	    setCallfield_RequiredRejectReason(udScreenPop.isRequiredRejectReason());
	    setCallfield_RequiredResponse(udScreenPop.isRequiredResponse());
	    setCallfield_DialModeCode(callInfo.getCallbackData().getDialModeCode());
	    setCallfield_EventCode(callInfo.getOtherData().getEventCode());
	    setCallfield_OriginalServiceID(callInfo.getOtherData().getOriginalServiceID());
	    setCallfield_TimeoutInSeconds(udScreenPop.getTimeoutForAcceptCall());
	    setCallfield_PlayAudioAlert(udScreenPop.isPlayAudioAlert());
	    setCallfield_SwitchCallID(udScreenPop.getSwitchCallID());
	    setCallfield_TableName(callInfo.getAodData().getTableName());
	    setCallfield_StoreManagerID(callInfo.getAodData().getStoreManagerId());
	    setCallfield_TargetManagerID(callInfo.getAodData().getTargetManagerId());
	    setCallfield_CallTableRecordNum(callInfo.getAodData().getCallTableRecordNum());
	    setCallfield_CallbackMemo(callInfo.getCallbackData().getCallbackMemo());
	    setCallfield_CallCategory(callInfo.getOtherData().getCallCategory());
	    setCallfield_CallbackFlag(callInfo.getCallbackData().getCallBackFlag());
	    setCallfield_ANI(callInfo.getScreenData().getAni());
	    setCallfield_CallerID(callInfo.getScreenData().getCallerID());
	    setCallfield_CallType(callInfo.getOtherData().getCallType());
	    setCallfield_DNIS(callInfo.getScreenData().getDnis());
	    setCallfield_FirstName(callInfo.getScreenData().getFirstName());
	    setCallfield_LastName(callInfo.getScreenData().getLastName());
	    setCallfield_PhoneNumber(callInfo.getScreenData().getPhoneNumber());
	    setCallfield_ServiceID(callInfo.getOtherData().getServiceID());
	    setCallfield_EmailMsgId(callInfo.getEmailData().getMessageId());
	    setCallfield_EmailQId(callInfo.getEmailData().getMailQueId());
	    setCallfield_EmailForwardTo(callInfo.getEmailData().getForwardToMBox());
	    setCallfield_ServiceName(udScreenPop.getServiceName());
	    setPbxaodserviceid(udScreenPop.getPbxaodserviceid());
	    setCallfield_CallID(udScreenPop.getCallID());

	    updateCallInfoUserItems(callInfo.getUserDefinedData().getUserDefinedItems());

	    if (getCallfield_ServiceName() == null)
	      setCallfield_ServiceName("");
	  }

	  public void update(int agentIndex, UDPreview udPreview)
	  {
	    UDCallInfo callInfo = udPreview.getCallInfo();

	    setCallfield_AgentIndex(agentIndex);
	    setCallfield_Preemptive(udPreview.getPreemptive());
	    setCallfield_StatusReason(udPreview.getStatusReason());
	    setCallfield_ServiceName(udPreview.getServiceName());
	    setCallfield_CallID(udPreview.getCallID());

	    setCallfield_RequiredRejectReason(false);
	    setCallfield_RequiredResponse(false);
	    setCallfield_TimeoutInSeconds(0);
	    setCallfield_PlayAudioAlert(false);
	    setCallfield_SwitchCallID(0);

	    setCallfield_DialModeCode(callInfo.getCallbackData().getDialModeCode());
	    setCallfield_EventCode(callInfo.getOtherData().getEventCode());
	    setCallfield_OriginalServiceID(callInfo.getOtherData().getOriginalServiceID());
	    setCallfield_TableName(callInfo.getAodData().getTableName());
	    setCallfield_StoreManagerID(callInfo.getAodData().getStoreManagerId());
	    setCallfield_TargetManagerID(callInfo.getAodData().getTargetManagerId());
	    setCallfield_CallTableRecordNum(callInfo.getAodData().getCallTableRecordNum());
	    setCallfield_CallbackMemo(callInfo.getCallbackData().getCallbackMemo());
	    setCallfield_CallCategory(callInfo.getOtherData().getCallCategory());
	    setCallfield_CallbackFlag(callInfo.getCallbackData().getCallBackFlag());
	    setCallfield_ANI(callInfo.getScreenData().getAni());
	    setCallfield_CallerID(callInfo.getScreenData().getCallerID());
	    setCallfield_CallType(callInfo.getOtherData().getCallType());
	    setCallfield_DNIS(callInfo.getScreenData().getDnis());
	    setCallfield_FirstName(callInfo.getScreenData().getFirstName());
	    setCallfield_LastName(callInfo.getScreenData().getLastName());
	    setCallfield_PhoneNumber(callInfo.getScreenData().getPhoneNumber());
	    setCallfield_ServiceID(callInfo.getOtherData().getServiceID());
	    setCallfield_EmailMsgId(callInfo.getEmailData().getMessageId());
	    setCallfield_EmailQId(callInfo.getEmailData().getMailQueId());
	    setCallfield_EmailForwardTo(callInfo.getEmailData().getForwardToMBox());

	    updateCallInfoUserItems(callInfo.getUserDefinedData().getUserDefinedItems());

	    if (getCallfield_ServiceName() == null)
	      setCallfield_ServiceName("");
	  }

	  public void getUserDefinedInfo(String[] userDefinedKey, String[] userDefinedValue)
	  {
	    userDefinedKey[0] = getCallfield_UserDefined1key();
	    userDefinedValue[0] = getCallfield_UserDefined1();
	    userDefinedKey[1] = getCallfield_UserDefined2key();
	    userDefinedValue[1] = getCallfield_UserDefined2();
	    userDefinedKey[2] = getCallfield_UserDefined3key();
	    userDefinedValue[2] = getCallfield_UserDefined3();
	    userDefinedKey[3] = getCallfield_UserDefined4key();
	    userDefinedValue[3] = getCallfield_UserDefined4();
	    userDefinedKey[4] = getCallfield_UserDefined5key();
	    userDefinedValue[4] = getCallfield_UserDefined5();
	    userDefinedKey[5] = getCallfield_UserDefined6key();
	    userDefinedValue[5] = getCallfield_UserDefined6();
	    userDefinedKey[6] = getCallfield_UserDefined7key();
	    userDefinedValue[6] = getCallfield_UserDefined7();
	    userDefinedKey[7] = getCallfield_UserDefined8key();
	    userDefinedValue[7] = getCallfield_UserDefined8();
	    userDefinedKey[8] = getCallfield_UserDefined9key();
	    userDefinedValue[8] = getCallfield_UserDefined9();
	    userDefinedKey[9] = getCallfield_UserDefined10key();
	    userDefinedValue[9] = getCallfield_UserDefined10();
	    userDefinedKey[10] = getCallfield_UserDefined11key();
	    userDefinedValue[10] = getCallfield_UserDefined11();
	    userDefinedKey[11] = getCallfield_UserDefined12key();
	    userDefinedValue[11] = getCallfield_UserDefined12();
	    userDefinedKey[12] = getCallfield_UserDefined13key();
	    userDefinedValue[12] = getCallfield_UserDefined13();
	    userDefinedKey[13] = getCallfield_UserDefined14key();
	    userDefinedValue[13] = getCallfield_UserDefined14();
	    userDefinedKey[14] = getCallfield_UserDefined15key();
	    userDefinedValue[14] = getCallfield_UserDefined15();
	    userDefinedKey[15] = getCallfield_UserDefined16key();
	    userDefinedValue[15] = getCallfield_UserDefined16();
	    userDefinedKey[16] = getCallfield_UserDefined17key();
	    userDefinedValue[16] = getCallfield_UserDefined17();
	    userDefinedKey[17] = getCallfield_UserDefined18key();
	    userDefinedValue[17] = getCallfield_UserDefined18();
	    userDefinedKey[18] = getCallfield_UserDefined19key();
	    userDefinedValue[18] = getCallfield_UserDefined19();
	    userDefinedKey[19] = getCallfield_UserDefined20key();
	    userDefinedValue[19] = getCallfield_UserDefined20();
	  }

	  private void updateCallInfoUserItems(UDCallInfoUserDefinedItem[] tmpArray) {
	    String[] userDefinedKey = new String[20];
	    String[] userDefinedValue = new String[20];

	    for (int i = 0; i < 20; ++i) {
	      userDefinedKey[i] = "";
	      userDefinedValue[i] = "";
	    }

	    for (int i = 0; (i < 20) && (i < tmpArray.length); ++i) {
	      UDCallInfoUserDefinedItem tmpItem = tmpArray[i];
	      userDefinedKey[i] = tmpItem.getKey();
	      userDefinedValue[i] = tmpItem.getValue();
	    }

	    setCallfield_UserDefined1(userDefinedValue[0]);
	    setCallfield_UserDefined1key(userDefinedKey[0]);
	    setCallfield_UserDefined2(userDefinedValue[1]);
	    setCallfield_UserDefined2key(userDefinedKey[1]);
	    setCallfield_UserDefined3(userDefinedValue[2]);
	    setCallfield_UserDefined3key(userDefinedKey[2]);
	    setCallfield_UserDefined4(userDefinedValue[3]);
	    setCallfield_UserDefined4key(userDefinedKey[3]);
	    setCallfield_UserDefined5(userDefinedValue[4]);
	    setCallfield_UserDefined5key(userDefinedKey[4]);
	    setCallfield_UserDefined6(userDefinedValue[5]);
	    setCallfield_UserDefined6key(userDefinedKey[5]);
	    setCallfield_UserDefined7(userDefinedValue[6]);
	    setCallfield_UserDefined7key(userDefinedKey[6]);
	    setCallfield_UserDefined8(userDefinedValue[7]);
	    setCallfield_UserDefined8key(userDefinedKey[7]);
	    setCallfield_UserDefined9(userDefinedValue[8]);
	    setCallfield_UserDefined9key(userDefinedKey[8]);
	    setCallfield_UserDefined10(userDefinedValue[9]);
	    setCallfield_UserDefined10key(userDefinedKey[9]);
	    setCallfield_UserDefined11(userDefinedValue[10]);
	    setCallfield_UserDefined11key(userDefinedKey[10]);
	    setCallfield_UserDefined12(userDefinedValue[11]);
	    setCallfield_UserDefined12key(userDefinedKey[11]);
	    setCallfield_UserDefined13(userDefinedValue[12]);
	    setCallfield_UserDefined13key(userDefinedKey[12]);
	    setCallfield_UserDefined14(userDefinedValue[13]);
	    setCallfield_UserDefined14key(userDefinedKey[13]);
	    setCallfield_UserDefined15(userDefinedValue[14]);
	    setCallfield_UserDefined15key(userDefinedKey[14]);
	    setCallfield_UserDefined16(userDefinedValue[15]);
	    setCallfield_UserDefined16key(userDefinedKey[15]);
	    setCallfield_UserDefined17(userDefinedValue[16]);
	    setCallfield_UserDefined17key(userDefinedKey[16]);
	    setCallfield_UserDefined18(userDefinedValue[17]);
	    setCallfield_UserDefined18key(userDefinedKey[17]);
	    setCallfield_UserDefined19(userDefinedValue[18]);
	    setCallfield_UserDefined19key(userDefinedKey[18]);
	    setCallfield_UserDefined20(userDefinedValue[19]);
	    setCallfield_UserDefined20key(userDefinedKey[19]);
	  }
}