package com.sinosoft.aspect.softphone.soap.agent;

import java.net.URL;

import com.sinosoft.aspect.softphone.soap.ServiceException;

public interface AgentWebServiceService {

	String getConcertoAgentPortalAddress();

	AgentWebService getConcertoAgentPortal() throws ServiceException;

	AgentWebService getConcertoAgentPortal(URL url) throws ServiceException;
}
