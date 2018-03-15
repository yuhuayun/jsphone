package com.sinosoft.aspect.softphone.soap.event;

import java.net.URL;

import com.sinosoft.aspect.softphone.soap.Stub;
import com.sinosoft.aspect.softphone.soap.agent.AgentPortalException;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.AgentEvent;


public class EventServiceSoapBindingStub extends Stub implements EventService {

    EventServiceSoapBindingStub(URL endpointURL){
		super.cachedEndpoint = endpointURL;
	}

	public AgentEvent getNextAgentEvent(int registrationId) throws AgentPortalException {
        return (AgentEvent) invoke("getNextAgentEvent",
                new Object[] {
        		"<registrationId>" + registrationId + "</registrationId>"
            });
    }

	public int registerAgentEventSubscriber(String agentName) throws AgentPortalException {
        Object _resp = invoke("registerAgentEventSubscriber",new Object[] {
        		"<agentName>" + agentName + "</agentName>"
            });

        return Integer.parseInt(_resp.toString());
    }

	public void unregisterAgentEventSubscriber(int registrationId) throws AgentPortalException {
        invoke("unregisterAgentEventSubscriber",new Object[] {
        		"<registrationId>" + registrationId + "</registrationId>"});
    }


	public int registerAllAgentEventSubscriber() throws AgentPortalException {
        return (Integer) invoke("registerAllAgentEventSubscriber",new Object[0]);
    }

	public AgentEvent getNextAgentEventAck(int registrationId, long sequenceNumber) throws AgentPortalException {

        return (AgentEvent) invoke("getNextAgentEventAck",
                new Object[] {
                        "<registrationId>" + registrationId + "</registrationId>\n" +
        				"<sequenceNumber>" + sequenceNumber + "</sequenceNumber>\n"
                });
    }

}
