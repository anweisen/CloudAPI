package net.anweisen.cloudapi.node.module;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see ModuleController
 */
public interface Module {

	@Nonnull
	ModuleController getController();

	@Nonnull
	static Module getProvidingModule(@Nonnull Class<?> clazz) {
		ClassLoader classLoader = clazz.getClassLoader();
		if (!(classLoader instanceof ModuleClassLoader))
			throw new IllegalStateException(clazz.getName() + " is not loaded by a CloudAPI module");
		Module module = ((ModuleClassLoader) classLoader).module;
		if (module == null)
			throw new IllegalStateException("Module of " + clazz.getName() + " is still in initialization");
		return module;
	}

}
