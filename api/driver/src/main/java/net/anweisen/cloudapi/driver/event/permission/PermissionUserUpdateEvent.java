package net.anweisen.cloudapi.driver.event.permission;

import net.anweisen.cloudapi.driver.permission.PermissionUser;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class PermissionUserUpdateEvent extends PermissionUserEvent {

	public PermissionUserUpdateEvent(@Nonnull PermissionUser user) {
		super(user);
	}
}
