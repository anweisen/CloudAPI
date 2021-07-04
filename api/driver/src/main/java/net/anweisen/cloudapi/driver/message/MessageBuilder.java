package net.anweisen.cloudapi.driver.message;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.utilities.common.config.Document;

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
	default MessageBuilder targetService(@Nonnull ServiceInfo service) {
		return targetService(service.getName());
	}

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetTask(@Nonnull String name);

	@Nonnull
	@CheckReturnValue
	default MessageBuilder targetTask(@Nonnull ServiceTask task) {
		return targetTask(task.getName());
	}

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetNodes();

	@Nonnull
	@CheckReturnValue
	MessageBuilder targetNode(@Nonnull String name);

	@Nonnull
	@CheckReturnValue
	default MessageBuilder targetNode(@Nonnull NodeInfo node) {
		return targetNode(node.getName());
	}

	@Nonnull
	@CheckReturnValue
	default MessageBuilder targetLocalNode() {
		return targetNode(CloudDriver.getInstance().getNode().getName());
	}

	@Nonnull
	@CheckReturnValue
	default MessageBuilder targetComponent(@Nonnull NetworkComponent component) {
		if (component instanceof NodeInfo) {
			return targetNode((NodeInfo) component);
		} else if (component instanceof ServiceInfo) {
			return targetService((ServiceInfo) component);
		} else {
			throw new IllegalArgumentException("Unrecognized NetworkComponent " + component.getClass().getName());
		}
	}

	@Nonnull
	default CloudMessenger getMessenger() {
		return CloudDriver.getInstance().getMessenger();
	}

	@Nonnull
	@CheckReturnValue
	ChannelMessage build();

}
