package net.anweisen.cloudapi.driver.event.service;

import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ServiceUnregisterEvent extends ServiceEvent {

	public ServiceUnregisterEvent(@Nonnull ServiceInfo serviceInfo) {
		super(serviceInfo);
	}
}
