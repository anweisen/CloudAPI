package net.anweisen.cloudapi.driver.event.events.service;

import net.anweisen.cloudapi.driver.event.events.DriverEvent;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class ServiceEvent extends DriverEvent {

	protected final ServiceInfo serviceInfo;

	public ServiceEvent(@Nonnull ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	@Nonnull
	public ServiceInfo getServiceInfo() {
		return serviceInfo;
	}
}
