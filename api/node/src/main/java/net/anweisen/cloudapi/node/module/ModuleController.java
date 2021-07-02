package net.anweisen.cloudapi.node.module;

import net.anweisen.utilities.common.config.FileDocument;

import javax.annotation.Nonnull;
import java.io.File;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see Module
 * @see ModuleManager
 */
public interface ModuleController {

	@Nonnull
	ModuleController loadModule();

	@Nonnull
	ModuleController enableModule();

	@Nonnull
	ModuleController disableModule();

	@Nonnull
	Module getModule();

	@Nonnull
	ModuleManager getModuleManager();

	@Nonnull
	FileDocument getConfig();

	@Nonnull
	FileDocument reloadConfig();

	@Nonnull
	File getDataFolder();

	@Nonnull
	ModuleConfig getModuleConfig();

	@Nonnull
	ModuleState getState();

	/**
	 * @throws IllegalStateException
	 *         If this module is a {@link #isWrapped() wrapped} module
	 */
	@Nonnull
	ModuleClassLoader getClassLoader();

	/**
	 * @return {@code true} if this module is not a CloudAPI module
	 */
	boolean isWrapped();

}
