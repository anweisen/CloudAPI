package net.anweisen.cloudapi.driver;

import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.driver.database.DatabaseProvider;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.node.NodeManager;
import net.anweisen.cloudapi.driver.permission.PermissionManager;
import net.anweisen.cloudapi.driver.player.PlayerManager;
import net.anweisen.cloudapi.driver.service.ServiceManager;
import net.anweisen.cloudapi.driver.service.ServiceFactory;
import net.anweisen.cloudapi.driver.service.ServiceTaskManager;
import net.anweisen.cloudapi.driver.support.SupportInfo;
import net.anweisen.utilities.common.logging.ILogger;

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
	ILogger getLogger();

	@Nonnull
	CloudEnvironment getEnvironment();

	@Nonnull
	SupportInfo getSupportInfo();

	/**
	 * @return either the service this wrapper is running on or the node of the cloud
	 */
	@Nonnull
	NetworkComponent getComponent();

	@Nonnull
	NodeInfo getNode();

	@Nonnull
	NodeManager getNodeManager();

	/**
	 * @deprecated only a few clouds support database apis for the wrapper and the implementation is not very handy, may behave incorrect
	 *             may implement an own connection/implementation here later?
	 */
	@Nonnull
	@Deprecated
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
	ServiceManager getServiceManager();

	@Nonnull
	ServiceFactory getServiceFactory();

	@Nonnull
	ServiceTaskManager getTaskManager();

	static CloudDriver getInstance() {
		return InstanceHolder.instance;
	}

	static void setInstance(@Nonnull CloudDriver instance) {
		if (instance == null) throw new NullPointerException("Cannot set instance to null");
		InstanceHolder.instance = instance;
	}
}
