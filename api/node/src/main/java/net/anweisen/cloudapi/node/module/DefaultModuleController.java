package net.anweisen.cloudapi.node.module;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.exceptions.IllegalModuleConfigException;
import net.anweisen.cloudapi.driver.exceptions.IllegalModuleException;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.common.misc.FileUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class DefaultModuleController implements ModuleController {

	protected static final String CONFIG_RESOURCE = "module.json";

	private final DefaultModuleManager manager;
	private final File jarFile;

	private File dataFolder;
	private CloudModule module;
	private ModuleConfig moduleConfig;
	private ModuleClassLoader classLoader;

	private ModuleState state = ModuleState.DISABLED;

	public DefaultModuleController(@Nonnull File jarFile, @Nonnull DefaultModuleManager manager) {
		this.manager = manager;
		this.jarFile = jarFile;
	}

	public void initConfig() throws Exception {
		URL url = jarFile.toURI().toURL();

		classLoader = new ModuleClassLoader(url, this.getClass().getClassLoader());

		InputStream input = classLoader.getResourceAsStream(CONFIG_RESOURCE);
		if (input == null) throw new IllegalModuleConfigException("No such resource " + CONFIG_RESOURCE);

		InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
		Document document;

		try {
			document = Document.parseJson(reader);
		} catch (Exception ex) {
			throw new IllegalModuleConfigException("Unable to parse module config", ex);
		}

		if (!document.contains("name")) throw new IllegalModuleConfigException("Missing property 'name'");
		if (!document.contains("version")) throw new IllegalModuleConfigException("Missing property 'version'");
		if (!document.contains("author")) throw new IllegalModuleConfigException("Missing property 'author'");
		if (!document.contains("main")) throw new IllegalModuleConfigException("Missing property 'main'");

		moduleConfig = new ModuleConfig(
			document.getString("name"),
			document.getString("author"),
			document.getString("version"),
			document.getString("description", ""),
			FileUtils.getRealFileName(jarFile),
			document.getString("main"),
			document.getString("website", ""),
			document.getStringArray("depends")
		);

	}

	public void initModule() throws Exception {

		dataFolder = new File(manager.getModulesFolder(), moduleConfig.getName());

		Class<?> mainClass = classLoader.loadClass(moduleConfig.getMainClass());
		Constructor<?> constructor = mainClass.getDeclaredConstructor();
		Object instance = constructor.newInstance();
		if (!(instance instanceof CloudModule)) throw new IllegalModuleException("Main class (" + moduleConfig.getMainClass() + ") does not extend " + CloudModule.class.getName());

		module = (CloudModule) instance;
		module.controller = this;

		classLoader.module = module;

	}

	@Nonnull
	@Override
	public ModuleController loadModule() {
		synchronized (this) {
			if (state != ModuleState.DISABLED) return this; // Must be disabled first

			CloudDriver.getInstance().getLogger().info("Module {} by {} is being loaded..", module, moduleConfig.getAuthor());

			try {
				module.onLoad();
				state = ModuleState.LOADED;
			} catch (Throwable ex) {
				CloudDriver.getInstance().getLogger().error("An error occurred while loading module {}", module, ex);
				disableModule();
			}

			return this;
		}
	}

	@Nonnull
	@Override
	public ModuleController enableModule() {
		synchronized (this) {
			if (state != ModuleState.LOADED) return this; // Must be loaded first

			CloudDriver.getInstance().getLogger().info("Module {} by {} is being enabled..", module, moduleConfig.getAuthor());

			try {
				module.onEnable();
				state = ModuleState.ENABLED;
			} catch (Throwable ex) {
				CloudDriver.getInstance().getLogger().error("An error occurred while enabling module {}", module, ex);
				disableModule();
			}

			return this;
		}
	}

	@Nonnull
	@Override
	public ModuleController disableModule() {
		synchronized (this) {
			CloudDriver.getInstance().getLogger().info("Module {} by {} is being disabled..", module, moduleConfig.getAuthor());
			CloudDriver.getInstance().getEventManager().unregisterListeners(classLoader);

			try {
				module.onDisable();
			} catch (Throwable ex) {
				CloudDriver.getInstance().getLogger().error("An error occurred while disabling module {}", module, ex);
			}

			state = ModuleState.DISABLED;

			return this;
		}
	}

	@Nonnull
	@Override
	public Module getModule() {
		return module;
	}

	@Nonnull
	@Override
	public File getDataFolder() {
		return dataFolder;
	}

	@Nonnull
	@Override
	public ModuleConfig getModuleConfig() {
		return moduleConfig;
	}

	@Nonnull
	@Override
	public ModuleState getState() {
		return state;
	}

	@Nonnull
	@Override
	public ModuleManager getModuleManager() {
		return manager;
	}

	@Nonnull
	@Override
	public ModuleClassLoader getClassLoader() {
		return classLoader;
	}

	@Override
	public boolean isWrapped() {
		return false;
	}

}
