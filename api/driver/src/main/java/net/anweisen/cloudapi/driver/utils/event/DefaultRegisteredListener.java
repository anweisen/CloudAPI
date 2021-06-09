package net.anweisen.cloudapi.driver.utils.event;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.driver.event.RegisteredListener;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public final class DefaultRegisteredListener implements RegisteredListener {

	private final Object holder;
	private final Method method;
	private final Class<? extends Event> eventClass;

	public DefaultRegisteredListener(@Nonnull Object holder, @Nonnull Method method, @Nonnull Class<? extends Event> eventClass) {
		this.holder = holder;
		this.method = method;
		this.eventClass = eventClass;
	}

	public void execute(@Nonnull Event event) {
		try {
			method.invoke(holder, event);
		} catch (Throwable ex) {
			DefaultEventManager.LOGGER.error("An error uncaught occurred while executing event listener", ex);
			if (ex instanceof Error)
				throw (Error) ex;
		}
	}

	@Nonnull
	@Override
	public Class<? extends Event> getEventClass() {
		return eventClass;
	}

	@Nonnull
	public Object getHolder() {
		return holder;
	}

}
