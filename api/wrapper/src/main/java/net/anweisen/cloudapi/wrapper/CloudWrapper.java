package net.anweisen.cloudapi.wrapper;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.service.specific.ServiceId;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;

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
	ServiceId getServiceId();

	@Nonnull
	ServiceInfo getCurrentServiceInfo();

	@Nonnull
	ServiceInfo getLastServiceInfo();

	static CloudWrapper getInstance() {
		return (CloudWrapper) CloudDriver.getInstance();
	}

}
