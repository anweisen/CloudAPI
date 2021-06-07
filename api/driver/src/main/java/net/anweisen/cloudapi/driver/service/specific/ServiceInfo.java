package net.anweisen.cloudapi.driver.service.specific;

import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.utilities.commons.config.Document;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ServiceInfo {

	@Nonnull
	UUID getUniqueId();

	@Nonnull
	String getName();

	/**
	 * @return the id of the service inside of the task, eg 2 for Lobby-2
	 */
	int getServiceTaskId();

	@Nonnull
	ServiceTask getTask();

	@Nonnull
	NodeInfo getNode();

	@Nonnull
	ServiceState getState();

	@Nonnull
	ServiceType getType();

	/**
	 * @return if the service isset to invisible, always {@code false} if not supported
	 */
	boolean isInvisible();

	@Nonnull
	ServiceController getController();

	@Nonnull
	Document getProperties();

}
