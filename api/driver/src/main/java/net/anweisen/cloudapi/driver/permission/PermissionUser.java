package net.anweisen.cloudapi.driver.permission;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PermissionUser extends Permissible {

	@Nonnull
	UUID getUniqueId();

	@Nonnull
	Collection<PermissionUserGroupInfo> getGroups();

	/**
	 * Requires the user to be updates via {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param group the name of the group to add, may be case-sensitive, depending on the cloud
	 */
	void addGroup(@Nonnull String group);

	/**
	 * If the user has already the group, commonly the time until the group expires will be set to the given time.
	 * This may vary depending on the cloud.
	 *
	 * Requires the user to be updates via {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param group the name of the group to add, may be case-sensitive, depending on the cloud
	 * @param time when the permission should expire
	 * @param unit the unit for {@code time}
	 *
	 * @throws UnsupportedOperationException
	 *        If this cloud does not support permission timeouts for users
	 */
	void addGroup(@Nonnull String group, long time, @Nonnull TimeUnit unit);

	/**
	 * Requires the user to be updates via {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param group the name of the group to remove, may be case-sensitive, depending on the cloud
	 */
	void removeGroup(@Nonnull String group);

	/**
	 * @param group the name of the group, may be case-sensitive, depending on the cloud
	 * @return if this user is in the given group
	 */
	boolean hasGroup(@Nonnull String group);

}
