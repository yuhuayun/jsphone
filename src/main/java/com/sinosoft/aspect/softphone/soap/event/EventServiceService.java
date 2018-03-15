package com.sinosoft.aspect.softphone.soap.event;

import java.net.URL;

import com.sinosoft.aspect.softphone.soap.ServiceException;


/**
 * 事件服务业务接口
 * @author Believe
 *
 */
public interface EventServiceService {

	/**
	 * 获取事件服务地址
	 * @return
	 */
	String getEventServiceAddress();

	/**
	 * 获取事件服务
	 * @return
	 * @throws ServiceException
	 */
	EventService getEventService() throws ServiceException;

	/**
	 * 获取事件服务
	 * @param url 事件服务地址
	 * @return
	 * @throws ServiceException
	 */
	EventService getEventService(URL url) throws ServiceException;
}
