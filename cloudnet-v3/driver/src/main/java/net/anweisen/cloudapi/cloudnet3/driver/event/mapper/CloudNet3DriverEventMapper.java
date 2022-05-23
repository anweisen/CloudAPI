package net.anweisen.cloudapi.cloudnet3.driver.event.mapper;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.*;
import de.dytanic.cloudnet.driver.event.invoker.ListenerInvoker;
import net.anweisen.cloudapi.cloudnet3.driver.message.CloudNet3ChannelMessage;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.channel.ChannelMessageReceiveEvent;
import net.anweisen.cloudapi.driver.event.permission.PermissionUserUpdateEvent;
import net.anweisen.cloudapi.driver.event.player.PlayerConnectEvent;
import net.anweisen.cloudapi.driver.event.player.PlayerDisconnectEvent;
import net.anweisen.cloudapi.driver.event.player.PlayerSwitchServerEvent;
import net.anweisen.cloudapi.driver.event.service.*;
import net.anweisen.cloudapi.driver.player.CloudPlayer;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.cloudapi.driver.utils.defaults.event.EventMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static net.anweisen.cloudapi.cloudnet3.driver.message.CloudNet3Messenger.extractMessage;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class CloudNet3DriverEventMapper extends EventMapper {

	public CloudNet3DriverEventMapper() {
		register(de.dytanic.cloudnet.driver.event.events.channel.ChannelMessageReceiveEvent.class,
				event -> new ChannelMessageReceiveEvent(new CloudNet3ChannelMessage(event.getChannelMessage()), event.isQuery()),
				(origin, mapped) -> { if (origin.isQuery() && mapped.getQueryResponse() != null) origin.setQueryResponse(extractMessage(mapped.getQueryResponse())); }
		);

		register(de.dytanic.cloudnet.driver.event.events.service.CloudServiceRegisterEvent.class,       event -> new ServiceRegisterEvent(Mapping.mapServiceInfo(event.getServiceInfo())));
		register(de.dytanic.cloudnet.driver.event.events.service.CloudServiceStartEvent.class,          event -> new ServiceStartEvent(Mapping.mapServiceInfo(event.getServiceInfo())));
		register(de.dytanic.cloudnet.driver.event.events.service.CloudServiceStopEvent.class,           event -> new ServiceStopEvent(Mapping.mapServiceInfo(event.getServiceInfo())));
		register(de.dytanic.cloudnet.driver.event.events.service.CloudServiceUnregisterEvent.class,     event -> new ServiceUnregisterEvent(Mapping.mapServiceInfo(event.getServiceInfo())));
		register(de.dytanic.cloudnet.driver.event.events.service.CloudServiceInfoUpdateEvent.class,     event -> new ServiceInfoUpdateEvent(Mapping.mapServiceInfo(event.getServiceInfo())));

		register(de.dytanic.cloudnet.ext.bridge.event.BridgeProxyPlayerLoginSuccessEvent.class,         event -> new PlayerConnectEvent(event.getNetworkConnectionInfo().getUniqueId(), event.getNetworkConnectionInfo().getName()));
		register(de.dytanic.cloudnet.ext.bridge.event.BridgeProxyPlayerDisconnectEvent.class,           event -> new PlayerDisconnectEvent(event.getNetworkConnectionInfo().getUniqueId(), event.getNetworkConnectionInfo().getName()));
		register(de.dytanic.cloudnet.ext.bridge.event.BridgeProxyPlayerServerSwitchEvent.class,         event -> new PlayerSwitchServerEvent(event.getNetworkConnectionInfo().getUniqueId(), event.getNetworkConnectionInfo().getName(), getService(event.getNetworkServiceInfo().getUniqueId())));

		register(de.dytanic.cloudnet.driver.event.events.permission.PermissionUpdateUserEvent.class,    event -> new PermissionUserUpdateEvent(Mapping.mapUser(event.getPermissionUser())));
	}

	@Nullable
	protected CloudPlayer getPlayer(@Nonnull UUID uniqueId) {
		return CloudDriver.getInstance().getPlayerManager().getOnlinePlayer(uniqueId);
	}

	@Nullable
	protected ServiceInfo getService(@Nonnull UUID uniqueId) {
		return CloudDriver.getInstance().getServiceManager().getServiceById(uniqueId);
	}

	@Override
	protected void injectChannelListener0(@Nonnull String channel) throws Exception {
		IEventManager manager = CloudNetDriver.getInstance().getEventManager();
		Class<?> managerClass = manager.getClass();

		Field listenersField = managerClass.getDeclaredField("registeredListeners");
		listenersField.setAccessible(true);

		@SuppressWarnings("unchecked")
		Map<String, List<IRegisteredEventListener>> listenerMap = (Map<String, List<IRegisteredEventListener>>) listenersField.get(manager);
		List<IRegisteredEventListener> listenerList = listenerMap.computeIfAbsent(channel, key -> new CopyOnWriteArrayList<>());
		listenerList.add(new RegisteredEventListener(channel));
	}

	private class RegisteredEventListener implements IRegisteredEventListener {

		private final String channel;

		public RegisteredEventListener(@Nonnull String channel) {
			this.channel = channel;
		}

		@Override
		public void fireEvent(Event event) {
			handleIncomingEvent(channel, event);
		}

		@Override
		public EventListener getEventListener() {
			return null;
		}

		@Override
		public EventPriority getPriority() {
			return EventPriority.NORMAL;
		}

		@Override
		public Object getInstance() {
			return this;
		}

		@Override
		public ListenerInvoker getInvoker() {
			return null;
		}

		@Override
		public Class<?> getEventClass() {
			return Event.class;
		}

		@Override
		public String getMethodName() {
			return null;
		}

	}

}
