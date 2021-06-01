package net.anweisen.cloudapi.driver.permission;

import net.anweisen.cloudapi.driver.permission.info.PermissionInfo;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see PermissionUser
 * @see PermissionGroup
 */
public interface Permissible {

	@Nonnull
	String getName();

	@Nonnull
	Collection<String> getGroupNames();

	@Nonnull
	Collection<PermissionInfo> getPermissions();

	@Nonnull
	Collection<String> getPermissionNames();

	/**
	 * @return a map containing all permissions on specific task groups, may or may not be mutable
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permission granting on specific task groups
	 */
	@Nonnull
	Map<String, Collection<PermissionInfo>> getTaskPermissions();

	boolean hasPermission(@Nonnull String permission);

	boolean hasPermission(@Nonnull PermissionInfo permission);

	boolean hasPermission(@Nonnull String taskGroup, @Nonnull String permission);

	boolean hasPermission(@Nonnull String taskGroup, @Nonnull PermissionInfo permission);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * The permission will be added with the default potency of the cloud and no timeout.
	 *
	 * @param permission the permission to add
	 */
	void addPermission(@Nonnull String permission);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * The permission will be added with the default potency of the cloud.
	 *
	 * @param permission the permission to add
	 * @param time when the permission should expire
	 * @param unit the unit of {@code time}
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permission timeouts for this permissible
	 */
	void addPermission(@Nonnull String permission, long time, @Nonnull TimeUnit unit);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * The permission will be added with no timeout, if supported.
	 *
	 * @param permission the name of the permission
	 * @param potency the potency of the permission, will be ignored if this cloud does not support permission potencys
	 */
	void addPermission(@Nonnull String permission, int potency);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param permission the permission to add
	 * @param potency the potency of the permission, will be ignored if this cloud does not support permission potencys
	 * @param time when the permission should expire
	 * @param unit the unit of {@code time}
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permission timeouts for this permissible
	 */
	void addPermission(@Nonnull String permission, int potency, long time, @Nonnull TimeUnit unit);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param permission the permission to add
	 */
	void addPermission(@Nonnull PermissionInfo permission);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * The permission will be added with the default potency of the cloud and no timeout.
	 *
	 * @param taskGroup the group on which the permission should be granted, eg Lobby
	 * @param permission the permission to add
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permission granting on specific task groups
	 */
	void addPermission(@Nonnull String taskGroup, @Nonnull String permission);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * The permission will be added with the default potency of the cloud.
	 *
	 * @param permission the permission to add
	 * @param time when the permission should expire
	 * @param unit the unit of {@code time}
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permission timeouts for this permissible
	 *         If this cloud does not support permission granting on specific task groups
	 */
	void addPermission(@Nonnull String taskGroup, @Nonnull String permission, long time, @Nonnull TimeUnit unit);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * The permission will be added with no timeout, if supported.
	 *
	 * @param taskGroup the group on which the permission should be granted, eg Lobby
	 * @param permission the name of the permission
	 * @param potency the potency of the permission, will be ignored if this cloud does not support permission potencys
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permission granting on specific task groups
	 */
	void addPermission(@Nonnull String taskGroup, @Nonnull String permission, int potency);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param taskGroup the group on which the permission should be granted, eg Lobby
	 * @param permission the permission to add
	 * @param potency the potency of the permission, will be ignored if this cloud does not support permission potencys
	 * @param time when the permission should expire
	 * @param unit the unit of {@code time}
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permission timeouts for this permissible
	 *         If this cloud does not support permission granting on specific task groups
	 */
	void addPermission(@Nonnull String taskGroup, @Nonnull String permission, int potency, long time, @Nonnull TimeUnit unit);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param permission the permission to remove
	 * @return {@code true} if the permission was removed successfully, {@code false} if the given permission was not set
	 */
	boolean removePermission(@Nonnull String permission);

	/**
	 * Requires the permissible to be updated via {@link PermissionManager#updateGroup(PermissionGroup)}
	 * or {@link PermissionManager#updateUser(PermissionUser)}.
	 *
	 * @param taskGroup the group on which the permission should be removed, eg Lobby
	 * @param permission the permission to remove
	 * @return {@code true} if the permission was removed successfully, {@code false} if the given permission was not set
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support permissions on specific task groups
	 */
	boolean removePermission(@Nonnull String taskGroup, @Nonnull String permission);

}
