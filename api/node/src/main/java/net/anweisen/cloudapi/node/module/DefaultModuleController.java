package net.anweisen.cloudapi.node.module;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.exceptions.IllegalModuleConfigException;
import net.anweisen.cloudapi.driver.exceptions.IllegalModuleException;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.common.config.FileDocument;
import net.anweisen.utilities.common.misc.FileUtils;

import javax.annotation.Nonnull;
import java.io.*;
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

	private File dataFolder;
	private CloudModule module;
	private ModuleConfig moduleConfig;
	private ModuleClassLoader classLoader;

	private ModuleState state = ModuleState.DISABLED;

	public DefaultModuleController(@Nonnull File jarFile, @Nonnull DefaultModuleManager manager) throws Exception {
		this.manager = manager;
		init(jarFile, manager.getModulesFolder());
	}

	private void init(@Nonnull File jarFile, @Nonnull File modulesFolder) throws Exception {
		URL url = jarFile.toURI().toURL();

		classLoader = new ModuleClassLoader(url, this.getClass().getClassLoader());

		InputStream input = classLoader.getResourceAsStream(CONFIG_RESOURCE);
		if (input == null) throw new IllegalModuleConfigException("No such resource " + CONFIG_RESOURCE);

		InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
		Document document = Document.parseJson(reader);

		if (!document.contains("name")) throw new IllegalModuleConfigException("Missing property 'name'");
		if (!document.contains("version")) throw new IllegalModuleConfigException("Missing property 'version'");
		if (!document.contains("author")) throw new IllegalModuleConfigException("Missing property 'author'");
		if (!document.contains("main")) throw new IllegalModuleConfigException("Missing property 'main'");

		moduleConfig = new ModuleConfig(
			document.getString("name"),
			document.getString("author"),
			document.getString("version"),
			document.getString("main"),
			document.getString("description", ""),
			document.getString("website", ""),
			FileUtils.getRealFileName(jarFile)
		);

		dataFolder = new File(modulesFolder, moduleConfig.getName());

		String mainClassName = document.getString("main");
		Class<?> mainClass = classLoader.loadClass(mainClassName);
		Constructor<?> constructor = mainClass.getDeclaredConstructor();
		Object instance = constructor.newInstance();
		if (!(instance instanceof CloudModule)) throw new IllegalModuleException("Main class (" + mainClass.getName() + ") does not extend " + CloudModule.class.getName());

		module = (CloudModule) instance;
		module.controller = this;

		classLoader.module = module;
	}

	@Nonnull
	@Override
	public ModuleController loadModule() {
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

	@Nonnull
	@Override
	public ModuleController enableModule() {
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

	@Nonnull
	@Override
	public ModuleController disableModule() {
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
