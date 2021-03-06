package net.anweisen.cloudapi.driver.utils.defaults.event;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.EventPriority;
import net.anweisen.cloudapi.driver.event.RegisteredListener;
import net.anweisen.cloudapi.driver.event.Event;

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
	private final EventPriority priority;
	private final boolean ignoreCancelled;

	public DefaultRegisteredListener(@Nonnull Object holder, @Nonnull Method method, @Nonnull Class<? extends Event> eventClass, @Nonnull EventPriority priority, boolean ignoreCancelled) {
		this.holder = holder;
		this.method = method;
		this.eventClass = eventClass;
		this.priority = priority;
		this.ignoreCancelled = ignoreCancelled;
	}

	public void execute(@Nonnull Event event) {
		try {
			method.invoke(holder, event);
		} catch (Throwable ex) {
			CloudDriver.getInstance().getLogger().error("An error uncaught occurred while executing event listener", ex);
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
	@Override
	public EventPriority getPriority() {
		return priority;
	}

	@Override
	public boolean getIgnoreCancelled() {
		return ignoreCancelled;
	}

	@Nonnull
	public Object getHolder() {
		return holder;
	}

}
