package net.anweisen.cloudapi.driver;

import net.anweisen.cloudapi.driver.database.DatabaseProvider;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.driver.permission.PermissionManager;
import net.anweisen.cloudapi.driver.player.PlayerManager;
import net.anweisen.cloudapi.driver.service.GeneralServiceManager;
import net.anweisen.cloudapi.driver.service.ServiceFactory;
import net.anweisen.cloudapi.driver.service.ServiceTaskManager;
import net.anweisen.cloudapi.driver.support.SupportInfo;

import javax.annotation.Nonnull;

/**
 * Represents the interface to the cloud.
 * This driver can either be in the cloud application ({@code CloudNode}, {@link CloudEnvironment#NODE})
 * or on a cloud service ({@code CloudWrapper}, {@link CloudEnvironment#WRAPPER}) like a minecraft server.
 * By using the driver class, you dont need to specify where this application should run and can be changed easily.
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see CloudEnvironment
 */
public interface CloudDriver {

	@Nonnull
	CloudEnvironment getEnvironment();

	@Nonnull
	SupportInfo getSupportInfo();

	/**
	 * @return the name of the component the driver is running on, either on a cloud service or a node, eg Lobby-1 or Node-1
	 */
	@Nonnull
	String getComponentName();

	/**
	 * @return the name of this node, depends on the cloud and config
	 */
	@Nonnull
	String getNodeName();

	@Nonnull
	DatabaseProvider getDatabaseProvider();

	@Nonnull
	EventManager getEventManager();

	@Nonnull
	CloudMessenger getMessenger();

	@Nonnull
	PermissionManager getPermissionManager();

	@Nonnull
	PlayerManager getPlayerManager();

	@Nonnull
	GeneralServiceManager getServiceManager();

	@Nonnull
	ServiceFactory getServiceFactory();

	@Nonnull
	ServiceTaskManager getTaskManager();

	static CloudDriver getInstance() {
		return Holder.instance;
	}

	static void setInstance(@Nonnull CloudDriver instance) {
		if (instance == null) throw new NullPointerException();
		Holder.instance = instance;
	}
}
