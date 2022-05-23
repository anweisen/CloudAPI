package net.anweisen.cloudapi.cloudnet3.node.module;

import de.dytanic.cloudnet.driver.module.IModuleWrapper;
import de.dytanic.cloudnet.driver.module.ModuleConfiguration;
import de.dytanic.cloudnet.driver.module.driver.DriverModule;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.node.module.*;
import net.anweisen.utilities.common.config.FileDocument;

import javax.annotation.Nonnull;
import java.io.File;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3WrappedModuleController implements ModuleController {

	private final IModuleWrapper wrapper;
	private final ModuleManager manager;

	private Module module;

	public CloudNet3WrappedModuleController(@Nonnull IModuleWrapper wrapper, @Nonnull ModuleManager manager) {
		this.wrapper = wrapper;
		this.manager = manager;
	}

	@Nonnull
	@Override
	public ModuleController loadModule() {
		wrapper.loadModule();
		return this;
	}

	@Nonnull
	@Override
	public ModuleController enableModule() {
		wrapper.startModule();
		return this;
	}

	@Nonnull
	@Override
	public ModuleController disableModule() {
		wrapper.stopModule();
		return this;
	}

	@Nonnull
	@Override
	public Module getModule() {
		if (module == null)
			module = new DefaultWrappedModule(this, this::getConfig, this::reloadConfig);

		return module;
	}

	@Nonnull
	@Override
	public ModuleManager getModuleManager() {
		return manager;
	}

	@Nonnull
	public FileDocument getConfig() {
		if (!(wrapper.getModule() instanceof DriverModule))
			throw new UnsupportedOperationException(wrapper.getModule().getClass().getName() + " does not support a config");
		DriverModule module = (DriverModule) wrapper.getModule();
		return Mapping.mapDocument(module.getConfig()).asFileDocument(wrapper.getDataDirectory().resolve("config.json").toFile());
	}

	public void reloadConfig() {
		if (!(wrapper.getModule() instanceof DriverModule))
			throw new UnsupportedOperationException(wrapper.getModule().getClass().getName() + " does not support a config");
		DriverModule module = (DriverModule) wrapper.getModule();
		module.reloadConfig();
	}

	@Nonnull
	@Override
	public File getDataFolder() {
		return wrapper.getDataDirectory().toFile();
	}

	@Nonnull
	@Override
	public ModuleConfig getModuleConfig() {
		ModuleConfiguration config = wrapper.getModuleConfiguration();
		return new ModuleConfig(
			config.getName(),
			config.getAuthor(),
			config.getVersion(),
			config.getDescription(),
			wrapper.getModuleConfiguration().getName() + ".jar",
			config.getMainClass(),
			config.getWebsite(),
			new String[0]
		);
	}

	@Nonnull
	@Override
	public ModuleState getState() {
		switch (wrapper.getModuleLifeCycle()) {
			default:        return ModuleState.DISABLED;
			case LOADED:    return ModuleState.LOADED;
			case STARTED:   return ModuleState.ENABLED;
		}
	}

	@Nonnull
	@Override
	public ModuleClassLoader getClassLoader() {
		throw new IllegalStateException("Cannot get ModuleClassLoader of wrapped module");
	}

	@Override
	public boolean isWrapped() {
		return true;
	}
}
