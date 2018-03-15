package com.sinosoft.aspect.softphone.soap.agent;

import com.sinosoft.aspect.softphone.soap.Stub;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgent;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgentInfo;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAnswerCall;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDCall;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDConferenceIn;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDConsultIn;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDDigits;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDDisposition;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDInfo;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDMonitor;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDParm;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDReason;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDRecorderData;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDTransfer;

/**
 * 软电话服务接口默认实现
 *
 * @author wangjunhua
 * @since 1.0
 *
 */
public class ConcertoAgentPortalSoapBindingStub extends Stub implements AgentWebService {

    ConcertoAgentPortalSoapBindingStub(java.net.URL endpointURL) {
        super.cachedEndpoint = endpointURL;
    }

    public void register(UDAgent udAgent, UDAgentInfo udAgentInfo) throws AgentPortalException {
        invoke("register", new Object[]{ udAgent, udAgentInfo });
    }

    public void transfer(UDAgent udAgent, UDTransfer udTransfer) throws AgentPortalException {
        invoke("transfer", new Object[]{ udAgent, udTransfer});
    }

    public void available(UDAgent udAgent) throws AgentPortalException {
        invoke("available", new Object[]{udAgent});
    }

    public void authenticate(UDAgent udAgent, UDAgentInfo udAgentInfo) throws AgentPortalException {
        invoke("authenticate", new Object[]{udAgent, udAgentInfo});
    }

    public void monitor(UDAgent udAgent, UDMonitor udMonitor) throws AgentPortalException {
        invoke("monitor", new Object[]{udAgent, udMonitor});
    }

    public Object getInfo(UDAgent udAgent, UDInfo udInfo) throws AgentPortalException {
        return invoke("getInfo", new Object[]{ udAgent, udInfo });
    }

    public void keepAlive(UDAgent udAgent) throws AgentPortalException {
        invoke("keepAlive", new Object[]{ udAgent });
    }

    public void consult(UDAgent udAgent, UDConsultIn udConsultIn) throws AgentPortalException {
        invoke("consult", new Object[]{udAgent, udConsultIn});
    }

    public void conference(UDAgent udAgent, UDConferenceIn udConferenceIn) throws AgentPortalException {
        invoke("conference", new Object[]{ udAgent, udConferenceIn});
    }

    public void hold(UDAgent udAgent, UDParm udParm) throws AgentPortalException {
        invoke("hold", new Object[]{ udAgent, udParm });
    }

    public void answerCall(UDAgent udAgent, UDAnswerCall udAnswerCall) throws AgentPortalException {
        invoke("answerCall", new Object[]{udAgent, udAnswerCall});
    }

    public void callOutcome(UDAgent udAgent, UDDisposition udDisposition) throws AgentPortalException {
        invoke("callOutcome", new Object[]{udAgent, udDisposition});
    }

    public void dial(UDAgent udAgent, UDCall udCall) throws AgentPortalException {
        invoke("dial", new Object[]{udAgent, udCall});
    }

    public void hangup(UDAgent udAgent, UDParm udParm) throws AgentPortalException {
        invoke("hangup", new Object[]{udAgent, udParm});
    }

    public void playDigits(UDAgent udAgent, UDDigits udDigits) throws AgentPortalException {
        invoke("playDigits", new Object[]{udAgent, udDigits});
    }

    public void requestLogout(UDAgent udAgent, UDReason udReason) throws AgentPortalException {
        invoke("requestLogout", new Object[]{udAgent, udReason});
    }

    public void requestUnavailable(UDAgent udAgent, UDReason udReason) throws AgentPortalException {
        invoke("requestUnavailable", new Object[]{udAgent, udReason});
    }

    public void retrieveHold(UDAgent udAgent, UDParm udParm) throws AgentPortalException {
        invoke("retrieveHold", new Object[]{udAgent, udParm});
    }

    public void unRegister(UDAgent udAgent) throws AgentPortalException {
        invoke("unRegister", new Object[]{udAgent});
    }

    public void record(UDAgent udAgent, UDRecorderData udRecorderData) throws AgentPortalException {
        invoke("record", new Object[]{udAgent, udRecorderData});
    }

}
