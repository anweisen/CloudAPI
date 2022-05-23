package net.anweisen.cloudapi.simplecloud.wrapper;

import eu.thesimplecloud.plugin.startup.CloudPlugin;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.cloud.CloudImplementationInfo;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.cloudapi.driver.utils.defaults.event.EventMapper;
import net.anweisen.cloudapi.simplecloud.driver.SimpleCloudDriver;
import net.anweisen.cloudapi.simplecloud.driver.service.specific.SimpleCloudServiceInfo;
import net.anweisen.cloudapi.wrapper.CloudWrapper;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SimpleCloudWrapper extends SimpleCloudDriver implements CloudWrapper {

	private final CloudPlugin plugin = CloudPlugin.getInstance();

	public SimpleCloudWrapper() {
		super(null);
	}

	@Nonnull
	@Override
	public NodeInfo getNode() {
		return Objects.requireNonNull(CloudDriver.getInstance().getNodeManager().getNode(plugin.thisService().getWrapperName()));
	}

	@Nonnull
	@Override
	public ServiceInfo getCurrentServiceInfo() {
		return new SimpleCloudServiceInfo(plugin.thisService());
	}

	@Nonnull
	@Override
	public ServiceInfo getLastServiceInfo() {
		return getCurrentServiceInfo();
	}
}
