package net.anweisen.cloudapi.cloudnet3.node.launcher;

import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.module.NodeCloudNetModule;
import net.anweisen.cloudapi.cloudnet3.node.CloudNet3Node;
import net.anweisen.cloudapi.cloudnet3.node.commands.CloudNet3ApiCommand;
import net.anweisen.cloudapi.driver.CloudDriver;

import java.io.File;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudModule extends NodeCloudNetModule {

	private CloudNet3Node cloud;

	@ModuleTask(event = ModuleLifeCycle.LOADED, order = -127)
	public void onLoad() throws Exception {
		File folder = getModuleWrapper().getDataDirectory().toFile();
		folder.mkdir();
		CloudDriver.setInstance(cloud = new CloudNet3Node(folder));

		cloud.load();

		getCloudNet().getCommandMap().unregisterCommand("cloudapi:api");
		getCloudNet().getCommandMap().registerCommand(new CloudNet3ApiCommand(this));
	}

	@ModuleTask(event = ModuleLifeCycle.STARTED, order = -127)
	public void onStart() throws Exception {
		if (cloud == null) return; // Error in onLoad
		cloud.enable();
	}

	@ModuleTask(event = ModuleLifeCycle.STOPPED, order = -127)
	public void onStop() throws Exception {
		if (cloud == null) return; // Error in onLoad
		cloud.stop();
	}

	public CloudNet3Node getCloud() {
		return cloud;
	}
}
