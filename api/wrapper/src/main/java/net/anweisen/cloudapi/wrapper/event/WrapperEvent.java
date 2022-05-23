package net.anweisen.cloudapi.wrapper.event;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.wrapper.CloudWrapper;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface WrapperEvent extends Event {

	@Nonnull
	default CloudWrapper getWrapper() {
		return CloudWrapper.getInstance();
	}

}
