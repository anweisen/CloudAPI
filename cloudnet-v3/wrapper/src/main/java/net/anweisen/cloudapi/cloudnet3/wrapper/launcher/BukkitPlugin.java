package net.anweisen.cloudapi.cloudnet3.wrapper.launcher;

import net.anweisen.cloudapi.cloudnet3.wrapper.CloudNet3Wrapper;
import net.anweisen.cloudapi.driver.CloudDriver;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class BukkitPlugin extends JavaPlugin {

	@Override
	public void onLoad() {
		CloudDriver.setInstance(new CloudNet3Wrapper());
	}

}
