package net.anweisen.cloudapi.driver.event.channel;

import com.google.common.base.Preconditions;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.driver.event.Event;

import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This event is called when a {@link ChannelMessage} is received.
 * The event is always called on the {@literal *} event channel and not the {@link ChannelMessage#getChannel() channel of the received message}.
 *
 * You can set the response to a query channel message with {@link #setQueryResponse(ChannelMessage)}
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see CloudMessenger
 */
public final class ChannelMessageReceiveEvent implements Event {

	private final ChannelMessage message;
	private final boolean query;

	private ChannelMessage response;

	public ChannelMessageReceiveEvent(@Nonnull ChannelMessage message, boolean query) {
		this.message = message;
		this.query = query;
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

	public void setQueryResponse(@Nonnull ChannelMessage response) {
		Preconditions.checkArgument(query, "Cannot set query response of no query");
		this.response = response;
	}

	public void setJsonResponse(@Nonnull Document data) {
		setQueryResponse(message.createResponse().data(data).build());
	}

	@Nonnull
	@CheckReturnValue
	public Document createResponse() {
		Document document = Document.newJsonDocument();
		setJsonResponse(document);
		return document;
	}

	public boolean isQuery() {
		return query;
	}

	@Nullable
	public ChannelMessage getQueryResponse() {
		Preconditions.checkArgument(query, "Cannot get query response of no query");
		return response;
	}

	@Override
	public String toString() {
		return "ChannelMessageReceiveEvent[channel=" + getChannel() + " message=" + getMessage() + " sender=" + getSenderName() + " query=" + query + "]";
	}
}
