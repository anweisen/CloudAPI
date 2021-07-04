package net.anweisen.cloudapi.driver.message;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.channel.ChannelMessageReceiveEvent;
import net.anweisen.cloudapi.driver.exceptions.UnsupportedCloudFeatureException;
import net.anweisen.utilities.common.concurrent.task.Task;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface CloudMessenger {

	@Nonnull
	@CheckReturnValue
	MessageBuilder createMessage();

	/**
	 * @param message the message to send
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support channel messages
	 */
	void sendMessage(@Nonnull ChannelMessage message);

	/**
	 * @param channel the channel to send the message on
	 * @param message the message to send
	 * @param data the data to send
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support channel messages
	 */
	default void sendMessage(@Nonnull String channel, @Nonnull String message, @Nonnull Document data) {
		ChannelMessage channelMessage = createMessage().channel(channel).message(message).data(data).build();
		sendMessage(channelMessage);
	}

	/**
	 * Sends a channel message and waits for the first response.
	 *
	 * @param message the message to send or {@code null} if no one responded
	 * @return the first response received
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	@Nullable
	ChannelMessage sendSingleMessageQuery(@Nonnull ChannelMessage message);

	/**
	 * Sends a channel message and waits for the first response.
	 *
	 * @param channel the channel to send the message on
	 * @param message the message to send
	 * @param data the data to send
	 * @return the first response received
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	@Nullable
	default ChannelMessage sendSingleMessageQuery(@Nonnull String channel, @Nonnull String message, @Nonnull Document data) {
		ChannelMessage channelMessage = createMessage().channel(channel).message(message).data(data).build();
		return sendSingleMessageQuery(channelMessage);
	}

	/**
	 * Sends a channel message and waits for the first response.
	 *
	 * @param message the message to send
	 * @return the first response received
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	@Nonnull
	Task<ChannelMessage> sendSingleMessageQueryAsync(@Nonnull ChannelMessage message);

	/**
	 * Sends a channel message and waits for the first response.
	 *
	 * @param channel the channel to send the message on
	 * @param message the message to send
	 * @param data the data to send
	 * @return the first response received
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	default Task<ChannelMessage> sendSingleMessageQueryAsync(@Nonnull String channel, @Nonnull String message, @Nonnull Document data) {
		ChannelMessage channelMessage = createMessage().channel(channel).message(message).data(data).build();
		return sendSingleMessageQueryAsync(channelMessage);
	}

	/**
	 * Sends a channel message and waits for the result from all receivers
	 *
	 * @param message the message to send
	 * @return a collection containing the responses from all receivers
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	@Nonnull
	Collection<ChannelMessage> sendMessageQuery(@Nonnull ChannelMessage message);

	/**
	 * Sends a channel message and waits for the result from all receivers
	 *
	 * @param channel the channel to send the message on
	 * @param message the message to send
	 * @param data the data to send
	 * @return a collection containing the responses from all receivers
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	@Nonnull
	default Collection<ChannelMessage> sendMessageQuery(@Nonnull String channel, @Nonnull String message, @Nonnull Document data) {
		ChannelMessage channelMessage = createMessage().channel(channel).message(message).data(data).build();
		return sendMessageQuery(channelMessage);
	}

	/**
	 * Sends a channel message and waits for the result from all receivers
	 *
	 * @param message the message to send
	 * @return a collection containing the responses from all receivers
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	@Nonnull
	Task<Collection<ChannelMessage>> sendMessageQueryAsync(@Nonnull ChannelMessage message);

	/**
	 * Sends a channel message and waits for the result from all receivers
	 *
	 * @param channel the channel to send the message on
	 * @param message the message to send
	 * @param data the data to send
	 * @return a collection containing the responses from all receivers
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support query message responses
	 */
	@Nonnull
	default Task<Collection<ChannelMessage>> sendMessageQueryAsync(@Nonnull String channel, @Nonnull String message, @Nonnull Document data) {
		ChannelMessage channelMessage = createMessage().channel(channel).message(message).data(data).build();
		return sendMessageQueryAsync(channelMessage);
	}

	/**
	 * Registers the given channels to be listened on, nothing if not required
	 *
	 * @param channelNames the channels on which we should listen on for messages
	 */
	@Nonnull
	CloudMessenger registerChannel(@Nonnull String... channelNames);

	@Nonnull
	default CloudMessenger listenForMessage(@Nonnull String channel, @Nonnull Consumer<? super ChannelMessage> action) {
		CloudDriver.getInstance().getEventManager().on(ChannelMessageReceiveEvent.class, event -> {
			if (event.getChannel().equals(channel))
				action.accept(event.getChannelMessage());
		});
		return registerChannel(channel);
	}

	@Nonnull
	default CloudMessenger listenForMessage(@Nonnull String channel, @Nonnull String message, @Nonnull Consumer<? super ChannelMessage> action) {
		CloudDriver.getInstance().getEventManager().on(ChannelMessageReceiveEvent.class, event -> {
			if (event.getChannel().equals(channel) && event.getMessage().equals(message))
				action.accept(event.getChannelMessage());
		});
		return registerChannel(channel);
	}

}
