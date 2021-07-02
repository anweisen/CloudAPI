package net.anweisen.cloudapi.node.module;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class DefaultWrappedModule implements Module {

	private final ModuleController controller;

	public DefaultWrappedModule(@Nonnull ModuleController controller) {
		this.controller = controller;
	}

	@Nonnull
	@Override
	public ModuleController getController() {
		return controller;
	}

}
