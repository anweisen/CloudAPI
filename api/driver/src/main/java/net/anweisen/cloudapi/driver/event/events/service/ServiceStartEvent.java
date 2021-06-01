package net.anweisen.cloudapi.driver.event.events.service;

import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ServiceStartEvent extends ServiceEvent {

	public ServiceStartEvent(@Nonnull ServiceInfo serviceInfo) {
		super(serviceInfo);
	}
}
