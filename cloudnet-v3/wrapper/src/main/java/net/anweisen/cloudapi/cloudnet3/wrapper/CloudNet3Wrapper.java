package net.anweisen.cloudapi.cloudnet3.wrapper;

import de.dytanic.cloudnet.wrapper.Wrapper;
import net.anweisen.cloudapi.cloudnet3.driver.CloudNet3Driver;

import net.anweisen.cloudapi.cloudnet3.driver.service.specific.CloudNet3ServiceInfo;
import net.anweisen.cloudapi.cloudnet3.wrapper.event.mapper.CloudNet3WrapperEventMapper;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.cloudapi.driver.utils.defaults.event.DefaultEventManager;
import net.anweisen.cloudapi.driver.utils.defaults.event.EventMapper;
import net.anweisen.cloudapi.wrapper.CloudWrapper;
import net.anweisen.utilities.common.logging.ILogger;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Wrapper extends CloudNet3Driver implements CloudWrapper {

	protected static final Wrapper wrapper = Wrapper.getInstance();

	private EventManager eventManager;

	public CloudNet3Wrapper() {
		ILogger.setConstantFactory(this.getLogger());
	}

	@Nonnull
	@Override
	public NodeInfo getNode() {
		return getCurrentServiceInfo().getNode();
	}

	@Nonnull
	@Override
	public ServiceInfo getCurrentServiceInfo() {
		return new CloudNet3ServiceInfo(wrapper.getCurrentServiceInfoSnapshot());
	}

	@Nonnull
	@Override
	public ServiceInfo getLastServiceInfo() {
		return new CloudNet3ServiceInfo(wrapper.getLastServiceInfoSnapShot());
	}

	@Nonnull
	@Override
	public EventManager getEventManager() {
		if (eventManager == null)
			eventManager = new DefaultEventManager(new CloudNet3WrapperEventMapper());

		return eventManager;
	}
}
