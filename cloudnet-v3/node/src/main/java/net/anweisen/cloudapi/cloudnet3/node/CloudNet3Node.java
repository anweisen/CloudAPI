package net.anweisen.cloudapi.cloudnet3.node;

import de.dytanic.cloudnet.CloudNet;
import net.anweisen.cloudapi.cloudnet3.driver.CloudNet3Driver;
import net.anweisen.cloudapi.cloudnet3.driver.node.CloudNet3NodeInfo;
import net.anweisen.cloudapi.cloudnet3.node.event.mapper.CloudNet3NodeEventMapper;
import net.anweisen.cloudapi.cloudnet3.node.module.CloudNet3ModuleManager;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.utils.defaults.event.DefaultEventManager;
import net.anweisen.cloudapi.driver.utils.defaults.event.QueuedEventManager;
import net.anweisen.cloudapi.node.CloudNode;
import net.anweisen.cloudapi.node.module.ModuleManager;
import net.anweisen.utilities.common.logging.ILogger;

import javax.annotation.Nonnull;
import java.io.File;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Node extends CloudNet3Driver implements CloudNode {

	protected static final CloudNet cloud = CloudNet.getInstance();

	private final File rootFolder;

	private QueuedEventManager queuedEventManager = new QueuedEventManager();
	private EventManager eventManager;

	private final CloudNet3ModuleManager moduleManager;

	public CloudNet3Node(@Nonnull File rootFolder) {
		ILogger.setConstantFactory(this.getLogger());

		this.rootFolder = rootFolder;
		this.moduleManager = new CloudNet3ModuleManager(new File(rootFolder, "modules"));
	}

	public void load() {
		getModuleManager().resolveModules();
		getModuleManager().loadModules();
	}

	public void enable() {
		getModuleManager().enableModules();

		eventManager = new DefaultEventManager(new CloudNet3NodeEventMapper());
		queuedEventManager.copyTo(eventManager);
		queuedEventManager = null;
	}

	public void stop() {
		getModuleManager().disableModules();
	}

	@Nonnull
	@Override
	public NodeInfo getNode() {
		return new CloudNet3NodeInfo(cloud.getClusterNodeServerProvider().getSelfNode().getNodeInfo());
	}

	@Nonnull
	@Override
	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	@Nonnull
	@Override
	public EventManager getEventManager() {
		if (eventManager == null)
			return queuedEventManager;

		return eventManager;
	}
}
