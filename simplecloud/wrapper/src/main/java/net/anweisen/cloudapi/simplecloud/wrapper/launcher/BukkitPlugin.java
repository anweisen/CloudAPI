package net.anweisen.cloudapi.simplecloud.wrapper.launcher;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.simplecloud.wrapper.SimpleCloudWrapper;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class BukkitPlugin extends JavaPlugin {

	@Override
	public void onLoad() {
		CloudDriver.setInstance(new SimpleCloudWrapper());
	}

}
