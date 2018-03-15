package com.sinosoft.aspect.softphone.logging;


import com.sinosoft.aspect.softphone.soap.agent.AgentPortalException;
import com.sinosoft.aspect.softphone.soap.agent.AgentWebService;
import com.sinosoft.aspect.softphone.soap.agent.Client.*;
import com.sinosoft.aspect.softphone.soap.agent.ConcertoFault;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.util.Timer;

@Slf4j
public class AgentServices implements AgentWebService {

  private static final int KEEPALIVE_INTERVAL = 60000;
  private static final long LONG_SOAPMETHOD_WARNING_TIME = 60000L;
  private final AgentWebService delegate;
  private final UDAgent agentObj;
  private Timer timer = null;

  public AgentServices(AgentWebService msgDelegate, UDAgent udAgent) {
    if ((msgDelegate == null) || (udAgent == null)) {
      throw new NullPointerException("Either msgDelegate or agent was null");
    }

    this.delegate = msgDelegate;
    this.agentObj = udAgent;

    log.info("AgentServices() created {}",toString());
  }

  public synchronized void answerCall(UDAgent agent, UDAnswerCall udAnswerCall) throws AgentPortalException {
    log.info("AWS.answerCall() name={}",agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.answerCall(agent, udAnswerCall);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "answerCall()");
    } catch (AgentPortalException e) {
      logAgentPortalException("answerCall()", e);
      throw e;
    }
  }

  public synchronized void authenticate(UDAgent agent, UDAgentInfo agentInfo) throws AgentPortalException {
    log.info("AWS.authenticate() name={}", agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.authenticate(agent, agentInfo);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "authenticate()");
    } catch (AgentPortalException e) {
      logAgentPortalException("authenticate()", e);
      throw e;
    }
  }

  public synchronized void available(UDAgent agent) throws AgentPortalException {
    log.info("AWS.available() name={}",agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.available(agent);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "available()");
    } catch (AgentPortalException e) {
      logAgentPortalException("available()", e);
      throw e;
    }
  }

  public synchronized void callOutcome(UDAgent agent, UDDisposition disposition) throws AgentPortalException,RemoteException {
    boolean retry;
    int retryCount = 0;
    do {
      log.info("AWS.callOutcome() name=" + agent.getAgentLoginName() + " cid=" + disposition.getCallId() + " disp=" + disposition.getSDisposition() + " (" + disposition.getIDisposition() + ")");

      retry = false;
      try {
        long startTimestamp = System.currentTimeMillis();
        this.delegate.callOutcome(agent, disposition);
        logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "callOutcome()");
      }
      catch (AgentPortalException e)
      {
        logAgentPortalException("callOutcome()", e);
        throw e;
      }
      catch (ConcertoFault e) {
        logAxisFault("callOutcome()", e);
        if (!(isOkToRetry("callOutcome()", retryCount, e))) {
          throw e;
        }

        ++retryCount;
        retry = true;
      }

    }
    while (retry);
  }

  public synchronized void conference(UDAgent agent, UDConferenceIn conferenceIn) throws AgentPortalException {
    log.info("AWS.conference() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.conference(agent, conferenceIn);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "conference()");
    } catch (AgentPortalException e) {
      logAgentPortalException("conference()", e);
      throw e;
    }
  }

  public synchronized void consult(UDAgent agent, UDConsultIn consultIn) throws AgentPortalException {
    log.info("AWS.consult() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.consult(agent, consultIn);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "consult()");
    } catch (AgentPortalException e) {
      logAgentPortalException("consult()", e);
      throw e;
    }
  }

  public synchronized void dial(UDAgent agent, UDCall call) throws AgentPortalException {
    log.info("AWS.dial() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.dial(agent, call);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "dial()");
    } catch (AgentPortalException e) {
      logAgentPortalException("dial()", e);
      throw e;
    }
  }

  public synchronized Object getInfo(UDAgent agent, UDInfo arg1) throws AgentPortalException {
    log.info("AWS.getInfo() name=" + agent.getAgentLoginName() + " LDAPUserID=" + agent.getLDAPUserId() + " ListType: " + arg1.getListType() + " ServiceId: " + arg1.getServiceId());

    try
    {
      long startTimestamp = System.currentTimeMillis();
      Object info = delegate.getInfo(agent, arg1);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "getInfo()");
      return info;
    } catch (AgentPortalException e) {
      log.info("AgentPortalException:"+e.getMessage());
      logAgentPortalException("getInfo()", e);
      throw e;
    }
  }

  public synchronized void hangup(UDAgent agent, UDParm param) throws AgentPortalException,RemoteException {
    boolean retry;
    int retryCount = 0;
    do {
      log.info("AWS.hangup() name=" + agent.getAgentLoginName() + " cid=" + param.getCallId());

      retry = false;
      try {
        long startTimestamp = System.currentTimeMillis();
        this.delegate.hangup(agent, param);
        logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "hangup()");
      }
      catch (AgentPortalException e)
      {
        logAgentPortalException("hangup()", e);
        throw e;
      }
      catch (ConcertoFault e) {
        logAxisFault("hangup()", e);
        if (!(isOkToRetry("hangup()", retryCount, e))) {
          throw e;
        }

        ++retryCount;
        retry = true;
      }
    }
    while (retry);
  }

  public synchronized void hold(UDAgent agent, UDParm param) throws AgentPortalException {
    log.info("AWS.hold() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.hold(agent, param);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "hold()");
    } catch (AgentPortalException e) {
      logAgentPortalException("hold()", e);
      throw e;
    }
  }

  public synchronized void monitor(UDAgent agent, UDMonitor monitor) throws AgentPortalException {
    log.info("AWS.monitor() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.monitor(agent, monitor);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "monitor()");
    } catch (AgentPortalException e) {
      logAgentPortalException("monitor()", e);
      throw e;
    }
  }

  public synchronized void playDigits(UDAgent agent, UDDigits digits) throws AgentPortalException {
    log.info("AWS.playDigits() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.playDigits(agent, digits);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "playDigits()");
    } catch (AgentPortalException e) {
      logAgentPortalException("playDigits()", e);
      throw e;
    }
  }


  public synchronized void register(UDAgent agent, UDAgentInfo agentInfo) throws AgentPortalException {
    log.info("AWS.register() name=" + agent.getAgentLoginName());
    try
    {
      log.info("AgentService:register:LDAPUserId=" + agent.getLDAPUserId() + ", AgentLoginName=" + agent.getAgentLoginName());

      startKeepAliveThread(agent.getAgentLoginName(), LONG_SOAPMETHOD_WARNING_TIME);

      long startTimestamp = System.currentTimeMillis();
      this.delegate.register(agent, agentInfo);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "register()");
    } catch (AgentPortalException e) {
      logAgentPortalException("register()", e);
      throw e;
    }
  }

  public synchronized void requestLogout(UDAgent agent, UDReason reason) throws AgentPortalException {
    log.info("AWS.requestLogout() name=" + agent.getAgentLoginName());

    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.requestLogout(agent, reason);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "requestLogout()");
    }
    catch (AgentPortalException e) {
      if (!(e.getFaultString().startsWith("Could not acquire agent object"))) {
        logAgentPortalException("requestLogout()", e);
        throw e;
      }
    }
  }

  public synchronized void requestUnavailable(UDAgent agent, UDReason reason) throws AgentPortalException {
    log.info("AWS.requestUnavailable() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.requestUnavailable(agent, reason);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "requestUnavailable()");
    } catch (AgentPortalException e) {
      logAgentPortalException("requestUnavailable()", e);
      throw e;
    }
  }

  public synchronized void retrieveHold(UDAgent agent, UDParm param) throws AgentPortalException {
    log.info("AWS.retrieveHold() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.retrieveHold(agent, param);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "retrieveHold()");
    } catch (AgentPortalException e) {
      logAgentPortalException("retrieveHold()", e);
      throw e;
    }
  }

  public synchronized void transfer(UDAgent agent, UDTransfer transfer) throws AgentPortalException {
    log.info("AWS.transfer() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.transfer(agent, transfer);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "transfer()");
    } catch (AgentPortalException e) {
      logAgentPortalException("transfer()", e);
      throw e;
    }
  }

  public synchronized void unRegister(UDAgent agent) throws AgentPortalException {
    log.info("AWS.unRegister() name=" + agent.getAgentLoginName());
    try
    {
      stopKeepAliveThread(agent.getAgentLoginName(), "unRegister");

      long startTimestamp = System.currentTimeMillis();
      this.delegate.unRegister(agent);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "unRegister()");
    } catch (AgentPortalException e) {
      logAgentPortalException("unRegister()", e);
      throw e;
    }
  }

  public synchronized void record(UDAgent agent, UDRecorderData recorder) throws AgentPortalException{
    log.info("AWS.record() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.record(agent, recorder);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "record()");
    } catch (AgentPortalException e) {
      logAgentPortalException("record()", e);
      throw e;
    }
  }

  public synchronized void keepAlive(UDAgent agent) throws AgentPortalException,RemoteException {
    log.debug("AWS.keepAlive() name=" + agent.getAgentLoginName());
    try
    {
      long startTimestamp = System.currentTimeMillis();
      this.delegate.keepAlive(agent);
      logMethodTime(agent, System.currentTimeMillis() - startTimestamp, "keepAlive()");
    }
    catch (AgentPortalException e)
    {
      logAgentPortalException("keepAlive()", e);
      stopKeepAliveThread(agent.getAgentLoginName(), "AgentPortalException in keepAlive()");
      throw e;
    }
    catch (ConcertoFault e) {
      logAxisFault("keepAlive()", e);
      if (!(isOkToRetry("keepAlive()", 0, e))) {
        stopKeepAliveThread(agent.getAgentLoginName(), "AxisFault in keepAlive()");
        throw e;
      }

    }
  }

  private void logAgentPortalException(String message, AgentPortalException e) {
    log.error("name=" + this.agentObj.getAgentLoginName() + ", " + message + " AgentPortalException, FaultString: " + e.getFaultString());
    log.error("exception detail", e);
  }

  private void logAxisFault(String message, ConcertoFault e) {
    log.error("name=" + this.agentObj.getAgentLoginName() + ", " + message + " AxisFault " + e.getMessage() + ", FaultString: , FaultDetail: " + e.detail.getClass().getName(), e);
  }

  private void logMethodTime(UDAgent agent, long methodTime, String method) {
    if ((methodTime > 6000L))
      log.info("name=" + agent.getAgentLoginName() + " " + method + "() took " + methodTime + "ms");
  }

  private boolean isOkToRetry(String message, int retryCount, ConcertoFault fault) {
    if ((!(fault.detail instanceof SocketTimeoutException)) && (!(fault.detail instanceof SocketException)))
    {
      return false;
    }

    if (retryCount >= 20000L) {
      return false;
    }

    log.error("name=" + this.agentObj.getAgentLoginName() + ", " + message + " socket failure, retry " + retryCount);
    try
    {
      Thread.sleep(750L);
    }
    catch (InterruptedException ignored) {
    }
    return true;
  }

  private void startKeepAliveThread(String agentName, long updateInterval) {

    if (this.timer == null) {
        this.timer = new Timer();
    }

    log.info("startKeepAliveThread creating keepAlive for " + agentName);
    try
    {
      this.timer.scheduleAtFixedRate(new SessionKeepAlive(this.agentObj), KEEPALIVE_INTERVAL, updateInterval);
      log.info("startKeepAliveThread:agentName=" + agentName + ", keepAliveTask=" + timer);

    } catch (RuntimeException e) {
      log.error("Exception creating keepAlive thread for " + agentName, e);
    }
  }

  public String toString() {
    return super.toString();//"Agent: " + this.agentObj.toString() + ", Delegate: " + this.delegate.toString() + ", timer: " + this.timer.toString();
  }

  private void stopKeepAliveThread(String agentName, String reason) {

    log.info("stopKeepAliveThread: agentName=" + agentName + ", keepAliveTask=" + timer + ", reason=" + reason);

    if (this.timer != null) {
      log.info("stopKeepAliveThread: Removing keepAlive for " + agentName);
      this.timer.cancel();
      this.timer = null;
    }
  }

  private class SessionKeepAlive extends java.util.TimerTask {
    private final String agentName;
    private UDAgent inAgent;

    protected SessionKeepAlive(UDAgent agent) {
      if (agent == null) {
        throw new NullPointerException("Agent name cannot be null");
      }

      this.agentName = agent.getAgentLoginName();
      this.inAgent = agent;

      log.info("Initialized SessionKeepAlive for " + this.agentName);
    }

    public void run() {
      try {
        AgentServices.this.keepAlive(this.inAgent);

      } catch (AgentPortalException e) {
        log.error("Agent: " + this.agentName + " AgentPortalException: " + e.getFaultString(), e);
      }
      catch (ConcertoFault e) {
        log.error("Agent: " + this.agentName + " AxisFault: " + e.getConcertoFaultString(), e);
      } catch (RemoteException e) {
        log.error("Agent: " + this.agentName + " RemoteException: " + e.toString(), e);
      } catch (Exception e) {
        log.error("Agent: " + this.agentName + " RuntimeException: ", e);
      }
    }
  }
}