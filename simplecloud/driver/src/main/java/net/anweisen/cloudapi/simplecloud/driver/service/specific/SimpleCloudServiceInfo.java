package net.anweisen.cloudapi.simplecloud.driver.service.specific;

import eu.thesimplecloud.api.service.ICloudService;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.cloudapi.driver.service.specific.ServiceController;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.cloudapi.driver.service.specific.ServiceState;
import net.anweisen.cloudapi.driver.service.specific.ServiceType;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SimpleCloudServiceInfo implements ServiceInfo {

	private final ICloudService service;

	public SimpleCloudServiceInfo(@Nonnull ICloudService service) {
		this.service = service;
	}

	@Nonnull
	@Override
	public String getName() {
		return service.getName();
	}

	@Nonnull
	@Override
	public UUID getUniqueId() {
		return service.getUniqueId();
	}

	@Override
	public int getPort() {
		return service.getPort();
	}

	@Nonnull
	@Override
	public String getHost() {
		return service.getHost();
	}

	@Override
	public int getServiceNumber() {
		return service.getServiceNumber();
	}

	@Nonnull
	@Override
	public ServiceTask getTask() {
		return null;
	}

	@Nonnull
	@Override
	public NodeInfo getNode() {
		return Objects.requireNonNull(CloudDriver.getInstance().getNodeManager().getNode(service.getWrapperName()));
	}

	@Nonnull
	@Override
	public ServiceState getState() {
		switch (service.getState()) {
			case PREPARED:
			case STARTING:  return ServiceState.PREPARED;
			case VISIBLE:
			case INVISIBLE: return ServiceState.RUNNING;
			case CLOSED:    return ServiceState.DELETED;
			default:        throw new IllegalStateException("Unrecognized state " + service.getState());
		}
	}

	@Nonnull
	@Override
	public ServiceType getType() {
		switch (service.getServiceType()) {
			case SERVER:
			case LOBBY: return ServiceType.SERVER;
			case PROXY: return ServiceType.PROXY;
			default:    return ServiceType.OTHER;
		}
	}

	@Override
	public boolean isInvisible() {
		return service.getState() == eu.thesimplecloud.api.service.ServiceState.INVISIBLE;
	}

	@Nonnull
	@Override
	public ServiceController getController() {
		return null;
	}

	@Nonnull
	@Override
	public Document getProperties() {
		return null;
	}

	@Override
	public String toString() {
		return formatString();
	}
}
