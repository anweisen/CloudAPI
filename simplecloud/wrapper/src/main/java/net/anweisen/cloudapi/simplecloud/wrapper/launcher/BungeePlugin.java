package net.anweisen.cloudapi.simplecloud.wrapper.launcher;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.simplecloud.wrapper.SimpleCloudWrapper;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class BungeePlugin extends Plugin {

	@Override
	public void onLoad() {
		CloudDriver.setInstance(new SimpleCloudWrapper());
	}

}
