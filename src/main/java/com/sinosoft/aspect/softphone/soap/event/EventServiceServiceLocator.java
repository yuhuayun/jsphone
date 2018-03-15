package com.sinosoft.aspect.softphone.soap.event;

import java.net.MalformedURLException;
import java.net.URL;

import com.sinosoft.aspect.softphone.soap.ServiceException;


/**
 * 事件服务业务接口实现类
 *
 * @author wangjunhua
 * @since 1.0
 *
 */
public class EventServiceServiceLocator implements EventServiceService {

	private String EventService_address;
	private static final int CONNECT_TIMEOUT = 20000;
	private static final int READ_TIMEOUT = 20000;

	/**
	 * 事件服务业务构造方法
	 */
	public EventServiceServiceLocator() {
		EventService_address = "http://localhost:8180/EventService/services/EventService";
	}

	/**
	 * 获取事件服务地址
	 */
	public String getEventServiceAddress() {
		return EventService_address;
	}

	/**
	 * 获取事件服务
	 */
	public EventService getEventService() throws ServiceException {
		URL endpoint;
		try {
			endpoint = new URL(EventService_address);
		} catch (MalformedURLException e) {
			throw new ServiceException(e);
		}
		return getEventService(endpoint);
	}

	/**
	 * 通过URL地址获取事件服务
	 */
	public EventService getEventService(URL portAddress) {
		EventServiceSoapBindingStub _stub = new EventServiceSoapBindingStub(portAddress);
		// maintain Connect
		_stub.setConnectTimeout(CONNECT_TIMEOUT);
		// Time out after a minute
		_stub.setReadTimeout(READ_TIMEOUT);

		return _stub;
	}

}
