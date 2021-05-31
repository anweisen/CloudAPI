package net.anweisen.cloudapi.driver;

import net.anweisen.cloudapi.driver.permission.PermissionManager;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface CloudDriver {

	@Nonnull
	PermissionManager getPermissionManager();

	static CloudDriver getInstance() {
		return Holder.instance;
	}

	static void setInstance(CloudDriver instance) {
		if (instance == null) throw new NullPointerException();
		Holder.instance = instance;
	}

	final class Holder {
		private Holder() {}
		private static CloudDriver instance;
	}
}
