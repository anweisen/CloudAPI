package net.anweisen.cloudapi.driver.utils.defaults.event;

import net.anweisen.cloudapi.driver.event.EventListener;
import net.anweisen.cloudapi.driver.event.*;
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

	protected final Map<String, List<RegisteredListener>> listeners = new HashMap<>();
	protected final EventMapper eventMapper;

	public DefaultEventManager(@Nonnull EventMapper eventMapper) {
		this.eventMapper = eventMapper;
	}

	@Nonnull
	@Override
	public EventManager addListener(@Nonnull String channel, @Nonnull RegisteredListener listener) {
		List<RegisteredListener> list = listeners.computeIfAbsent(channel, key -> new ArrayList<>(1));
		list.add(listener);
		list.sort(Comparator.comparingInt(value -> value.getPriority().ordinal()));
		eventMapper.acquireChannel(channel);
		return this;
	}

	@Nonnull
	@Override
	public EventManager addListeners(@Nonnull String channel, @Nonnull Collection<? extends RegisteredListener> registeredListeners) {
		List<RegisteredListener> list = listeners.computeIfAbsent(channel, key -> new ArrayList<>(registeredListeners.size()));
		list.addAll(registeredListeners);
		list.sort(Comparator.comparingInt(value -> value.getPriority().ordinal()));
		eventMapper.acquireChannel(channel);
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
			addListener(annotation.channel(), new DefaultRegisteredListener(listener, method, parameterType.asSubclass(Event.class), annotation.priority(), annotation.ignoreCancelled()));
		}

		return this;
	}

	@Nonnull
	@Override
	public <E extends Event> EventManager on(@Nonnull String channel, @Nonnull Class<E> eventClass, @Nonnull EventPriority priority, boolean ignoreCancelled, @Nonnull Consumer<? super E> action) {
		return addListener(channel, new RegisteredActionListener<>(eventClass, action, priority, ignoreCancelled));
	}

	@Nonnull
	@Override
	public EventManager unregisterListener(@Nonnull Object listener) {
		listeners.forEach((key, listeners) -> listeners.removeIf(registered -> registered.getHolder() == listener));
		return this;
	}

	@Nonnull
	@Override
	public EventManager unregisterListener(@Nonnull Class<?> listenerClass) {
		listeners.forEach((key, listeners) -> listeners.removeIf(registered -> registered.getHolder().getClass() == listenerClass));
		return this;
	}

	@Nonnull
	@Override
	public EventManager unregisterListeners(@Nonnull ClassLoader loader) {
		listeners.forEach((key, listeners) -> listeners.removeIf(registered -> registered.getHolder().getClass().getClassLoader().equals(loader)));
		return this;
	}

	@Nonnull
	@Override
	public EventManager unregisterAll() {
		listeners.clear();
		return this;
	}

	@Nonnull
	@Override
	public <E extends Event> E callEvent(@Nonnull String channel, @Nonnull E event) {
		if (channel.equals("*")) {
			for (Collection<RegisteredListener> listeners : listeners.values()) {
				executeListeners(event, listeners);
			}
		} else {
			executeListeners(event, listeners.getOrDefault(channel, Collections.emptyList()));
			executeListeners(event, listeners.getOrDefault("*", Collections.emptyList()));
		}
		return event;
	}

	@Nonnull
	@Override
	public <E extends Event> E callEventOnlyOn(@Nonnull String channel, @Nonnull E event) {
		executeListeners(event, listeners.getOrDefault(channel, Collections.emptyList()));
		return event;
	}

	protected void executeListeners(@Nonnull Event event, @Nonnull Collection<? extends RegisteredListener> listeners) {
		for (RegisteredListener listener : listeners) {
			if (!listener.getEventClass().isAssignableFrom(event.getClass())) continue;
			if (listener.getIgnoreCancelled() && event instanceof Cancelable && ((Cancelable)event).isCancelled()) continue;

			listener.execute(event);
		}
	}
}
