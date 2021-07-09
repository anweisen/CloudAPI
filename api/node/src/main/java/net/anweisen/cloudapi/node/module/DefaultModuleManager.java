package net.anweisen.cloudapi.node.module;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.utilities.common.config.FileDocument;
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

	private final List<ModuleController> modules = new CopyOnWriteArrayList<>();
	private final File modulesFolder;

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
		modules.clear();

		for (File file : modulesFolder.listFiles((dir, name) -> name.endsWith(".jar"))) {
			try {
				CloudDriver.getInstance().getLogger().info("Loading module {}..", FileUtils.getRealFileName(file));
				modules.add(new DefaultModuleController(file, this));
			} catch (Throwable ex) {
				CloudDriver.getInstance().getLogger().error("Could not init module '{}'", FileUtils.getRealFileName(file), ex);
			}
		}
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
