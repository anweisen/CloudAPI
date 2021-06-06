package net.anweisen.cloudapi.driver.event;

import net.anweisen.cloudapi.driver.CloudDriver;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface Event {

	@Nonnull
	default CloudDriver getDriver() {
		return CloudDriver.getInstance();
	}

}
