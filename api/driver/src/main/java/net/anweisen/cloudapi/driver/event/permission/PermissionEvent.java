package net.anweisen.cloudapi.driver.event.permission;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.driver.permission.PermissionManager;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class PermissionEvent implements Event {

	@Nonnull
	public PermissionManager getManager() {
		return getDriver().getPermissionManager();
	}

}
