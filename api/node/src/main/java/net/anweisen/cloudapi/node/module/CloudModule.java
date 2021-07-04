package net.anweisen.cloudapi.node.module;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.EventManager;
import net.anweisen.cloudapi.driver.message.CloudMessenger;
import net.anweisen.cloudapi.node.CloudNode;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.common.config.FileDocument;
import net.anweisen.utilities.common.logging.ILogger;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class CloudModule implements Module {

	private final Map<File, FileDocument> configs = new HashMap<>();

	private FileDocument config;

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
	@Override
	public FileDocument getConfig() {
		if (config == null)
			return reloadConfig();
		return config;
	}

	@Nonnull
	@Override
	public FileDocument reloadConfig() {
		synchronized (this) {
			File configFile = new File(getController().getDataFolder(), "config.json");
			return config = FileDocument.readJsonFile(configFile);
		}
	}

	@Nonnull
	public FileDocument getConfig(@Nonnull String filename) {
		if (!filename.contains(".")) filename += ".json";

		if (filename.equals("config.json"))
			return getConfig();

		synchronized (this) {
			File file = new File(getDataFolder(), filename);
			FileDocument config = configs.computeIfAbsent(file, key -> Document.readJsonFile(file).asFileDocument(file));
			if (!file.exists()) config.save();
			return config;
		}
	}

	@Nonnull
	public final ModuleConfig getModuleConfig() {
		return getController().getModuleConfig();
	}

	@Nonnull
	public final File getDataFolder() {
		return getController().getDataFolder();
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
