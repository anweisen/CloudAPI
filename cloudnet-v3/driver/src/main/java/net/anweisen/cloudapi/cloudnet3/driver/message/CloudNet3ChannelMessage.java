package net.anweisen.cloudapi.cloudnet3.driver.message;

import de.dytanic.cloudnet.driver.DriverEnvironment;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper.CloudNet3Document;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.MessageBuilder;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ChannelMessage implements ChannelMessage {

	final de.dytanic.cloudnet.driver.channel.ChannelMessage message;

	public CloudNet3ChannelMessage(@Nonnull de.dytanic.cloudnet.driver.channel.ChannelMessage message) {
		this.message = message;
	}

	@Nonnull
	@Override
	public String getSenderName() {
		return message.getSender().getName();
	}

	@Override
	public NetworkComponent getSender() {
		DriverEnvironment type = message.getSender().getType();
		switch (type) {
			case CLOUDNET:  return CloudDriver.getInstance().getNodeManager().getNode(getSenderName());
			case WRAPPER:   return CloudDriver.getInstance().getServiceManager().getServiceByName(getSenderName());
			default:
				CloudDriver.getInstance().getLogger().error("Unrecognized CloudNet DriverEnvironment." + type + " for sender=" + getSenderName());
				return null;
		}
	}

	@Nonnull
	@Override
	public String getChannel() {
		return message.getChannel();
	}

	@Nonnull
	@Override
	public String getMessage() {
		return message.getMessage() == null ? "" : message.getMessage();
	}

	@Nonnull
	@Override
	public Document getData() {
		return Mapping.mapDocument(message.getJson());
	}

	@Nonnull
	@Override
	public MessageBuilder createResponse() {
		return new CloudNet3MessageBuilder(de.dytanic.cloudnet.driver.channel.ChannelMessage.buildResponseFor(message));
	}

	@Override
	public String toString() {
		return formatString();
	}
}
