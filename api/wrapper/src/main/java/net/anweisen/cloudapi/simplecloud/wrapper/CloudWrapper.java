package net.anweisen.cloudapi.simplecloud.wrapper;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.CloudEnvironment;
import net.anweisen.cloudapi.driver.service.specific.ServiceId;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.commons.logging.ILogger;

import javax.annotation.Nonnull;

/**
 * The wrapper is an {@link CloudDriver} instance which is running on a cloud service instance.
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see CloudDriver
 */
public interface CloudWrapper extends CloudDriver {

	@Nonnull
	@Override
	default ILogger getLogger() { // TODO
		return ILogger.forThisClass();
	}

	@Nonnull
	@Override
	default CloudEnvironment getEnvironment() {
		return CloudEnvironment.WRAPPER;
	}

	@Nonnull
	ServiceId getServiceId();

	@Nonnull
	ServiceInfo getCurrentServiceInfo();

	/**
	 * @return the last published service info, or the {@link #getCurrentServiceInfo() current} if no one is cached or unsupported
	 */
	@Nonnull
	ServiceInfo getLastServiceInfo();

	static CloudWrapper getInstance() {
		return (CloudWrapper) CloudDriver.getInstance();
	}

}
