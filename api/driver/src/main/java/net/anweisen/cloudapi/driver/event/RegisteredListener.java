package net.anweisen.cloudapi.driver.event;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see EventManager
 * @see EventManager#addListener(String, RegisteredListener)
 */
public interface RegisteredListener {

	void execute(@Nonnull Event event);

	@Nonnull
	Class<? extends Event> getEventClass();

	@Nonnull
	EventPriority getPriority();

	boolean getIgnoreCancelled();

	@Nonnull
	Object getHolder();

}
