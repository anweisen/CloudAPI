package net.anweisen.cloudapi.node.module;

import net.anweisen.utilities.common.config.FileDocument;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class DefaultWrappedModule implements Module {

	private final ModuleController controller;
	private final Supplier<FileDocument> configGetter;
	private final Runnable configReloader;

	public DefaultWrappedModule(@Nonnull ModuleController controller, @Nonnull Supplier<FileDocument> configGetter, @Nonnull Runnable configReloader) {
		this.controller = controller;
		this.configGetter = configGetter;
		this.configReloader = configReloader;
	}

	@Nonnull
	@Override
	public ModuleController getController() {
		return controller;
	}

	@Nonnull
	@Override
	public FileDocument getConfig() {
		return configGetter.get();
	}

	@Nonnull
	@Override
	public FileDocument reloadConfig() {
		configReloader.run();
		return getConfig();
	}
}
