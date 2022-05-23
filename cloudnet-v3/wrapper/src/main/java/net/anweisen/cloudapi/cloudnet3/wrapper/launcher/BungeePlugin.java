package net.anweisen.cloudapi.cloudnet3.wrapper.launcher;

import net.anweisen.cloudapi.cloudnet3.wrapper.CloudNet3Wrapper;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class BungeePlugin extends Plugin {

	@Override
	public void onLoad() {
		CloudDriver.setInstance(new CloudNet3Wrapper());
	}

}
