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
	ServiceState getState();

	@Nonnull
	ServiceType getType();

	/**
	 * @return if the service isset to invisible, always {@code false} if not supported
	 */
	boolean isInvisible();

	/**
	 * @return the pid of the process or {@code -1}
	 */
	int getPid();

	long getConnectedTime();

	long getCreationTime();

	@Nonnull
	SpecificServiceManager getManager();

	@Nonnull
	Document getProperties();

}
