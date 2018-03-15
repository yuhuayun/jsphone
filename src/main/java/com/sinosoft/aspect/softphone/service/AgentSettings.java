package com.sinosoft.aspect.softphone.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyCallDataDefFields;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyDispositions;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyService;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNotifyServiceSettings;


class AgentSettings {

  private static final Logger LOGGER = Logger.getLogger(AgentSettings.class.getName());
  private int wrapRedAlertSecs;
  private int noDispMaxWrapSecs;
  private int reqWrapOrReqDisp;
  private int service_id;
  private String serviceName;
  private int callbackdays;
  private int displayCallbackTimeWarningF;
  private int calldatadefid;
  private int dispositionPlanID;
  private int service_type_id;
  private boolean threeWayAllowed;
  private boolean consultHangupAllowed;
  private boolean displayACDRecordings;
  private boolean previewNumChangeAllowed;
  private int parkDelay;
  private boolean parkState;
  private int previewtimeoutinsecs;
  private boolean wramTransfer;
  private boolean dialAllowed;
  private boolean holdAllowed;
  private boolean kBAllowed;
  private boolean transferAllowed;
  private boolean hangupAllowed;
  private boolean consultAllowed;
  private boolean blindTransferAllowed;
  private boolean recordAllowed;
  private boolean playAllowed;
  private boolean chatRecordAllowed;
  private boolean chatDisconnectAllowed;
  private boolean preemptive;
  private boolean reqManualDialService;
  private boolean showManualDialServiceList;
  private boolean agentCallbacksAllowedToAgent;
  private String scriptName;
  private boolean serviceCallbacksAllowedToAgent;
  private boolean serviceCallbacksAllowedToService;
  private int outgoingmask;
  private int scriptID;
  private int pbxAODServiceID;
  private DispositionCodeBean[] dispCodes;
  private CallDataDefBean[] callDataDefs;

  AgentSettings()
  {
    this.wrapRedAlertSecs = 0;

    this.noDispMaxWrapSecs = 0;

    this.reqWrapOrReqDisp = 0;
    this.service_id = 0;
    this.serviceName = "";
    this.callbackdays = -1;
    this.displayCallbackTimeWarningF = 1;

    this.calldatadefid = 0;
    this.dispositionPlanID = 0;
    this.service_type_id = 0;

    this.threeWayAllowed = true;
    this.consultHangupAllowed = true;
    this.displayACDRecordings = true;
    this.previewNumChangeAllowed = true;

    this.parkDelay = 0;
    this.parkState = true;
    this.previewtimeoutinsecs = 0;
    this.wramTransfer = true;
    this.dialAllowed = true;
    this.holdAllowed = true;
    this.kBAllowed = true;
    this.transferAllowed = true;
    this.hangupAllowed = true;
    this.consultAllowed = true;
    this.blindTransferAllowed = true;
    this.recordAllowed = true;
    this.playAllowed = true;
    this.chatRecordAllowed = true;
    this.chatDisconnectAllowed = true;

    this.preemptive = true;

    this.reqManualDialService = true;
    this.showManualDialServiceList = true;
    this.agentCallbacksAllowedToAgent = true;
    this.scriptName = "";
    this.serviceCallbacksAllowedToAgent = true;
    this.serviceCallbacksAllowedToService = true;
    this.outgoingmask = 0;
    this.scriptID = 0;
    this.pbxAODServiceID = 0;

    this.dispCodes = null;
    this.callDataDefs = null; }

  public boolean isPreviewNumChangeAllowed() {
    return this.previewNumChangeAllowed;
  }

  public boolean isDisplayACDRecordings() {
    return this.displayACDRecordings;
  }

  public boolean isConsultHangupAllowed() {
    return this.consultHangupAllowed;
  }

  public boolean isThreeWayAllowed() {
    return this.threeWayAllowed;
  }

  public int getReqWrapOrReqDisp() {
    return this.reqWrapOrReqDisp;
  }

  public int getNoDispMaxWrapSecs() {
    return this.noDispMaxWrapSecs;
  }

  public int getWrapRedAlertSecs() {
    return this.wrapRedAlertSecs;
  }

  public void setPreviewNumChangeAllowed(boolean PreviewNumChangeAllowed) {
    this.previewNumChangeAllowed = PreviewNumChangeAllowed;
  }

  public void setDisplayACDRecordings(boolean DisplayACDRecordings) {
    this.displayACDRecordings = DisplayACDRecordings;
  }

  public void setConsultHangupAllowed(boolean ConsultHangupAllowed) {
    this.consultHangupAllowed = ConsultHangupAllowed;
  }

  public void setThreeWayAllowed(boolean ThreeWayAllowed) {
    this.threeWayAllowed = ThreeWayAllowed;
  }

  public void setReqWrapOrReqDisp(int ReqWrapOrReqDisp) {
    this.reqWrapOrReqDisp = ReqWrapOrReqDisp;
  }

  public void setNoDispMaxWrapSecs(int NoDispMaxWrapSecs) {
    this.noDispMaxWrapSecs = NoDispMaxWrapSecs;
  }

  public void setWrapRedAlertSecs(int WrapRedAlertSecs) {
    this.wrapRedAlertSecs = WrapRedAlertSecs;
  }

  public void setDialAllowed(boolean DialAllowed) {
    this.dialAllowed = DialAllowed;
  }

  public boolean isDialAllowed() {
    return this.dialAllowed;
  }

  public void setHoldAllowed(boolean HoldAllowed) {
    this.holdAllowed = HoldAllowed;
  }

  public boolean isHoldAllowed() {
    return this.holdAllowed;
  }

  public void setKBAllowed(boolean kBAllowed) {
    this.kBAllowed = kBAllowed;
  }

  public boolean isKBAllowed() {
    return this.kBAllowed;
  }

  public void setTransferAllowed(boolean TransferAllowed) {
    this.transferAllowed = TransferAllowed;
  }

  public boolean isTransferAllowed() {
    return this.transferAllowed;
  }

  public void setHangupAllowed(boolean HangupAllowed) {
    this.hangupAllowed = HangupAllowed;
  }

  public boolean isHangupAllowed() {
    return this.hangupAllowed;
  }

  public void setConsultAllowed(boolean ConsultAllowed) {
    this.consultAllowed = ConsultAllowed;
  }

  public boolean isConsultAllowed() {
    return this.consultAllowed;
  }

  public void setBlindTransferAllowed(boolean BlindTransferAllowed) {
    this.blindTransferAllowed = BlindTransferAllowed;
  }

  public boolean isBlindTransferAllowed() {
    return this.blindTransferAllowed;
  }

  public void setRecordAllowed(boolean RecordAllowed) {
    this.recordAllowed = RecordAllowed;
  }

  public boolean isRecordAllowed() {
    return this.recordAllowed;
  }

  public void setPlayAllowed(boolean PlayAllowed) {
    this.playAllowed = PlayAllowed;
  }

  public boolean isPlayAllowed() {
    return this.playAllowed;
  }

  public void setChatRecordAllowed(boolean ChatRecordAllowed) {
    this.chatRecordAllowed = ChatRecordAllowed;
  }

  public boolean isChatRecordAllowed() {
    return this.chatRecordAllowed;
  }

  public void setChatDisconnectAllowed(boolean ChatDisconnectAllowed) {
    this.chatDisconnectAllowed = ChatDisconnectAllowed;
  }

  public boolean isChatDisconnectAllowed() {
    return this.chatDisconnectAllowed;
  }

  public void setService_id(int service_id) {
    this.service_id = service_id;
  }

  public void setPreemptive(boolean preemptive) {
    this.preemptive = preemptive;
  }

  public void setReqManualDialService(boolean reqManualDialService) {
    this.reqManualDialService = reqManualDialService;
  }

  public void setShowManualDialServiceList(boolean showManualDialServiceList) {
    this.showManualDialServiceList = showManualDialServiceList;
  }

  public void setAgentCallbacksAllowedToAgent(boolean agentCallbacksAllowedToAgent) {
    this.agentCallbacksAllowedToAgent = agentCallbacksAllowedToAgent;
  }

  public void setServiceCallbacksAllowedToAgent(boolean serviceCallbacksAllowedToAgent) {
    this.serviceCallbacksAllowedToAgent = serviceCallbacksAllowedToAgent;
  }

  public void setServiceCallbacksAllowedToService(boolean serviceCallbacksAllowedToService) {
    this.serviceCallbacksAllowedToService = serviceCallbacksAllowedToService;
  }

  public void setDispositionPlanID(int dispositionPlanID) {
    this.dispositionPlanID = dispositionPlanID;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public void setCalldatadefid(int calldatadefid) {
    this.calldatadefid = calldatadefid;
  }

  public void setWramTransfer(boolean wramTransfer) {
    this.wramTransfer = wramTransfer;
  }

  public void setService_type_id(int service_type_id) {
    this.service_type_id = service_type_id;
  }

  public void setPreviewtimeoutinsecs(int previewtimeoutinsecs) {
    this.previewtimeoutinsecs = previewtimeoutinsecs;
  }

  public void setOutgoingmask(int outgoingmask) {
    this.outgoingmask = outgoingmask;
  }

  public void setCallbackdays(int callbackdays) {
    this.callbackdays = callbackdays;
  }

  public void setDisplayCallbackTimeWarningF(int displayCallbackTimeWarningF) {
    this.displayCallbackTimeWarningF = displayCallbackTimeWarningF;
  }

  public void setParkState(boolean parkState) {
    this.parkState = parkState;
  }

  public void setParkDelay(int parkDelay) {
    this.parkDelay = parkDelay;
  }

  public void setScriptID(int scriptID) {
    this.scriptID = scriptID;
  }

  public void setScriptName(String scriptName) {
    this.scriptName = scriptName;
  }

  public void setPbxAODServiceID(int pbxAODServiceID) {
    this.pbxAODServiceID = pbxAODServiceID;
  }

  public int getService_id() {
    return this.service_id;
  }

  public boolean isPreemptive() {
    return this.preemptive;
  }

  public boolean isReqManualDialService() {
    return this.reqManualDialService;
  }

  public boolean isShowManualDialServiceList() {
    return this.showManualDialServiceList;
  }

  public boolean isAgentCallbacksAllowedToAgent() {
    return this.agentCallbacksAllowedToAgent;
  }

  public boolean isServiceCallbacksAllowedToAgent() {
    return this.serviceCallbacksAllowedToAgent;
  }

  public boolean isServiceCallbacksAllowedToService() {
    return this.serviceCallbacksAllowedToService;
  }

  public int getDispositionPlanID() {
    return this.dispositionPlanID;
  }

  public String getServiceName() {
    return this.serviceName;
  }

  public int getCalldatadefid() {
    return this.calldatadefid;
  }

  public boolean isWramTransfer() {
    return this.wramTransfer;
  }

  public int getService_type_id() {
    return this.service_type_id;
  }

  public int getPreviewtimeoutinsecs() {
    return this.previewtimeoutinsecs;
  }

  public int getOutgoingmask() {
    return this.outgoingmask;
  }

  public int getCallbackdays() {
    return this.callbackdays;
  }

  public int getDisplayCallbackTimeWarningF() {
    return this.displayCallbackTimeWarningF;
  }

  public boolean isParkState() {
    return this.parkState;
  }

  public int getParkDelay() {
    return this.parkDelay;
  }

  public int getScriptID() {
    return this.scriptID;
  }

  public String getScriptName() {
    return this.scriptName;
  }

  public int getPbxAODServiceID() {
    return this.pbxAODServiceID;
  }

  public void clearCallDataDefs() {
    this.callDataDefs = null;
  }

  public int getCallDataLength() {
    return ((this.callDataDefs != null) ? this.callDataDefs.length : 0);
  }

  public int getCallDataFieldOrder(int i) {
    return this.callDataDefs[i].getFieldOrder();
  }

  public String getCallDataFieldLabel(int i) {
    return this.callDataDefs[i].getFieldLabel();
  }

  public String getCallDataFieldType(int i) {
    return this.callDataDefs[i].getFieldType();
  }

  public void ensureCallDataDefCount(int count) {
    if (this.callDataDefs != null)
      return;
    LOGGER.severe("ensureCallDataDefs: Call Data Def Array is EMPTY! *****");
    this.callDataDefs = new CallDataDefBean[count];
    for (int j = 0; j < count; ++j)
      this.callDataDefs[j] = new CallDataDefBean(0, "", "");
  }

  public void updateCallDataDef(int index, int maxCount, CallDataDefBean bean)
  {
    ensureCallDataDefCount(maxCount);
    this.callDataDefs[index] = bean;
  }

  public String callDataFieldsToString() {
    String newline = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer();

    sb.append("Service: ").append(this.service_id).append(" CallDataDefID: ").append(this.calldatadefid).append(newline);
    sb.append("CallDataDefFields [").append((this.callDataDefs != null) ? Integer.toString(this.callDataDefs.length) : "0").append("]").append(newline);
    if ((this.callDataDefs != null) && (this.callDataDefs.length > 0)) {
      for (int i = 0; i < this.callDataDefs.length; ++i) {
        sb.append("  ").append(this.callDataDefs[i].toString());
        if (i < this.callDataDefs.length - 1) {
          sb.append(newline);
        }
      }
    }
    return sb.toString();
  }

  private void ensureDispositionPlanCount(int count) {
    if (this.dispCodes != null)
      return;
    LOGGER.severe("ensureDispositionPlan: Disposition Code Array is EMPTY! *****");
    this.dispCodes = new DispositionCodeBean[count];
    for (int j = 0; j < count; ++j)
      this.dispCodes[j] = new DispositionCodeBean("", 0, "", false, false, false);
  }

  public void updateDispositionPlan(int index, int maxCount, DispositionCodeBean bean)
  {
    ensureDispositionPlanCount(maxCount);
    this.dispCodes[index] = bean;
  }

  public int getDispositionPlanLength() {
    return this.dispCodes.length;
  }

  public DispositionCodeBean getDispositionPlan(int index) {
    return this.dispCodes[index];
  }

  public String dispositionsToString() {
    String newline = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer();

    sb.append("SERVICE: ").append(this.service_id).append(" DispPlanID: ").append(this.dispositionPlanID).append(newline);
    sb.append("DISPOSITIONS [").append((this.dispCodes != null) ? Integer.toString(this.dispCodes.length) : "0").append("]").append(newline);
    if ((this.dispCodes != null) && (this.dispCodes.length > 0)) {
      for (int i = 0; i < this.dispCodes.length; ++i) {
        sb.append("  ").append(this.dispCodes[i].toString());
        if (i < this.dispCodes.length - 1) {
          sb.append(newline);
        }
      }
    }
    return sb.toString();
  }

  public void handleUDNotifyService(UDNotifyService serviceInfo) {
    setDispositionPlanID(serviceInfo.getDispplanid());
    setOutgoingmask(serviceInfo.getOutgoingmask());
    setServiceName(serviceInfo.getServicec());
    setService_type_id(serviceInfo.getServicetypeid());
  }

  public void handleUDNotifyDispositions(UDNotifyDispositions[] dispositions) {
    int nbr = dispositions.length;
    LOGGER.info("There are " + nbr + " DISPOSITIONS being parsed");

    this.dispCodes = new DispositionCodeBean[nbr];
    for (int i = 0; i < nbr; ++i)
      try {
    	UDNotifyDispositions d = dispositions[i];
        String code = d.getCode();
        int id = d.getDispid();
        String description = d.getDescription();
        int salef = d.getSalesf();
        int callbackf = d.getCallbackf();
        int exclusionf = d.getExclusionf();

        this.dispCodes[i] = new DispositionCodeBean(code, id, description, parseBoolean(salef), parseBoolean(callbackf), parseBoolean(exclusionf));
      }
      catch (RuntimeException e)
      {
    	  LOGGER.log(Level.SEVERE,"RuntimeException while parsing Disposition " + i, e);
      }
  }

  public void cleanDispositionPlan()
  {
    this.dispCodes = null;
  }

	public void handleUDNotifyServiceSettings(
			UDNotifyServiceSettings serviceSettings) {

		setKBAllowed(parseBoolean(serviceSettings.getAllowkb()));
		setPreviewNumChangeAllowed(parseBoolean(serviceSettings
				.getAllowphonechangeinpreview()));
		setBlindTransferAllowed(parseBoolean(serviceSettings.getBtnblindxferf()));

		setConsultAllowed(parseBoolean(serviceSettings.getBtnconsultf()));
		setDialAllowed(parseBoolean(serviceSettings.getBtndialf()));
		setHangupAllowed(parseBoolean(serviceSettings.getBtnhangupf()));
		setHoldAllowed(parseBoolean(serviceSettings.getBtnholdf()));

		setPlayAllowed(parseBoolean(serviceSettings.getBtnplayf()));
		setRecordAllowed(parseBoolean(serviceSettings.getBtnrecordf()));
		setConsultHangupAllowed(parseBoolean(serviceSettings
				.getBtnthreecusthangupf()));
		setThreeWayAllowed(parseBoolean(serviceSettings.getBtnthreewayf()));
		setTransferAllowed(parseBoolean(serviceSettings.getBtnxferf()));
		setCallbackdays(serviceSettings.getCallbackdays());
		setDisplayCallbackTimeWarningF(serviceSettings
				.getDisplaycallbacktimewarningf());
		setCalldatadefid(serviceSettings.getCalldatadefid());

		setServiceCallbacksAllowedToAgent(parseBoolean(serviceSettings
				.getCbselfcallbackflag()));
		setServiceCallbacksAllowedToService(parseBoolean(serviceSettings
				.getCbservicecallbackflag()));
		setChatDisconnectAllowed(parseBoolean(serviceSettings
				.getChatdisconnectallowed()));
		setChatRecordAllowed(parseBoolean(serviceSettings
				.getChatrecordallowed()));
		setDisplayACDRecordings(parseBoolean(serviceSettings
				.getDisplayservicerecordingf()));
		setShowManualDialServiceList(parseBoolean(serviceSettings
				.getDisplayservicesf()));

		setNoDispMaxWrapSecs(serviceSettings.getNodispmaxwrap());
		setPreviewtimeoutinsecs(serviceSettings.getPreviewtimeoutinsecs());
		setReqWrapOrReqDisp(serviceSettings.getReqdispositionf());
		try {
			setReqManualDialService(parseBoolean(serviceSettings
					.getRequireservicef()));
		} catch (Exception e1) {
			LOGGER.log(Level.SEVERE,
					" Parsing Exception while parsing Service setReqManualDialService "
							+ e1);
		}

		setWramTransfer(parseBoolean(serviceSettings.getWarmtransfer()));
		setWrapRedAlertSecs(serviceSettings.getWrapwarningdelay());

		setScriptName(serviceSettings.getLyricallscriptname());
		setScriptID(serviceSettings.getLyricallscriptid());
		setPbxAODServiceID(serviceSettings.getPbxaodserviceid());

	}

  public void handleUDNotifyCallDataDefFields(UDNotifyCallDataDefFields[]fields)
  {
    int nbr = fields.length;
    LOGGER.info("There are " + nbr + " CallDataDefFields being parsed");

    this.callDataDefs = new CallDataDefBean[nbr];
    for (int i = 0; i < nbr; ++i)
      try {
				UDNotifyCallDataDefFields setting = fields[i];
				int order = setting.getFieldorder();
				String label = setting.getLabel();
				String type = setting.getFieldtype();

        this.callDataDefs[i] = new CallDataDefBean(order, label, type);
      } catch (RuntimeException e) {
    	  LOGGER.log(Level.SEVERE,"RuntimeException while parsing CallDataDefField ", e);
      }
  }


  private boolean parseBoolean(int value) {
    return (value == 1);
  }

  public int getInt(String settingName) {
    if (settingName == null) {
      return 0;
    }

    String name = settingName.trim();
    if (name.equalsIgnoreCase("WrapRedAlertSecs"))
      return getWrapRedAlertSecs();
    if (name.equalsIgnoreCase("NoDispMaxWrapSecs"))
      return getNoDispMaxWrapSecs();
    if (name.equalsIgnoreCase("ReqWrapOrReqDisp"))
      return getReqWrapOrReqDisp();
    if (name.equalsIgnoreCase("DispositionPlanID"))
      return getDispositionPlanID();
    if (name.equalsIgnoreCase("CallDataDefID"))
      return getCalldatadefid();
    if (name.equalsIgnoreCase("ServiceType"))
      return getService_type_id();
    if (name.equalsIgnoreCase("PreviewTimeoutinSecs"))
      return getPreviewtimeoutinsecs();
    if (name.equalsIgnoreCase("Outgoingmask"))
      return getOutgoingmask();
    if (name.equalsIgnoreCase("CallbackDays"))
      return getCallbackdays();
    if (name.equalsIgnoreCase("DisplayCallbackTimeWarningF"))
      return getDisplayCallbackTimeWarningF();
    if (name.equalsIgnoreCase("ParkDelay"))
      return getParkDelay();
    if (name.equalsIgnoreCase("ScriptID"))
      return getScriptID();
    if (name.equalsIgnoreCase("pbxAODServiceID")) {
      return getPbxAODServiceID();
    }
    return 0;
  }

  public String getString(String settingName) {
    if (settingName == null) {
      return "";
    }

    String name = settingName.trim();
    if (name.equalsIgnoreCase("ServiceName"))
      return getServiceName();
    if (name.equalsIgnoreCase("ScriptName")) {
      return getScriptName();
    }
    return "";
  }

  public boolean getBoolean(String getSettingName) {
    if (getSettingName == null) {
      return false;
    }

    String name = getSettingName.trim();
    if (name.equalsIgnoreCase("ThreeWayAllowed"))
      return isThreeWayAllowed();
    if (name.equalsIgnoreCase("ConsultHangupAllowed"))
      return isConsultHangupAllowed();
    if (name.equalsIgnoreCase("DisplayACDRecordings"))
      return isDisplayACDRecordings();
    if (name.equalsIgnoreCase("PreviewNumChangeAllowed"))
      return isPreviewNumChangeAllowed();
    if (name.equalsIgnoreCase("DialAllowed"))
      return isDialAllowed();
    if (name.equalsIgnoreCase("HoldAllowed"))
      return isHoldAllowed();
    if (name.equalsIgnoreCase("KBAllowed"))
      return isKBAllowed();
    if (name.equalsIgnoreCase("TransferAllowed"))
      return isTransferAllowed();
    if (name.equalsIgnoreCase("HangupAllowed"))
      return isHangupAllowed();
    if (name.equalsIgnoreCase("ConsultAllowed"))
      return isConsultAllowed();
    if (name.equalsIgnoreCase("BlindTransferAllowed"))
      return isBlindTransferAllowed();
    if (name.equalsIgnoreCase("RecordAllowed"))
      return isRecordAllowed();
    if (name.equalsIgnoreCase("PlayAllowed"))
      return isPlayAllowed();
    if (name.equalsIgnoreCase("ChatRecordAllowed"))
      return isChatRecordAllowed();
    if (name.equalsIgnoreCase("ChatDisconnectAllowed"))
      return isChatDisconnectAllowed();
    if (name.equalsIgnoreCase("Preemptive"))
      return isPreemptive();
    if (name.equalsIgnoreCase("ReqManualDialService"))
      return isReqManualDialService();
    if (name.equalsIgnoreCase("ShowManualDialServiceList"))
      return isShowManualDialServiceList();
    if (name.equalsIgnoreCase("ShowManualDialServiceList"))
      return isShowManualDialServiceList();
    if (name.equalsIgnoreCase("AgentCallbacksAllowedToAgent"))
      return isAgentCallbacksAllowedToAgent();
    if (name.equalsIgnoreCase("ServiceCallbacksAllowedToAgent"))
      return isServiceCallbacksAllowedToAgent();
    if (name.equalsIgnoreCase("ServiceCallbacksAllowedToService"))
      return isServiceCallbacksAllowedToService();
    if (name.equalsIgnoreCase("WarmTransfer"))
      return isWramTransfer();
    if (name.equalsIgnoreCase("ParkState")) {
      return isParkState();
    }
    return true;
  }

  public String toString() {
    String newline = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer();

    sb.append("Service: ").append(this.service_id).append(" NAME: ").append(this.serviceName).append(newline);
    sb.append("SERVICE SETTINGS").append(newline);
    sb.append("  agentCallbacksAllowedToAgent=").append(this.agentCallbacksAllowedToAgent).append(newline);
    sb.append("  blindTransferAllowed=").append(this.blindTransferAllowed).append(newline);
    sb.append("  callbackdays=").append(this.callbackdays).append(newline);
    sb.append("  displayCallbackTimeWarningF=").append(this.displayCallbackTimeWarningF).append(newline);
    sb.append("  calldatadefid=").append(this.calldatadefid).append(newline);
    sb.append("  chatDisconnectAllowed=").append(this.chatDisconnectAllowed).append(newline);
    sb.append("  chatRecordAllowed=").append(this.chatRecordAllowed).append(newline);
    sb.append("  consultAllowed=").append(this.consultAllowed).append(newline);
    sb.append("  consultHangupAllowed=").append(this.consultHangupAllowed).append(newline);
    sb.append("  dialAllowed=").append(this.dialAllowed).append(newline);
    sb.append("  displayACDRecordings=").append(this.displayACDRecordings).append(newline);
    sb.append("  dispositionPlanID=").append(this.dispositionPlanID).append(newline);
    sb.append("  holdAllowed=").append(this.holdAllowed).append(newline);
    sb.append("  hangupAllowed=").append(this.hangupAllowed).append(newline);
    sb.append("  kBAllowed=").append(this.kBAllowed).append(newline);
    sb.append("  noDispMaxWrapSecs=").append(this.noDispMaxWrapSecs).append(newline);
    sb.append("  outgoingmask=").append(this.outgoingmask);
    sb.append("  parkDelay=").append(this.parkDelay).append(newline);
    sb.append("  parkState=").append(this.parkState).append(newline);
    sb.append("  playAllowed=").append(this.playAllowed).append(newline);
    sb.append("  preemptive=").append(this.preemptive).append(newline);
    sb.append("  previewNumChangeAllowed=").append(this.previewNumChangeAllowed).append(newline);
    sb.append("  previewtimeoutinsecs=").append(this.previewtimeoutinsecs).append(newline);
    sb.append("  recordAllowed=").append(this.recordAllowed).append(newline);
    sb.append("  reqManualDialService=").append(this.reqManualDialService).append(newline);
    sb.append("  reqWrapOrReqDisp=").append(this.reqWrapOrReqDisp).append(newline);
    sb.append("  scriptID=").append(this.scriptID).append(newline);
    sb.append("  scriptName=").append(this.scriptName).append(newline);
    sb.append("  service_id=").append(this.service_id).append(newline);
    sb.append("  service_type_id=").append(this.service_type_id).append(newline);
    sb.append("  serviceName=").append(this.serviceName).append(newline);
    sb.append("  serviceCallbacksAllowedToAgent=").append(this.serviceCallbacksAllowedToAgent).append(newline);
    sb.append("  serviceCallbacksAllowedToService=").append(this.serviceCallbacksAllowedToService).append(newline);
    sb.append("  showManualDialServiceList=").append(this.showManualDialServiceList).append(newline);
    sb.append("  transferAllowed=").append(this.transferAllowed).append(newline);
    sb.append("  threeWayAllowed=").append(this.threeWayAllowed).append(newline);
    sb.append("  warmTransfer=").append(this.wramTransfer).append(newline);
    sb.append("  wrapRedAlertSecs=").append(this.wrapRedAlertSecs).append(newline);
    sb.append("  pbxAODServiceID=").append(this.pbxAODServiceID);
    return sb.toString();
  }
}