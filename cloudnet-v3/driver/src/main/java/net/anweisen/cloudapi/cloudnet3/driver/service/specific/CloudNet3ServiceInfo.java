package net.anweisen.cloudapi.cloudnet3.driver.service.specific;

import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper.CloudNet3Document;
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
public class CloudNet3ServiceInfo implements ServiceInfo {

	private final ServiceInfoSnapshot info;

	public CloudNet3ServiceInfo(@Nonnull ServiceInfoSnapshot info) {
		this.info = info;
	}

	@Nonnull
	@Override
	public UUID getUniqueId() {
		return info.getServiceId().getUniqueId();
	}

	@Nonnull
	@Override
	public String getName() {
		return info.getName();
	}

	@Nonnull
	@Override
	public String getHost() {
		return info.getAddress().getHost();
	}

	@Override
	public int getPort() {
		return info.getAddress().getPort();
	}

	@Override
	public int getServiceNumber() {
		return info.getServiceId().getTaskServiceId();
	}

	@Nonnull
	@Override
	public ServiceTask getTask() {
		return Objects.requireNonNull(CloudDriver.getInstance().getTaskManager().getTask(info.getServiceId().getTaskName()));
	}

	@Nonnull
	@Override
	public NodeInfo getNode() {
		return Objects.requireNonNull(CloudDriver.getInstance().getNodeManager().getNode(info.getServiceId().getNodeUniqueId()));
	}

	@Nonnull
	@Override
	public ServiceState getState() {
		switch (info.getLifeCycle()) {
			case DEFINED:   return ServiceState.DEFINED;
			case PREPARED:  return ServiceState.PREPARED;
			case RUNNING:   return ServiceState.RUNNING;
			case STOPPED:   return ServiceState.STOPPED;
			case DELETED:   return ServiceState.DELETED;
			default:        throw new IllegalStateException("Unrecognized state " + info.getLifeCycle());
		}
	}

	@Nonnull
	@Override
	public ServiceType getType() {
		switch (info.getServiceId().getEnvironment().getMinecraftType()) {
			case JAVA_PROXY:
			case BEDROCK_PROXY:
				return ServiceType.PROXY;
			case JAVA_SERVER:
			case BEDROCK_SERVER:
				return ServiceType.SERVER;
			default:
				return ServiceType.OTHER;
		}
	}

	@Override
	public boolean isInvisible() {
		return false;
	}

	@Nonnull
	@Override
	public ServiceController getController() {
		return new CloudNet3ServiceController(info.provider());
	}

	@Nonnull
	@Override
	public Document getProperties() {
		return Mapping.mapDocument(info.getProperties());
	}

	@Override
	public String toString() {
		return formatString();
	}
}
