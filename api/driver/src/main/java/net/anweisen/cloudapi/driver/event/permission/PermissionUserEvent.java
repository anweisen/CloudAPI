package net.anweisen.cloudapi.driver.event.permission;

import net.anweisen.cloudapi.driver.permission.PermissionUser;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class PermissionUserEvent extends PermissionEvent {

	private final PermissionUser user;

	public PermissionUserEvent(@Nonnull PermissionUser user) {
		this.user = user;
	}

	@Nonnull
	public PermissionUser getUser() {
		return user;
	}
}
