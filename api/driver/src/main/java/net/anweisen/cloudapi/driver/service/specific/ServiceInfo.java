package net.anweisen.cloudapi.driver.service.specific;

import net.anweisen.utilities.commons.config.Document;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ServiceInfo {

	@Nonnull
	ServiceId getServiceId();

	@Nonnull
	default String getName() {
		return getServiceId().getName();
	}

	@Nonnull
	ServiceState getState();

	/**
	 * @return the pid of the process or {@code -1}
	 */
	int getPid();

	long getConnectedTime();

	@Nonnull
	SpecificServiceManager getManager();

	@Nonnull
	Document getProperties();

}
