package com.sinosoft.aspect.softphone.soap.agent;

import java.net.MalformedURLException;
import java.net.URL;

import com.sinosoft.aspect.softphone.soap.ServiceException;


/**
 *  AgentWebService 接口本地实现类
 * @author Believe
 *
 */
public class AgentWebServiceServiceLocator implements AgentWebServiceService{

	private String ConcertoAgentPortal_address;

	public AgentWebServiceServiceLocator() {
		ConcertoAgentPortal_address = "http://localhost:8180/ConcertoAgentPortal/services/ConcertoAgentPortal";
	}

	public String getConcertoAgentPortalAddress() {
		return ConcertoAgentPortal_address;
	}

	public AgentWebService getConcertoAgentPortal() throws ServiceException {
		URL endpoint;
		try {
			endpoint = new URL(ConcertoAgentPortal_address);
		} catch (MalformedURLException e) {
			throw new ServiceException(e);
		}
		return getConcertoAgentPortal(endpoint);
	}

	public AgentWebService getConcertoAgentPortal(URL portAddress){
		ConcertoAgentPortalSoapBindingStub _stub = new ConcertoAgentPortalSoapBindingStub(portAddress);

		return _stub;
	}


}
