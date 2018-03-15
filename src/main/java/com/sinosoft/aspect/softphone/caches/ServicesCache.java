package com.sinosoft.aspect.softphone.caches;



import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDNewService;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDRemoveService;
import com.sinosoft.aspect.softphone.soap.event.Client.Notify.UDUpdateService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class ServicesCache {

	private String user_name;
	private final List services = new ArrayList();
	private final List callDataDefs = new ArrayList();
	private static final Logger LOG = Logger.getLogger(ServicesCache.class
			.getName());

	public ServicesCache(String user_name) {
		this.user_name = user_name;
	}

	public boolean updateService(UDUpdateService upUpdateService) {
		int service_id = upUpdateService.getServiceID();

		if ((this.services != null)
				&& (!(this.services.contains(new Integer(service_id))))) {
			LOG.config("userName=" + this.user_name
					+ " EventType.EVENT_UPDATESERVICE():serviceID="
					+ service_id + " : Agent not in service, event ignored");

			return false;
		}

		Integer updSvcCallDataDefId = new Integer(upUpdateService
				.getServiceSettings().getCalldatadefid());

		if ((this.callDataDefs != null)
				&& (!(this.callDataDefs.contains(updSvcCallDataDefId)))) {
			this.callDataDefs.add(updSvcCallDataDefId);
			LOG.config("userName=" + this.user_name
					+ " EventType.EVENT_NEWSERVICE():serviceID=" + service_id
					+ " : Agent added to callDataDef "
					+ this.callDataDefs.toString());

		}

		if (upUpdateService.getServiceInfo().getServicetypeid() == 4) {
			LOG.config("userName="
					+ this.user_name
					+ " EventType.EVENT_UPDATESERVICE(), Callback Service List flag reset to TRUE");
		}

		return true;
	}

	public boolean removeService(UDRemoveService udRemoveService) {
		int service_id = udRemoveService.getServiceID();

		Integer rmvServiceId = new Integer(service_id);
		if ((this.services == null)
				|| (!(this.services.contains(rmvServiceId)))) {
			return false;
		}

		int tmpIndex = this.services.indexOf(rmvServiceId);
		this.services.remove(tmpIndex);
		LOG.config("userName=" + this.user_name
				+ " EventType.EVENT_REMOVESERVICE():serviceID=" + service_id
				+ " : Agent removed from service " + this.services.toString());

		return true;
	}

	public void newService(UDNewService udNewService) {
		if(udNewService==null){
			LOG.info("获取服务失败");
			return;
		}
		else {
		int service_id = udNewService.getServiceID();

		Integer newServiceId = new Integer(service_id);
		if ((this.services != null)
				&& (!(this.services.contains(newServiceId)))) {
			this.services.add(newServiceId);

			LOG.config("userName=" + this.user_name
					+ " EventType.EVENT_NEWSERVICE():serviceID=" + service_id
					+ " : Agent added to service " + this.services.toString());

		}

		Integer newSvcCallDataDefId = new Integer(udNewService
				.getServiceSettings().getCalldatadefid());
		if ((this.callDataDefs != null)
				&& (!(this.callDataDefs.contains(newSvcCallDataDefId)))) {
			this.callDataDefs.add(newSvcCallDataDefId);

			LOG.config("userName=" + this.user_name
					+ " EventType.EVENT_NEWSERVICE():serviceID=" + service_id
					+ " : Agent added to callDataDef "
					+ this.callDataDefs.toString());

		}

		if (udNewService.getServiceInfo().getServicetypeid() == 4) {

			LOG.config("userName=" + this.user_name
					+ " EventType.EVENT_NEWSERVICE():serviceID=" + service_id
					+ " : Callback Service List flag reset to TRUE");
		}
		}
	}

	public boolean isUsingCallDataDef(int calldatadefid) {
		return ((this.callDataDefs != null) && (this.callDataDefs
				.contains(new Integer(calldatadefid))));
	}

}
