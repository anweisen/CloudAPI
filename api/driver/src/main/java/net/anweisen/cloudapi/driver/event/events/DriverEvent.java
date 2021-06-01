package net.anweisen.cloudapi.driver.event.events;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.Event;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class DriverEvent implements Event {

	@Nonnull
	public CloudDriver getDriver() {
		return CloudDriver.getInstance();
	}

}
