package net.anweisen.cloudapi.node.module;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.node.CloudNode;
import net.anweisen.utilities.common.config.FileDocument;
import net.anweisen.utilities.common.logging.ILogger;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class CloudModule implements Module {

	ModuleController controller;

	public CloudModule() {
	}

	@Nonnull
	public final CloudDriver getDriver() {
		return CloudDriver.getInstance();
	}

	@Nonnull
	public final CloudNode getCloud() {
		return CloudNode.getInstance();
	}

	@Nonnull
	public ILogger getLogger() {
		return getDriver().getLogger();
	}

	@Nonnull
	public final EventManager getEventManager() {
		return getDriver().getEventManager();
	}

	@Nonnull
	public final CloudMessenger getMessenger() {
		return getDriver().getMessenger();
	}

	public final void registerListeners(@Nonnull Object... listeners) {
		getEventManager().registerListeners(listeners);
	}

	@Nonnull
	public final FileDocument getConfig() {
		return getController().getConfig();
	}

	@Nonnull
	public final ModuleConfig getModuleConfig() {
		return getController().getModuleConfig();
	}

	@Nonnull
	@Override
	public final ModuleController getController() {
		if (controller == null) throw new IllegalStateException("Still in initialization");
		return controller;
	}

	@Nonnull
	public final ModuleState getState() {
		return getController().getState();
	}

	protected void onLoad() throws Exception {}
	protected void onEnable() throws Exception {}
	protected void onDisable() throws Exception {}

	@Override
	public String toString() {
		return getModuleConfig().getFullName();
	}

}
