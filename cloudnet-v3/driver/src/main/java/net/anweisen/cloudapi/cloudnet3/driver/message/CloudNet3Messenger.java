package net.anweisen.cloudapi.cloudnet3.driver.message;

import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.MessageBuilder;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping.mapTask;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Messenger implements CloudMessenger {

	private final de.dytanic.cloudnet.driver.provider.CloudMessenger messenger;

	public CloudNet3Messenger(@Nonnull de.dytanic.cloudnet.driver.provider.CloudMessenger messenger) {
		this.messenger = messenger;
	}

	public static de.dytanic.cloudnet.driver.channel.ChannelMessage extractMessage(@Nonnull ChannelMessage message) {
		return ((CloudNet3ChannelMessage)message).message;
	}

	public static List<ChannelMessage> mapMessages(@Nonnull Collection<? extends de.dytanic.cloudnet.driver.channel.ChannelMessage> messages) {
		return messages.stream().map(CloudNet3ChannelMessage::new).collect(Collectors.toList());
	}

	public static ChannelMessage mapMessage(@Nullable de.dytanic.cloudnet.driver.channel.ChannelMessage message) {
		return Optional.ofNullable(message).map(CloudNet3ChannelMessage::new).orElse(null);
	}

	@Nonnull
	@Override
	public MessageBuilder createMessage() {
		return new CloudNet3MessageBuilder();
	}

	@Override
	public void sendMessage(@Nonnull ChannelMessage message) {
		messenger.sendChannelMessage(extractMessage(message));
	}

	@Nullable
	@Override
	public ChannelMessage sendSingleMessageQuery(@Nonnull ChannelMessage message) {
		return mapMessage(messenger.sendSingleChannelMessageQuery(extractMessage(message)));
	}

	@Nonnull
	@Override
	public Task<ChannelMessage> sendSingleMessageQueryAsync(@Nonnull ChannelMessage message) {
		return mapTask(messenger.sendSingleChannelMessageQueryAsync(extractMessage(message)).map(CloudNet3Messenger::mapMessage));
	}

	@Nonnull
	@Override
	public Collection<ChannelMessage> sendMessageQuery(@Nonnull ChannelMessage message) {
		return mapMessages(messenger.sendChannelMessageQuery(extractMessage(message)));
	}

	@Nonnull
	@Override
	public Task<Collection<ChannelMessage>> sendMessageQueryAsync(@Nonnull ChannelMessage message) {
		return mapTask(messenger.sendChannelMessageQueryAsync(extractMessage(message)).map(CloudNet3Messenger::mapMessages));
	}

	@Nonnull
	@Override
	public CloudMessenger registerChannel(@Nonnull String... channelNames) {
		// In CloudNet3 we dont have to register messaging channels
		return this;
	}
}
