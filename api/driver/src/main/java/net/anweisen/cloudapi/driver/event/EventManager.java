package net.anweisen.cloudapi.driver.event;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface EventManager {

	@Nonnull
	EventManager addListener(@Nonnull String channel, @Nonnull RegisteredListener listener);

	@Nonnull
	<E extends Event> EventManager addListener(@Nonnull String channel, @Nonnull Class<E> eventClass, @Nonnull Consumer<? super E> action);

	@Nonnull
	EventManager registerListener(@Nonnull Object listener);

	@Nonnull
	default EventManager registerListeners(@Nonnull Object... listeners) {
		for (Object listener : listeners) {
			registerListener(listener);
		}

		return this;
	}

	@Nonnull
	EventManager unregisterListener(@Nonnull Object listener);

	@Nonnull
	default EventManager unregisterListeners(@Nonnull Object... listeners) {
		for (Object listener : listeners) {
			unregisterListener(listener);
		}

		return this;
	}

	@Nonnull
	EventManager unregisterListener(@Nonnull Class<?> listenerClass);

	@Nonnull
	default EventManager unregisterListeners(@Nonnull Class<?>... listenerClasses) {
		for (Class<?> listenerClass : listenerClasses) {
			unregisterListener(listenerClass);
		}

		return this;
	}

	@Nonnull
	EventManager unregisterListeners(@Nonnull ClassLoader loader);

	@EventListener
	EventManager unregisterAll();

	@Nonnull
	default EventManager callEvent(@Nonnull Event event) {
		return callEvent("*", event);
	}

	/**
	 * @param channel the channel to call the event on, will be ignored if not supported
	 * @param event the event to call
	 */
	@Nonnull
	EventManager callEvent(@Nonnull String channel, @Nonnull Event event);

}
