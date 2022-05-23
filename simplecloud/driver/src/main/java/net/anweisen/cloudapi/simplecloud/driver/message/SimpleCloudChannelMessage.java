package net.anweisen.cloudapi.simplecloud.driver.message;

import eu.thesimplecloud.api.client.NetworkComponentReference;
import eu.thesimplecloud.api.client.NetworkComponentType;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.MessageBuilder;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.common.config.document.GsonDocument;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SimpleCloudChannelMessage implements ChannelMessage {

	private final NetworkComponentReference senderReference;
	private final GsonDocument document;
	private final SimpleCloudChannelMessageTarget target;

	private final String channel;
	private final String message;
	private final Document data;

	private final UUID queryId;

	public SimpleCloudChannelMessage(@Nonnull String channel, @Nonnull GsonDocument document,
	                                 @Nonnull NetworkComponentReference senderReference, @Nullable SimpleCloudChannelMessageTarget target) {
		this.channel = channel;
		this.document = document;
		this.senderReference = senderReference;
		this.target = target;

		this.message = document.getString("m");
		this.data = document.getDocument("d");

		this.queryId = document.getUUID("q");
	}

	public SimpleCloudChannelMessage(@Nonnull String channel, @Nonnull String message, @Nonnull GsonDocument data, @Nullable UUID queryId,
	                                 @Nonnull NetworkComponentReference senderReference, @Nonnull SimpleCloudChannelMessageTarget target) {
		this.channel = channel;
		this.senderReference = senderReference;
		this.target = target;
		this.document = (GsonDocument) new GsonDocument().set("m", message).set("d", data);

		this.message = message;
		this.data = data;

		this.queryId = queryId;
	}

	@Nonnull
	@Override
	public String getSenderName() {
		return senderReference.getName();
	}

	@Override
	public NetworkComponent getSender() {
		NetworkComponentType type = senderReference.getComponentType();
		switch (type) {
			case WRAPPER:
			case MANAGER:
				return CloudDriver.getInstance().getNodeManager().getNode(getSenderName());
			case SERVICE:
				return CloudDriver.getInstance().getServiceManager().getServiceByName(getSenderName());
			default:
				CloudDriver.getInstance().getLogger().error("Unrecognized SimpleCloud NetworkComponentType." + type + " for sender=" + getSenderName());
				return null;
		}
	}

	@Nonnull
	@Override
	public String getChannel() {
		return channel;
	}

	@Nonnull
	@Override
	public String getMessage() {
		return message;
	}

	@Nonnull
	@Override
	public Document getData() {
		return data;
	}

	@Nonnull
	public GsonDocument getDocument() {
		return document;
	}

	public SimpleCloudChannelMessageTarget getTarget() {
		return target;
	}

	@Nonnull
	@Override
	public MessageBuilder createResponse() {
		if (queryId == null) throw new IllegalStateException("This is not a query message");
		SimpleCloudMessageBuilder builder = (SimpleCloudMessageBuilder) getMessenger().createMessage().channel(channel).message(this.message);
		builder.setQueryId(queryId);
		return builder;
	}

	@Override
	public String toString() {
		return formatString();
	}
}
