package net.anweisen.cloudapi.driver.utils.defaults.event;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.driver.event.EventPriority;
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
	private final EventPriority priority;
	private final boolean ignoreCancelled;

	public RegisteredActionListener(@Nonnull Class<E> eventClass, @Nonnull Consumer<? super E> action, @Nonnull EventPriority priority, boolean ignoreCancelled) {
		this.eventClass = eventClass;
		this.action = action;
		this.priority = priority;
		this.ignoreCancelled = ignoreCancelled;
	}

	@Override
	public void execute(@Nonnull Event event) {
		try {
			action.accept(eventClass.cast(event));
		} catch (Throwable ex) {
			CloudDriver.getInstance().getLogger().error("An error uncaught occurred while executing event listener", ex);
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
	public EventPriority getPriority() {
		return priority;
	}

	@Override
	public boolean getIgnoreCancelled() {
		return ignoreCancelled;
	}

	@Nonnull
	@Override
	public Object getHolder() {
		return action;
	}
}
