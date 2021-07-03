package net.anweisen.cloudapi.driver.message;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.utilities.common.concurrent.task.Task;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ChannelMessage {

	@Nonnull
	String getSenderName();

	/**
	 * @return the sender or {@code null} if the sender is no longer known to the cloud
	 */
	NetworkComponent getSender();

	@Nonnull
	String getChannel();

	@Nonnull
	String getMessage();

	@Nonnull
	Document getData();

	@Nonnull
	default CloudMessenger getMessenger() {
		return CloudDriver.getInstance().getMessenger();
	}

	default void send() {
		getMessenger().sendMessage(this);
	}

	@Nonnull
	default Collection<ChannelMessage> sendQuery() {
		return getMessenger().sendMessageQuery(this);
	}

	@Nonnull
	default Task<Collection<ChannelMessage>> sendQueryAsync() {
		return getMessenger().sendMessageQueryAsync(this);
	}

	@Nullable
	default ChannelMessage sendSingeQuery() {
		return getMessenger().sendSingleMessageQuery(this);
	}

	@Nonnull
	default Task<ChannelMessage> sendSingleQueryAsync() {
		return getMessenger().sendSingleMessageQueryAsync(this);
	}

	@Nonnull
	@CheckReturnValue
	MessageBuilder response();

	@Nonnull
	default String formatString() {
		return "ChannelMessage[channel=" + getChannel() + " message=" + getMessage() + " sender" + getSenderName() + " data=" + getData() + "]";
	}

}
