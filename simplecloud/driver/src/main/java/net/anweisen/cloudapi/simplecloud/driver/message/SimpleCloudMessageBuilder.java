package net.anweisen.cloudapi.simplecloud.driver.message;

import eu.thesimplecloud.api.CloudAPI;
import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.MessageBuilder;
import net.anweisen.cloudapi.simplecloud.driver.message.SimpleCloudChannelMessageTarget.Type;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.common.config.document.GsonDocument;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SimpleCloudMessageBuilder implements MessageBuilder {

	private String channel;
	private String message;
	private GsonDocument document;
	private SimpleCloudChannelMessageTarget target;
	private UUID queryId;

	@Nonnull
	@Override
	public MessageBuilder channel(@Nonnull String channel) {
		this.channel = channel;
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder message(@Nonnull String message) {
		this.message = message;
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder data(@Nonnull Consumer<? super Document> action) {
		if (document == null) document = new GsonDocument();
		action.accept(document);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder data(@Nonnull Document document) {
		if (!(document instanceof GsonDocument))
			throw new IllegalArgumentException("The document was not created with Document.newJsonDocument()");
		this.document = (GsonDocument) document;
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetAll() {
		target = new SimpleCloudChannelMessageTarget(Type.ALL);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetServices() {
		target = new SimpleCloudChannelMessageTarget(Type.SERVICE);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetService(@Nonnull String name) {
		target = new SimpleCloudChannelMessageTarget(Type.SERVICE, name);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetTask(@Nonnull String name) {
		target = new SimpleCloudChannelMessageTarget(Type.TASK, name);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetNodes() {
		target = new SimpleCloudChannelMessageTarget(Type.NODE);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetNode(@Nonnull String name) {
		target = new SimpleCloudChannelMessageTarget(Type.NODE, name);
		return this;
	}

	@Nonnull
	@Override
	public ChannelMessage build() {
		if (target == null)
			targetAll();

		return new SimpleCloudChannelMessage(channel, message, document, queryId, CloudAPI.getInstance().getThisSidesNetworkComponent().toNetworkComponentReference(), target);
	}

	public void setQueryId(@Nullable UUID queryId) {
		this.queryId = queryId;
	}
}
