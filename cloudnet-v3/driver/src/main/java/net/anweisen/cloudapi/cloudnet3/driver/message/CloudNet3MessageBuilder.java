package net.anweisen.cloudapi.cloudnet3.driver.message;

import com.google.common.base.Preconditions;
import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.channel.ChannelMessage.Builder;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper.CloudNet3Document;
import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.MessageBuilder;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.common.config.document.GsonDocument;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3MessageBuilder implements MessageBuilder {

	private final de.dytanic.cloudnet.driver.channel.ChannelMessage.Builder builder;

	public CloudNet3MessageBuilder(@Nonnull Builder builder) {
		this.builder = builder;
	}

	public CloudNet3MessageBuilder() {
		this(de.dytanic.cloudnet.driver.channel.ChannelMessage.builder());
	}

	@Nonnull
	@Override
	public MessageBuilder channel(@Nonnull String channel) {
		builder.channel(channel);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder message(@Nonnull String message) {
		builder.message(message);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder data(@Nonnull Consumer<? super Document> action) {
		de.dytanic.cloudnet.driver.channel.ChannelMessage message = builder.build();
		JsonDocument json = message.getJson();
		if (json == JsonDocument.EMPTY)
			builder.json(json = new JsonDocument());

		action.accept(Mapping.mapDocument(json));
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder data(@Nonnull Document document) {
		Preconditions.checkArgument(document instanceof GsonDocument || document instanceof CloudNet3Document, "Document was not created via Document.newJsonDocument()");
		builder.json(document instanceof CloudNet3Document ? ((CloudNet3Document) document).getJsonDocument() : new JsonDocument(((GsonDocument)document).getJsonObject()));
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetAll() {
		builder.targetAll();
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetServices() {
		builder.targetServices();
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetService(@Nonnull String name) {
		builder.targetService(name);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetTask(@Nonnull String name) {
		builder.targetTask(name);
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetNodes() {
		builder.targetNodes();
		return this;
	}

	@Nonnull
	@Override
	public MessageBuilder targetNode(@Nonnull String name) {
		builder.targetNode(name);
		return this;
	}

	@Nonnull
	@Override
	public ChannelMessage build() {
		return new CloudNet3ChannelMessage(builder.build());
	}
}
