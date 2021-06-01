package net.anweisen.cloudapi.driver.event;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface EventManager {

	@Nonnull
	EventManager registerListener(@Nonnull Object listener);

	@Nonnull
	EventManager registerListeners(@Nonnull Object... listeners);

	@Nonnull
	EventManager unregisterListener(@Nonnull Object listener);

	@Nonnull
	EventManager unregisterListeners(@Nonnull Object... listeners);

	@Nonnull
	EventManager unregisterListener(@Nonnull Class<?> listenerClass);

	@Nonnull
	EventManager unregisterListeners(@Nonnull Class<?>... listenerClasses);

	@Nonnull
	EventManager unregisterListeners(@Nonnull ClassLoader loader);

	@Nonnull
	EventManager callEvent(@Nonnull Event event);

	/**
	 * @param channel the channel to call the event on, will be ignored if not supported
	 * @param event the event to call
	 */
	@Nonnull
	EventManager callEvent(@Nonnull String channel, @Nonnull Event event);

}
