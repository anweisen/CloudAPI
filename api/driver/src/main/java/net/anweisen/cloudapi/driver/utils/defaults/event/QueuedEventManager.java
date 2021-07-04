package net.anweisen.cloudapi.driver.utils.defaults.event;

import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.event.RegisteredListener;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class QueuedEventManager extends DefaultEventManager {

	public QueuedEventManager() {
		super(null);
	}

	@Nonnull
	@Override
	public EventManager addListener(@Nonnull String channel, @Nonnull RegisteredListener listener) {
		this.listeners.computeIfAbsent(channel, key -> new ArrayList<>(1)).add(listener);
		return this;
	}

	@Nonnull
	@Override
	public EventManager addListeners(@Nonnull String channel, @Nonnull Collection<? extends RegisteredListener> listeners) {
		this.listeners.computeIfAbsent(channel, key -> new ArrayList<>(listeners.size())).addAll(listeners);
		return this;
	}

	public void copyTo(@Nonnull EventManager eventManager) {
		listeners.forEach(eventManager::addListeners);
	}
}
