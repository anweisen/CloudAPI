package net.anweisen.cloudapi.node.module;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ModuleManager {

	/**
	 * Loads all modules
	 */
	void loadModules();

	/**
	 * Enables all modules
	 */
	void enableModules();

	/**
	 * Disables all modules
	 */
	void disableModules();

	/**
	 * @return the directory all CloudAPI modules are located in
	 */
	@Nonnull
	File getModulesFolder();

	/**
	 * @return an immutable list containing all modules loaded by the CloudAPI
	 */
	@Nonnull
	Collection<ModuleController> getApiModules();

	/**
	 * @return an immutable list containing all modules loaded by the cloud system itself
	 */
	@Nonnull
	Collection<ModuleController> getWrappedModules();

	/**
	 * @return an immutable list containing all modules loaded both by the the cloud system itself and the CloudAPI
	 */
	@Nonnull
	default Collection<ModuleController> getAllModules() {
		Collection<ModuleController> apiModules = getApiModules();
		Collection<ModuleController> wrappedModules = getWrappedModules();
		Collection<ModuleController> allModules = new ArrayList<>(apiModules.size() + wrappedModules.size());
		allModules.addAll(apiModules);
		allModules.addAll(wrappedModules);
		return Collections.unmodifiableCollection(allModules);
	}

}
