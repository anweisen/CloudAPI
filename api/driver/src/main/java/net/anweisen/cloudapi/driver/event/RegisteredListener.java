package net.anweisen.cloudapi.driver.event;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface RegisteredListener {

	void execute(@Nonnull Event event);

	@Nonnull
	Class<? extends Event> getEventClass();

	@Nonnull
	Object getHolder();

}
