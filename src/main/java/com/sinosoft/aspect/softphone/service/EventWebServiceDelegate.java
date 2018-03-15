package com.sinosoft.aspect.softphone.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;


import org.springframework.stereotype.Component;

import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgent;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.AgentEvent;
import com.sinosoft.aspect.softphone.soap.event.EventService;
import com.sinosoft.aspect.softphone.soap.event.EventServiceServiceLocator;

import lombok.extern.slf4j.Slf4j;


/**
 * 坐席事件服务代理
 *
 * @author wangjunhua
 * @since 1.0
 *
 */
@Component
@Slf4j
public class EventWebServiceDelegate {

  private String userName;
  private volatile EventService eventSvc;
  private volatile int subscriptionID;

  public EventWebServiceDelegate(){}

  public EventWebServiceDelegate(final String userName){
    this.userName = userName;
    getNewEventService("Initialization");
    this.subscriptionID = 0;
  }

  /**
   *  获取新的事件服务
   * @param why 调用描述信息
   */
  public void getNewEventService(String why) {
    try {
      EventServiceServiceLocator svcLocator = new EventServiceServiceLocator();
      this.eventSvc = svcLocator.getEventService(new URL(AbstractSoftPhone.Concerto_Event_Portal_URL));
      log.info("EventService  getNewEventService " + why);
    } catch (MalformedURLException ex) {
      log.error("userName=" + this.userName + ", " + why + " getNewEventService MalformedURLException " + ex.toString(), ex);
    }
  }

  /**
   * 注册事件服务
   * @param myUDAgent 坐席信息
   * @return 注册结果 true or false
   */
  public boolean register(UDAgent myUDAgent){
    if ((this.userName == null) || (this.userName.length() == 0)) {
      log.info("userName is empty! Cannot register with EWS!!!");
      return false;
    }
    try {
      this.subscriptionID = this.eventSvc.registerAgentEventSubscriber(myUDAgent.getAgentLoginName());
        log.info("userName=" + this.userName + ", registered for events with ID " + this.subscriptionID);

      return true;
    }catch (RemoteException ex) {
      log.info("userName=" + this.userName + ", RemoteException in register() :获取注册ID失败" );
    } catch (RuntimeException ex) {
      log.info("userName=" + this.userName + ", RuntimeException in register() ：获取注册ID失败" );
    }catch (Exception ex) {
    	log.info("userName=" + this.userName + ", RuntimeException in register() ：连接服务器异常" );
    }

    return false;
  }

  public void unregister(){
    try {
        this.eventSvc.unregisterAgentEventSubscriber(this.subscriptionID);
        this.subscriptionID = 0;
        log.info("userName=" + this.userName + ", EventGet:unregisterAgentEventSubscriber:1");

    }catch (RemoteException ex) {
      log.error("userName=" + this.userName + ", RemoteException in unregister() ");
    } catch (Exception ex) {
      log.error("userName=" + this.userName + ", RuntimeException in unregister() ");
    }
  }

  public AgentEvent getNextEvent(long startTime)throws Exception{
    int retryCount = 0;
    try
    {
      if (getAgt_id() == 0) {
        log.info("userName=" + this.userName + ", Invalid Registration Id == 0; giving up");
        unregister();
        return null;
      }
      if (retryCount > 0) {
        long delay = System.currentTimeMillis() - startTime;
        if (delay > 10000L) {
          log.info("userName=" + this.userName + ", No sendable Event Received after " + delay + " msec, so returning EVENT_NONE.");

          return null;
        }
        try {
          log.info("userName=" + this.userName + ", Sleep:0:" + new Date());
          Thread.sleep((long)(Math.random() * 1000.0D));
          log.info("userName=" + this.userName + ", Sleep:1:" + new Date());
        } catch (InterruptedException ie) {
          log.error("userName=" + this.userName + ", Interrupted before getting new Event Service Proxy");
        }
        getNewEventService("socket Exception recover");
      }

      synchronized (this){

        log.info("userName=" + this.userName + ", About to get next event");
        AgentEvent inEvent = getNextEvent();

        if (inEvent == null) {
          log.info("userName=" + this.userName + ", No Event Received");
        }

        return inEvent;
      }
    }
    catch (RemoteException ex)
    {
      String errMessage = ex.getMessage();
      if ((errMessage != null) && (errMessage.indexOf("Unknown Registration Id ") >= 0)) {
        log.info("userName=" + this.userName + ", Unknown Registration Id " + getAgt_id() + "; giving up");
        return null;
      }

      log.info("userName=" + this.userName + ", while loop exception: " );
      if (++retryCount > 20000L) {
        log.info("RemoteException retryCount=" + retryCount + ". Cannot recover; give up!");
        unregister();
        return null;
      }
      log.info("RemoteException retryCount=" + retryCount + "; will retry");
    }
	return null;
  }

  protected AgentEvent getNextEvent()throws Exception{
    return this.eventSvc.getNextAgentEvent(this.subscriptionID);
  }

  public int getAgt_id() {
    return this.subscriptionID;
  }

}