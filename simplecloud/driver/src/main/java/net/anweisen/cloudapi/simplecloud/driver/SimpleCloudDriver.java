package net.anweisen.cloudapi.simplecloud.driver;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.ICloudAPI;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.cloud.CloudImplementationInfo;
import net.anweisen.cloudapi.driver.database.DatabaseProvider;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.driver.node.NodeManager;
import net.anweisen.cloudapi.driver.permission.PermissionManager;
import net.anweisen.cloudapi.driver.player.PlayerManager;
import net.anweisen.cloudapi.driver.service.ServiceFactory;
import net.anweisen.cloudapi.driver.service.ServiceManager;
import net.anweisen.cloudapi.driver.service.ServiceTaskManager;
import net.anweisen.cloudapi.driver.cloud.SupportFlag;
import net.anweisen.cloudapi.driver.cloud.SupportInfo;
import net.anweisen.cloudapi.driver.utils.defaults.event.DefaultEventManager;
import net.anweisen.cloudapi.driver.exceptions.UnsupportedCloudFeatureException;
import net.anweisen.cloudapi.driver.utils.defaults.event.EventMapper;
import net.anweisen.cloudapi.simplecloud.driver.message.SimpleCloudMessenger;
import net.anweisen.utilities.common.logging.ILogger;

import javax.annotation.Nonnull;
import java.util.EnumSet;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class SimpleCloudDriver implements CloudDriver {

	private static final SupportInfo support = new SupportInfo(EnumSet.allOf(SupportFlag.class));
	private static final CloudImplementationInfo implementation = new CloudImplementationInfo("SimpleCloud", "2.1.0");
	protected static final ICloudAPI api = CloudAPI.getInstance();

	private final EventManager eventManager;
	private final EventMapper eventMapper;

	public SimpleCloudDriver(@Nonnull EventMapper eventMapper) {
		this.eventMapper = eventMapper;
		this.eventManager = new DefaultEventManager(eventMapper);
	}

	private SimpleCloudMessenger messenger;

	@Nonnull
	@Override
	public SupportInfo getSupportInfo() {
		return support;
	}

	@Nonnull
	@Override
	public CloudImplementationInfo getCloudImplementation() {
		return implementation;
	}

	@Nonnull
	@Override
	public ILogger getLogger() {
		return null;
	}

	@Nonnull
	@Override
	public NodeManager getNodeManager() {
		return null;
	}

	@Nonnull
	@Override
	public DatabaseProvider getDatabaseProvider() {
		throw new UnsupportedCloudFeatureException();
	}

	@Nonnull
	@Override
	public EventManager getEventManager() {
		return eventManager;
	}

	@Nonnull
	@Override
	public CloudMessenger getMessenger() {
		if (messenger == null)
			messenger = new SimpleCloudMessenger(api.getMessageChannelManager());

		return messenger;
	}

	@Nonnull
	@Override
	public PermissionManager getPermissionManager() {
		return null;
	}

	@Nonnull
	@Override
	public PlayerManager getPlayerManager() {
		return null;
	}

	@Nonnull
	@Override
	public ServiceManager getServiceManager() {
		return null;
	}

	@Nonnull
	@Override
	public ServiceFactory getServiceFactory() {
		return null;
	}

	@Nonnull
	@Override
	public ServiceTaskManager getTaskManager() {
		return null;
	}
}
