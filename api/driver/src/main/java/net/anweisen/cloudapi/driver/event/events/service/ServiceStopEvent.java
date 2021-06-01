package net.anweisen.cloudapi.driver.event.events.service;

import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ServiceStopEvent extends ServiceEvent {

	public ServiceStopEvent(@Nonnull ServiceInfo serviceInfo) {
		super(serviceInfo);
	}
}
