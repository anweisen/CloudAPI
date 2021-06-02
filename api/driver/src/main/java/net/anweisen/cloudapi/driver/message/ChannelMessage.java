package net.anweisen.cloudapi.driver.message;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.utils.task.Task;
import net.anweisen.utilities.commons.config.Document;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ChannelMessage {

	@Nonnull
	String getSender();

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

	@Nonnull
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

}
