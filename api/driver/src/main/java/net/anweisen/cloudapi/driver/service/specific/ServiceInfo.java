package net.anweisen.cloudapi.driver.service.specific;

import net.anweisen.cloudapi.driver.component.ComponentType;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.driver.exceptions.UnsupportedCloudFeatureException;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ServiceInfo extends NetworkComponent {

	@Nonnull
	UUID getUniqueId();

	@Nonnull
	String getName();

	@Nonnull
	@Override
	default ComponentType getComponentType() {
		return ComponentType.SERVICE;
	}

	int getPort();

	/**
	 * @return the id of the service inside of the task, eg 2 for Lobby-2
	 */
	int getServiceNumber();

	/**
	 * @return the task of this service
	 */
	@Nonnull
	ServiceTask getTask();

	/**
	 * @return the node this service is running on
	 */
	@Nonnull
	NodeInfo getNode();

	/**
	 * @return the current state of this service
	 */
	@Nonnull
	ServiceState getState();

	@Nonnull
	ServiceType getType();

	/**
	 * @return whether the service is set to invisible, or {@code false} if not supported
	 */
	boolean isInvisible();

	@Nonnull
	ServiceController getController();

	@Nonnull
	@Deprecated
	default Document getProperties() {
		throw new UnsupportedCloudFeatureException();
	}

	@Nonnull
	default String formatString() {
		return "Service[" + getName() + ":" + getUniqueId() + " task=" + getTask().getName() + " state=" + getState() + "]";
	}
}
