package net.anweisen.cloudapi.driver.utils.event;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.driver.event.RegisteredListener;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public final class RegisteredActionListener<E extends Event> implements RegisteredListener {

	private final Class<E> eventClass;
	private final Consumer<? super E> action;

	public RegisteredActionListener(@Nonnull Class<E> eventClass, @Nonnull Consumer<? super E> action) {
		this.eventClass = eventClass;
		this.action = action;
	}

	@Override
	public void execute(@Nonnull Event event) {
		try {
			action.accept(eventClass.cast(event));
		} catch (Throwable ex) {
			DefaultEventManager.LOGGER.error("An error uncaught occurred while executing event listener", ex);
			if (ex instanceof Error)
				throw (Error) ex;
		}
	}

	@Nonnull
	@Override
	public Class<E> getEventClass() {
		return eventClass;
	}

	@Nonnull
	@Override
	public Object getHolder() {
		return action;
	}
}
