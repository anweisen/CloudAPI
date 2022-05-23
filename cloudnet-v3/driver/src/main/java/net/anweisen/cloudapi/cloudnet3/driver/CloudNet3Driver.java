package net.anweisen.cloudapi.cloudnet3.driver;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import net.anweisen.cloudapi.cloudnet3.driver.database.CloudNet3DatabaseProvider;
import net.anweisen.cloudapi.cloudnet3.driver.message.CloudNet3Messenger;
import net.anweisen.cloudapi.cloudnet3.driver.node.CloudNet3NodeManager;
import net.anweisen.cloudapi.cloudnet3.driver.permission.CloudNet3PermissionManager;
import net.anweisen.cloudapi.cloudnet3.driver.player.CloudNet3PlayerManager;
import net.anweisen.cloudapi.cloudnet3.driver.service.CloudNet3ServiceFactory;
import net.anweisen.cloudapi.cloudnet3.driver.service.CloudNet3ServiceManager;
import net.anweisen.cloudapi.cloudnet3.driver.service.CloudNet3ServiceTaskManager;
import net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper.CloudNet3Logger;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.cloud.CloudImplementationInfo;
import net.anweisen.cloudapi.driver.database.DatabaseProvider;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.driver.permission.PermissionManager;
import net.anweisen.cloudapi.driver.player.PlayerManager;
import net.anweisen.cloudapi.driver.service.ServiceManager;
import net.anweisen.cloudapi.driver.service.ServiceFactory;
import net.anweisen.cloudapi.driver.service.ServiceTaskManager;
import net.anweisen.cloudapi.driver.cloud.SupportFlag;
import net.anweisen.cloudapi.driver.cloud.SupportInfo;
import net.anweisen.cloudapi.driver.utils.defaults.event.DefaultEventManager;
import net.anweisen.cloudapi.driver.exceptions.UnavailableCloudFeatureException;
import net.anweisen.cloudapi.driver.exceptions.UnsupportedCloudFeatureException;
import net.anweisen.cloudapi.driver.utils.defaults.event.EventMapper;
import net.anweisen.utilities.common.logging.ILogger;

import javax.annotation.Nonnull;
import java.util.EnumSet;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class CloudNet3Driver implements CloudDriver {

	private static final SupportInfo support = new SupportInfo(EnumSet.allOf(SupportFlag.class));
	private static final CloudImplementationInfo implementation = new CloudImplementationInfo("CloudNet", "3.4.0-SNAPSHOT");
	protected static final CloudNetDriver driver = CloudNetDriver.getInstance();

	private CloudNet3Logger logger;
	private CloudNet3NodeManager nodeManager;
	private CloudNet3DatabaseProvider databaseProvider;
	private CloudNet3Messenger messenger;
	private CloudNet3PermissionManager permissionManager;
	private CloudNet3PlayerManager playerManager;
	private CloudNet3ServiceManager serviceManager;
	private CloudNet3ServiceTaskManager taskManager;
	private CloudNet3ServiceFactory serviceFactory;

	@Nonnull
	@Override
	public final SupportInfo getSupportInfo() {
		return support;
	}

	@Nonnull
	@Override
	public final CloudImplementationInfo getCloudImplementation() {
		return implementation;
	}

	@Nonnull
	@Override
	public ILogger getLogger() {
		if (logger == null)
			logger = new CloudNet3Logger(driver.getLogger());

		return logger;
	}

	@Nonnull
	@Override
	public DatabaseProvider getDatabaseProvider() {
		if (databaseProvider == null)
			databaseProvider = new CloudNet3DatabaseProvider(driver.getDatabaseProvider());

		return databaseProvider;
	}

	@Nonnull
	@Override
	public CloudNet3NodeManager getNodeManager() {
		if (nodeManager == null)
			nodeManager = new CloudNet3NodeManager(driver.getNodeInfoProvider());

		return nodeManager;
	}

	@Nonnull
	@Override
	public CloudMessenger getMessenger() {
		if (messenger == null)
			messenger = new CloudNet3Messenger(driver.getMessenger());

		return messenger;
	}

	@Nonnull
	@Override
	public PermissionManager getPermissionManager() {
		if (permissionManager == null)
			permissionManager = new CloudNet3PermissionManager(driver.getPermissionManagement());

		return permissionManager;
	}

	@Nonnull
	@Override
	public PlayerManager getPlayerManager() {
		if (playerManager == null) {
			try {
				IPlayerManager cloudnetPlayerManager = driver.getServicesRegistry().getFirstService(IPlayerManager.class);
				if (cloudnetPlayerManager == null)
					throw new UnavailableCloudFeatureException("CloudNet Bridge currently not loaded");

				playerManager = new CloudNet3PlayerManager(cloudnetPlayerManager);
			} catch (Throwable ex) {
				if (ex instanceof ClassNotFoundException || ex instanceof NoClassDefFoundError) // Cant catch ClassNotFoundException directly here
					throw new UnavailableCloudFeatureException("CloudNet Bridge currently not loaded and could not be found");
				if (ex instanceof UnavailableCloudFeatureException)
					throw (UnavailableCloudFeatureException) ex;
				if (playerManager == null)
					throw new UnavailableCloudFeatureException("CloudNet Bridge currently not loaded");
			}
		}

		return playerManager;
	}

	@Nonnull
	@Override
	public ServiceManager getServiceManager() {
		if (serviceManager == null)
			serviceManager = new CloudNet3ServiceManager(driver.getCloudServiceProvider());

		return serviceManager;
	}

	@Nonnull
	@Override
	public ServiceFactory getServiceFactory() {
		if (serviceFactory == null)
			serviceFactory = new CloudNet3ServiceFactory(driver.getCloudServiceFactory());

		return serviceFactory;
	}

	@Nonnull
	@Override
	public ServiceTaskManager getTaskManager() {
		if (taskManager == null)
			taskManager = new CloudNet3ServiceTaskManager(driver.getServiceTaskProvider());

		return taskManager;
	}

}
