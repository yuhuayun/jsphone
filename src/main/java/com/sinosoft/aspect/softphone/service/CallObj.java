package com.sinosoft.aspect.softphone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


class CallObj {
  private static final Logger LOGGER = Logger.getLogger(CallObj.class.getName());
  private final Map agentState;
  private final Map currentCallID;
  private final Map serviceSetting;
  private final Map screenPop;
  private final List<Integer> serviceIDList;
  private final List currentCallList;
  private final List sList;

  CallObj() {
    this.agentState = new HashMap();
    this.currentCallID = new HashMap();
    this.serviceSetting = new HashMap();
    this.screenPop = new HashMap();
    this.serviceIDList = new ArrayList();
    this.currentCallList = new ArrayList();
    this.sList = new Vector();
   }

  public int getServiceListLength() {
    return this.serviceIDList.size();
  }

  public int getServiceID(int i) {
	 if(i <= this.serviceIDList.size()){
		 Integer aa = (Integer)this.serviceIDList.get(i);
		 return ((aa != null) ? aa.intValue() : -1);
	 }else{
		 return -1;
	 }
  }

  public void addService(int serviceID, AgentSettings serviceSettings) {
    LOGGER.info("CallObj:addService(serviceID " + serviceID + ", AgentSettings)");
    this.serviceIDList.add(serviceID);
    this.serviceSetting.put(serviceID, serviceSettings);
  }

  public void removeService(int serviceID) {
    LOGGER.info("CallObj:removeService:serviceID=" + serviceID);
    this.serviceIDList.remove(serviceID);
    this.serviceSetting.remove(serviceID);
  }

  public boolean hasService(int serviceID) {
    return this.serviceIDList.contains(serviceID);
  }

  public AgentSettings getAgentSettings(int serviceID)
  {
    return ((AgentSettings)this.serviceSetting.get(new Integer(serviceID)));
  }

  public int getAgentSettingInt(int serviceID, String name0) {
    AgentSettings agentSettings = (AgentSettings)this.serviceSetting.get(new Integer(serviceID));
    if (agentSettings == null) {
      return 0;
    }
    return agentSettings.getInt(name0);
  }

  public String getAgentSettingString(int serviceID, String name0) {
    AgentSettings agentSettings = (AgentSettings)this.serviceSetting.get(new Integer(serviceID));
    if (agentSettings == null) {
      return "";
    }
    return agentSettings.getString(name0);
  }

  public boolean getAgentSettingBoolean(int serviceID, String name0) {
    AgentSettings agentSettings = (AgentSettings)this.serviceSetting.get(new Integer(serviceID));
    if (agentSettings == null) {
      return false;
    }
    return agentSettings.getBoolean(name0);
  }

  public int getCurrentCallLength() {
    return this.currentCallList.size();
  }

  public boolean hasCurrentCall(int currentCall) {
    return this.currentCallList.contains(new Integer(currentCall));
  }

  public void addSPop(int currentCall,ScreenPopBean screenPop) {
    this.currentCallList.add(currentCall);
    this.screenPop.put(currentCall, screenPop);
  }

  public void removeSPop(int currentCall) {
    this.currentCallList.remove(new Integer(currentCall));
    this.screenPop.remove(new Integer(currentCall));
  }

  public void setSPop(int currentCall, String key, String value) {
    ScreenPopBean sPop = getSPop0(currentCall);
    if (sPop != null)
      sPop.setString(key, value);
  }

  public void setSPop(int currentCall, String key, int value)
  {
    ScreenPopBean sPop = getSPop0(currentCall);
    if (sPop != null)
      sPop.setInt(key, value);
  }

  public void setSPop(int currentCall, String key, boolean value)
  {
    ScreenPopBean sPop = getSPop0(currentCall);
    if (sPop != null)
      sPop.setBoolean(key, value);
  }

  public String getSPopString(int currentCall, String key)
  {
    ScreenPopBean sPop = getSPop0(currentCall);
    return ((sPop != null) ? sPop.getString(key) : "");
  }

  public int getSPopInt(int currentCall, String key) {
    ScreenPopBean sPop = getSPop0(currentCall);
    return ((sPop != null) ? sPop.getInt(key) : -1);
  }

  public boolean getSPopBoolean(int currentCall, String key) {
    ScreenPopBean sPop = getSPop0(currentCall);
    return ((sPop != null) ? sPop.getBoolean(key) : false);
  }

  public ScreenPopBean getSPop0(int currentCall) {
    return ((ScreenPopBean)this.screenPop.get(new Integer(currentCall)));
  }

  public int getCallID(int currentCall) {
    if (currentCall == 0) {
      return 0;
    }
    try
    {
      Integer aa = (Integer)this.currentCallID.get(new Integer(currentCall));
      int callid = (aa == null) ? -1 : aa.intValue();
      LOGGER.info("getCallID: " + callid);
      return callid;
    } catch (RuntimeException ee) {
      LOGGER.log(Level.INFO,"getCallID: RuntimeException", ee);
     }
    return -2;
  }

  public int getCurrentCall(int i)
  {
    if (i == 0) {
      return 0;
    }
    try
    {
      Iterator keys = this.currentCallID.keySet().iterator();
      while (keys.hasNext()) {
        Integer aa = (Integer)keys.next();
        if (((Integer)this.currentCallID.get(aa)).intValue() == i) {
          return aa.intValue();
        }
      }
      return -1;
    } catch (RuntimeException ee) {
    	LOGGER.log(Level.INFO,"getCurrentCall: RuntimeException", ee); }
    return -2;
  }

  public void removeCall(int currentCall)
  {
    this.currentCallID.remove(new Integer(currentCall));
    removeSPop(currentCall);
    Integer call_id = (Integer)this.currentCallID.get(new Integer(currentCall));
    if (call_id == null) {
      call_id = new Integer(0);
    }
    this.agentState.remove(call_id);
  }

  public void updateCurrentCall(int currentCall, int callID) {
    if (!(this.currentCallID.containsValue(new Integer(callID)))) {
      this.currentCallID.put(new Integer(currentCall), new Integer(callID));
    }
    else {
      int call = getCurrentCall(callID);
      if (call == currentCall)
        return;
      LOGGER.info("CallObj:updateCurrentCall:CallID=" + callID + " is already available. Call:" + call + ":currentCall:" + currentCall);
      this.currentCallID.remove(new Integer(call));
      this.currentCallID.put(new Integer(currentCall), new Integer(callID));
    }
  }

  public void updateCallID(int new_call_id, int original_call_id)
  {
    int state = getAgentState(original_call_id);
    if (state < 0) {
      return;
    }

    this.agentState.remove(new Integer(original_call_id));
    updateAgentState(new_call_id, state);

    int call = getCurrentCall(original_call_id);
    this.currentCallID.remove(new Integer(call));
    updateCurrentCall(call, new_call_id);
    setSPop(call, "Callfield_CallID", new_call_id);
  }

  public void updateAgentState(int callID, int state) {
    this.agentState.put(new Integer(callID), new Integer(state));
  }

  public int getAgentState(int callID) {
    try {
      Integer state = (Integer)this.agentState.get(new Integer(callID));
      return ((state == null) ? -1 : state.intValue()); } catch (Exception ee) {
    }
    return -2;
  }

  public int getServiceIDWithDispPlanIDListLength()
  {
    return this.sList.size();
  }

  public int getServiceIDWithDispPlanIDListSID(int i) {
    Integer service_id = (Integer)this.sList.get(i);
    return service_id.intValue();
  }

  public void getServiceIDWithDispPlanIDList(int dispplanid) {
    this.sList.clear();
    for (int i = 0; i < getServiceListLength(); ++i) {
      Integer service_id = (Integer)this.serviceIDList.get(i);
      int serviceid = service_id.intValue();
      if (getAgentSettingInt(serviceid, "DispositionPlanID") == dispplanid)
        this.sList.add(new Integer(serviceid));
    }
  }

  public int findServiceIDwithDispPlanID(int dispplanid)
  {
    for (int i = 0; i < getServiceListLength(); ++i) {
      Integer service_id = (Integer)this.serviceIDList.get(i);
      int serviceid = service_id.intValue();
      if (getAgentSettingInt(serviceid, "DispositionPlanID") == dispplanid) {
        return serviceid;
      }
    }

    return -1;
  }

  public void cleanDispositionPlan(int dispplanid) {
    for (int i = 0; i < getServiceListLength(); ++i) {
      Integer service_id = (Integer)this.serviceIDList.get(i);
      if (getAgentSettingInt(service_id.intValue(), "DispositionPlanID") == dispplanid) {
        AgentSettings agentSettings = (AgentSettings)this.serviceSetting.get(service_id);
        agentSettings.cleanDispositionPlan();
      }
    }
  }

  public void updateDispositionPlan(int dispplanid, int i, int count, DispositionCodeBean bean)
  {
    for (int ii = 0; ii < getServiceListLength(); ++ii) {
      Integer service_id = (Integer)this.serviceIDList.get(ii);
      if (getAgentSettingInt(service_id.intValue(), "DispositionPlanID") == dispplanid) {
        AgentSettings agentSettings = (AgentSettings)this.serviceSetting.get(service_id);
        agentSettings.updateDispositionPlan(i, count, bean);
      }
    }
  }

  public int findServiceIDwithCallDataDefID(int calldatadefid)
  {
    for (int i = 0; i < getServiceListLength(); ++i) {
      Integer service_id = (Integer)this.serviceIDList.get(i);
      int serviceid = service_id.intValue();
      if (getAgentSettingInt(serviceid, "CallDataDefID") == calldatadefid) {
        return serviceid;
      }
    }
    return -1;
  }

  public void updateCallDataDefFieldOrder(int updatecalldatadefid, int count, int i, CallDataDefBean callDataDefBean)
  {
    for (int ii = 0; ii < getServiceListLength(); ++ii) {
      Integer service_id = (Integer)this.serviceIDList.get(ii);
      if (getAgentSettingInt(service_id.intValue(), "CallDataDefID") == updatecalldatadefid) {
        AgentSettings agentSettings = (AgentSettings)this.serviceSetting.get(service_id);
        agentSettings.updateCallDataDef(i, count, callDataDefBean);
      }
    }
  }

  public void cleanCallDataDef(int updatecalldatadefid) {
    for (int i = 0; i < getServiceListLength(); ++i) {
      Integer service_id = (Integer)this.serviceIDList.get(i);
      if (getAgentSettingInt(service_id.intValue(), "CallDataDefID") == updatecalldatadefid) {
        AgentSettings agentSettings = (AgentSettings)this.serviceSetting.get(service_id);
        agentSettings.clearCallDataDefs();
      }
    }
  }

  public void dumpServiceSettings() {
    Iterator e = this.serviceSetting.keySet().iterator();
    while (e.hasNext()) {
      Integer id = (Integer)e.next();
      AgentSettings service = (AgentSettings)this.serviceSetting.get(id);
      LOGGER.info(service.toString());
      LOGGER.info(service.callDataFieldsToString());
      LOGGER.info(service.dispositionsToString());
    }
  }

  public String toString() {
    String newline = System.getProperty("line.separator");

    StringBuffer result = new StringBuffer();
    result.append("CallObj.agentState.size=").append(this.agentState.size()).append(newline);
    result.append("CallObj.currentCallID.size=").append(this.currentCallID.size()).append(newline);
    result.append("CallObj.serviceIDList.size=").append(this.serviceIDList.size()).append(newline);
    result.append("CallObj.currentCallList.size=").append(this.currentCallList.size()).append(newline);
    result.append("CallObj.serviceSetting.size=").append(this.serviceSetting.size()).append(newline);
    result.append("CallObj.screenPop.size=").append(this.screenPop.size());
    return result.toString();
  }
}