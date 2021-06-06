package net.anweisen.cloudapi.driver.message;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.utilities.commons.config.Document;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface MessageBuilder {

	@Nonnull
	@CheckReturnValue
	MessageBuilder channel(@Nonnull String channel);

	@Nonnull
	@CheckReturnValue
	MessageBuilder message(@Nonnull String message);

	@Nonnull
	@CheckReturnValue
	MessageBuilder data(@Nonnull Consumer<? super Document> action);

	@Nonnull
	@CheckReturnValue
	MessageBuilder data(@Nonnull Document document);

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetAll();

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetServices();

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetService(@Nonnull String name);

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetTask(@Nonnull String name);

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetNodes();

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetNode(@Nonnull String name);

	@Nonnull
	@CheckReturnValue
	default MessageBuilder targetLocalNode() {
		return targetNode(CloudDriver.getInstance().getNodeName());
	}

	@Nonnull
	default CloudMessenger getMessenger() {
		return CloudDriver.getInstance().getMessenger();
	}

	@Nonnull
	@CheckReturnValue
	ChannelMessage build();

}
