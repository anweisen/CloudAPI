package net.anweisen.cloudapi.node.module;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.utilities.common.logging.ILogger;
import net.anweisen.utilities.common.misc.FileUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class DefaultModuleManager implements ModuleManager {

	private final File modulesFolder;
	private List<DefaultModuleController> modules = Collections.emptyList();

	public DefaultModuleManager(@Nonnull File modulesFolder) {
		this.modulesFolder = modulesFolder;
		modulesFolder.mkdirs();
	}

	@Override
	public synchronized void reloadModules() {
		getLogger().info("Reloading api modules..");
		disableModules();
		resolveModules();
		loadModules();
		enableModules();
		getLogger().info("All api modules were reloaded");
	}

	@Override
	public synchronized void resolveModules() {
		for (DefaultModuleController module : modules) {
			try {
				module.getClassLoader().close();
			} catch (Throwable ex) {
				CloudDriver.getInstance().getLogger().error("An error occurred while closing class loader", ex);
			}
		}
		this.modules.clear();

		List<DefaultModuleController> modules = new CopyOnWriteArrayList<>();

		// Resolve modules and load configs
		for (File file : modulesFolder.listFiles((dir, name) -> name.endsWith(".jar"))) {
			try {
				CloudDriver.getInstance().getLogger().info("Resolving module {}..", FileUtils.getRealFileName(file));
				DefaultModuleController module = new DefaultModuleController(file, this);
				module.initConfig();

				modules.add(module);
			} catch (Throwable ex) {
				CloudDriver.getInstance().getLogger().error("Could not resolve module {}", FileUtils.getRealFileName(file), ex);
			}
		}

		// Check if the depends are existing
		for (DefaultModuleController module : modules) {
			for (String depend : module.getModuleConfig().getDepends()) {
				if (hasModule(modules, depend)) continue;

				modules.remove(module);
				CloudDriver.getInstance().getLogger().error("Could not find required depend '{}' for module {}", depend, module.getModuleConfig());
			}
		}

		// Order the modules by depends
		modules.sort((module1, module2) -> {

			// If this module requires the other module, load this after the other
			if (arrayContains(module1.getModuleConfig().getDepends(), module2.getModuleConfig().getName()))
				return -1;

			// If the other module requires this module, load the other after this
			if (arrayContains(module2.getModuleConfig().getDepends(), module1.getModuleConfig().getName()))
				return 1;

			return 0;
		});

		// Init modules
		for (DefaultModuleController module : modules) {
			try {
				module.initModule();
			} catch (Throwable ex) {
				modules.remove(module);
				CloudDriver.getInstance().getLogger().error("An error occurred while initializing {}", module.getModuleConfig());
			}
		}

		this.modules = modules;

	}

	private boolean hasModule(@Nonnull Collection<DefaultModuleController> modules, @Nonnull String depend) {
		for (DefaultModuleController module : modules) {
			if (module.getModuleConfig().getName().equals(depend))
				return true;
		}
		return false;
	}

	private <T> boolean arrayContains(@Nonnull T[] array, T subject) {
		for (T t : array) {
			if (t.equals(subject))
				return true;
		}
		return false;
	}

	@Override
	public synchronized void loadModules() {
		for (ModuleController module : modules) {
			module.loadModule();
		}
	}

	@Override
	public synchronized void enableModules() {
		for (ModuleController module : modules) {
			module.enableModule();
		}
	}

	@Override
	public synchronized void disableModules() {
		for (ModuleController module : modules) {
			module.disableModule();
		}
	}

	@Nonnull
	@Override
	public File getModulesFolder() {
		return modulesFolder;
	}

	@Nonnull
	@Override
	public Collection<ModuleController> getApiModules() {
		return Collections.unmodifiableCollection(modules);
	}

	protected ILogger getLogger() {
		return CloudDriver.getInstance().getLogger();
	}

}
