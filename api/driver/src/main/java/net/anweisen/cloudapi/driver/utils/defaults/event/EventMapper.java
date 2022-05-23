package net.anweisen.cloudapi.driver.utils.defaults.event;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.utilities.common.collection.ClassWalker;
import net.anweisen.utilities.common.collection.Tuple;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class EventMapper {

	@SuppressWarnings("unchecked")
	private static class Mapper<I, O extends Event> {

		private final Function<I, O> mapper;
		private final BiConsumer<I, O> task;

		public Mapper(@Nonnull Function<I, O> mapper, @Nullable BiConsumer<I, O> task) {
			this.mapper = mapper;
			this.task = task;
		}

		public O apply(@Nonnull Object event) {
			return mapper.apply((I) event);
		}

		public void runTask(@Nonnull Object originEvent, @Nonnull Object mappedEvent) {
			if (task == null) return;
			task.accept((I) originEvent, (O) mappedEvent);
		}

	}

	private final Map<Class<?>, Mapper<?, ?>> mappers = new HashMap<>();
	private final Collection<String> injectedChannels = new ArrayList<>(1);

	public EventMapper() {
		acquireChannel("*");
		acquireChannel("t");
	}

	public <I, O extends Event> void register(@Nonnull Class<I> trigger, @Nonnull Function<I, O> mapper) {
		register(trigger, mapper, null);
	}

	public <I, O extends Event> void register(@Nonnull Class<I> trigger, @Nonnull Function<I, O> mapper, @Nullable BiConsumer<I, O> task) {
		mappers.put(trigger, new Mapper<>(mapper, task));
	}

	public void acquireChannel(@Nonnull String channel) {
		if (injectedChannels.contains(channel)) return;
		injectedChannels.add(channel);

		try {
			injectChannelListener0(channel);
		} catch (Throwable ex) {
			CloudDriver.getInstance().getLogger().error("Unable to inject listener on channel {}", channel, ex);
		}
	}

	protected void handleIncomingEvent(@Nonnull String channel, @Nonnull Object event) {
		if (CloudDriver.getInstance() == null) return; // This should never happen, but it does at startup

		for (Class<?> clazz : ClassWalker.walk(event.getClass())) {
			Mapper<?, ?> mapper = mappers.get(clazz);
			if (mapper != null) {
				Event mappedEvent = mapper.apply(event);
				CloudDriver.getInstance().getEventManager().callEventOnlyOn(channel, mappedEvent);
				mapper.runTask(event, mappedEvent);
				return;
			}
		}
	}

	protected abstract void injectChannelListener0(@Nonnull String channel) throws Exception;

}
