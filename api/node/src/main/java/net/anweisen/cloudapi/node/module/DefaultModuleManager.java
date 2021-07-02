package net.anweisen.cloudapi.node.module;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.utilities.common.config.FileDocument;
import net.anweisen.utilities.common.misc.FileUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1..0
 */
public abstract class DefaultModuleManager implements ModuleManager {

	private final List<ModuleController> modules = new CopyOnWriteArrayList<>();
	private final File modulesFolder;

	public DefaultModuleManager(@Nonnull File modulesFolder) {
		this.modulesFolder = modulesFolder;
		modulesFolder.mkdirs();
	}

	@Override
	public void loadModules() {
		for (File file : modulesFolder.listFiles((dir, name) -> name.endsWith(".jar"))) {
			try {
				CloudDriver.getInstance().getLogger().info("Loading module {}..", FileUtils.getRealFileName(file));
				modules.add(new DefaultModuleController(file, this));
			} catch (Throwable ex) {
				CloudDriver.getInstance().getLogger().error("Could not init module '{}'", FileUtils.getRealFileName(file), ex);
			}
		}

		for (ModuleController module : modules) {
			module.loadModule();
		}
	}

	@Override
	public void enableModules() {
		for (ModuleController module : modules) {
			module.enableModule();
		}
	}

	@Override
	public void disableModules() {
		for (ModuleController module : modules) {
			module.disableModule();
		}

		modules.clear();
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

}
