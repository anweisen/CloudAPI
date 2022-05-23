package net.anweisen.cloudapi.simplecloud.driver.message;

import com.google.gson.JsonObject;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.client.NetworkComponentReference;
import eu.thesimplecloud.api.message.IMessageChannel;
import eu.thesimplecloud.api.message.IMessageChannelManager;
import eu.thesimplecloud.api.network.component.INetworkComponent;
import eu.thesimplecloud.api.service.ICloudServiceManager;
import eu.thesimplecloud.api.wrapper.IWrapperManager;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.channel.ChannelMessageReceiveEvent;
import net.anweisen.cloudapi.driver.message.ChannelMessage;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.driver.message.MessageBuilder;
import net.anweisen.utilities.common.concurrent.task.Task;
import net.anweisen.utilities.common.config.document.GsonDocument;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SimpleCloudMessenger implements CloudMessenger {

	private final IMessageChannelManager manager;

	public SimpleCloudMessenger(@Nonnull IMessageChannelManager manager) {
		this.manager = manager;
	}

	protected void acquireChannel(@Nonnull String channelName) {
		try {
			IMessageChannel<JsonObject> channel = manager.registerMessageChannel(CloudAPI.getInstance().getThisSidesCloudModule(), channelName, JsonObject.class);

			channel.registerListener((messageContent, sender) -> {
				if (sender == CloudAPI.getInstance().getThisSidesNetworkComponent()) return;
				CloudDriver.getInstance().getLogger().info("Received message from '{}' on channel '{}': {}", sender.getName(), channelName, messageContent);
				GsonDocument document = new GsonDocument(messageContent);
				SimpleCloudChannelMessage message = new SimpleCloudChannelMessage(channelName, document, sender.toNetworkComponentReference(), null);
				handleMessage(message);
			});
		} catch (IllegalArgumentException ex) {
			// Channel already exists
		}
	}

	@Nonnull
	protected IMessageChannel<JsonObject> getChannel(@Nonnull String channelName) {
		acquireChannel(channelName);
		IMessageChannel<JsonObject> channel = manager.getMessageChannelByName(channelName);
		if (channel == null) throw new IllegalStateException("Channel " + channelName + " is null although it is registered");
		return channel;
	}

	protected void handleMessage(@Nonnull SimpleCloudChannelMessage message) {
		CloudDriver.getInstance().getEventManager().callEvent(new ChannelMessageReceiveEvent(message, false));
	}

	@Nonnull
	@Override
	public MessageBuilder createMessage() {
		return new SimpleCloudMessageBuilder();
	}

	@Override
	public void sendMessage(@Nonnull ChannelMessage m) {
		SimpleCloudChannelMessage message = (SimpleCloudChannelMessage) m;

		CloudDriver.getInstance().getLogger().info("Sending message {}", message);

		List<INetworkComponent> receivers = new ArrayList<>();
		findReceivers(message, receivers);

		IMessageChannel<JsonObject> channel = getChannel(message.getChannel());
		channel.sendMessage(message.getDocument().getJsonObject(), receivers);
	}

	private void findReceivers(SimpleCloudChannelMessage message, List<INetworkComponent> receivers) {
		ICloudServiceManager serviceManager = CloudAPI.getInstance().getCloudServiceManager();
		IWrapperManager wrapperManager = CloudAPI.getInstance().getWrapperManager();
		INetworkComponent managerComponent = NetworkComponentReference.getMANAGER_COMPONENT_REFERENCE().getNetworkComponent();

		SimpleCloudChannelMessageTarget target = message.getTarget();
		switch (target.getType()) {
			case ALL:
				receivers.addAll(serviceManager.getAllCachedObjects());
				receivers.addAll(wrapperManager.getAllCachedObjects());
				receivers.add(managerComponent);
				break;
			case NODE:
				if (target.getName() == null) {
					receivers.addAll(wrapperManager.getAllCachedObjects());
					receivers.add(managerComponent);
				} else {
					if (target.getName().equals(managerComponent.getName())) {
						receivers.add(managerComponent);
					} else {
						receivers.add(wrapperManager.getWrapperByName(target.getName()));
					}
				}
				break;
			case SERVICE:
				if (target.getName() == null) {
					receivers.addAll(serviceManager.getAllCachedObjects());
				} else {
					receivers.add(serviceManager.getCloudServiceByName(target.getName()));
				}
				break;
			case TASK:
				if (target.getName() == null) {
					throw new IllegalArgumentException();
				} else {
					receivers.addAll(serviceManager.getCloudServicesByGroupName(target.getName()));
				}
				break;
		}
	}

	@Nullable
	@Override
	public ChannelMessage sendSingleMessageQuery(@Nonnull ChannelMessage message) {
		return null;
	}

	@Nonnull
	@Override
	public Task<ChannelMessage> sendSingleMessageQueryAsync(@Nonnull ChannelMessage message) {
		return null;
	}

	@Nonnull
	@Override
	public Collection<ChannelMessage> sendMessageQuery(@Nonnull ChannelMessage message) {
		return null;
	}

	@Nonnull
	@Override
	public Task<Collection<ChannelMessage>> sendMessageQueryAsync(@Nonnull ChannelMessage message) {
		return null;
	}

	@Nonnull
	@Override
	public CloudMessenger registerChannel(@Nonnull String... channelNames) {
		for (String channel : channelNames) {
			acquireChannel(channel);
		}

		return this;
	}
}
