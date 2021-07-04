package net.anweisen.cloudapi.driver.event.channel;

import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.driver.event.Event;

import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.driver.message.MessageBuilder;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public final class ChannelMessageReceiveEvent implements Event {

	private final ChannelMessage message;

	public ChannelMessageReceiveEvent(@Nonnull ChannelMessage message) {
		this.message = message;
	}

	@Nonnull
	public CloudMessenger getMessenger() {
		return getDriver().getMessenger();
	}

	@Nonnull
	public ChannelMessage getChannelMessage() {
		return message;
	}

	@Nonnull
	public String getSenderName() {
		return message.getSenderName();
	}

	@Nullable
	public NetworkComponent getSender() {
		return message.getSender();
	}

	@Nonnull
	public String getChannel() {
		return message.getChannel();
	}

	@Nonnull
	public String getMessage() {
		return message.getMessage();
	}

	@Nonnull
	public Document getData() {
		return message.getData();
	}

	@Nonnull
	@CheckReturnValue
	public MessageBuilder response() {
		return message.response();
	}

	@Override
	public String toString() {
		return "ChannelMessageReceiveEvent[channel=" + getChannel() + " message=" + getMessage() + " sender=" + getSenderName() + "]";
	}
}
