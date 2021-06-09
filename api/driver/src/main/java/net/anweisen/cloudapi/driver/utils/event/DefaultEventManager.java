package net.anweisen.cloudapi.driver.utils.event;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.driver.event.EventListener;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.event.RegisteredListener;
import net.anweisen.utilities.common.logging.ILogger;
import net.anweisen.utilities.common.misc.ReflectionUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class DefaultEventManager implements EventManager {

	public static final ILogger LOGGER = ILogger.forClass(EventManager.class);

	private final Map<String, Collection<RegisteredListener>> listeners = new HashMap<>();

	@Nonnull
	@Override
	public EventManager addListener(@Nonnull String channel, @Nonnull RegisteredListener listener) {
		listeners.computeIfAbsent(channel, key -> new ArrayList<>(1))
				.add(listener);
		return this;
	}

	@Nonnull
	@Override
	public EventManager registerListener(@Nonnull Object listener) {
		for (Method method : ReflectionUtils.getMethodsAnnotatedWith(listener.getClass(), EventListener.class)) {

			if (method.getParameterCount() != 1 || !Modifier.isPublic(method.getModifiers())) {
				throw new IllegalArgumentException(String.format(
						"Listener method %s:%s has to be public with exactly one argument",
						listener.getClass().getName(),
						method.getName()));
			}

			Class<?> parameterType = method.getParameterTypes()[0];
			if (!Event.class.isAssignableFrom(parameterType)) {
				throw new IllegalArgumentException(String.format(
						"Parameter type %s of listener method %s:%s is not an event",
						parameterType.getName(),
						listener.getClass().getName(),
						method.getName()));
			}

			EventListener annotation = method.getAnnotation(EventListener.class);
			addListener(annotation.channel(), new DefaultRegisteredListener(listener, method, parameterType.asSubclass(Event.class)));
		}

		return this;
	}

	@Nonnull
	@Override
	public <E extends Event> EventManager addListener(@Nonnull String channel, @Nonnull Class<E> eventClass, @Nonnull Consumer<? super E> action) {
		return addListener(channel, new RegisteredActionListener<>(eventClass, action));
	}

	@Nonnull
	@Override
	public EventManager unregisterListener(@Nonnull Object listener) {
		listeners.forEach((key, listeners) -> {
			listeners.removeIf(registered -> registered.getHolder() == listener);
		});

		return this;
	}

	@Nonnull
	@Override
	public EventManager unregisterListener(@Nonnull Class<?> listenerClass) {
		listeners.forEach((key, listeners) -> {
			listeners.removeIf(registered -> registered.getHolder().getClass() == listenerClass);
		});

		return this;
	}

	@Nonnull
	@Override
	public EventManager unregisterListeners(@Nonnull ClassLoader loader) {
		listeners.forEach((key, listeners) -> {
			listeners.removeIf(registered -> registered.getHolder().getClass().getClassLoader() == loader);
		});

		return this;
	}

	@Override
	public EventManager unregisterAll() {
		listeners.clear();
		return this;
	}

	@Nonnull
	@Override
	public EventManager callEvent(@Nonnull String channel, @Nonnull Event event) {
		if (channel.equals("*")) {
			for (Collection<RegisteredListener> listeners : listeners.values()) {
				executeListeners(event, listeners);
			}
		} else {
			executeListeners(event, listeners.getOrDefault(channel, Collections.emptyList()));
			executeListeners(event, listeners.getOrDefault("*", Collections.emptyList()));
		}

		return this;
	}

	private void executeListeners(@Nonnull Event event, @Nonnull Collection<RegisteredListener> listeners) {
		for (RegisteredListener listener : listeners) {
			if (listener.getEventClass().isAssignableFrom(event.getClass()))
				listener.execute(event);
		}
	}
}
