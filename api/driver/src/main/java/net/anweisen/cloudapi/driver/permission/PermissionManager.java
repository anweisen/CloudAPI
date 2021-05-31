package net.anweisen.cloudapi.driver.permission;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PermissionManager {

	/**
	 * @return all users registered
	 */
	@Nonnull
	Collection<PermissionUser> getUsers();

	/**
	 * @param group the name of the group, may be case-sensitive, depending on the cloud
	 * @return all users with the given group
	 */
	@Nonnull
	Collection<PermissionUser> getUsersWWithGroup(@Nonnull String group);

	/**
	 * @param name the name of the users, may be case-sensitive, depending on the cloud
	 * @return the users found
	 */
	@Nonnull
	List<PermissionUser> getUsers(@Nonnull String name);

	/**
	 * @param name the name of the user, may be case-sensitive, depending on the cloud
	 * @return the first user found, {@code null} if no user was found
	 */
	@Nullable
	PermissionUser getFirstUser(@Nonnull String name);

	/**
	 * @param uniqueId the uuid of the user
	 * @return the user found, {@code null} if no user was found
	 */
	@Nullable
	PermissionUser getUser(@Nonnull UUID uniqueId);

	/**
	 * Depending on the cloud, a new user may be created,
	 * if no user existed matching the {@link PermissionUser#getUniqueId() uniqueId} of the user.
	 *
	 * @param user the user to update
	 */
	void updateUser(@Nonnull PermissionUser user);

	/**
	 * Deletes one user or all users, depending on the cloud, matching the given name.
	 * This method may be case-sensitive, depending on the cloud.
	 *
	 * @param name the name of the user to delete, may be case-sensitive, depending on the cloud
	 */
	void deleteUser(@Nonnull String name);

	/**
	 * Deletes one user or all users, depending on the cloud, matching the {@link PermissionUser#getUniqueId() uniqueId} of the user.
	 *
	 * @param user the user to delete
	 */
	void deleteUser(@Nonnull PermissionUser user);

	/**
	 * @param user the user to get the groups of
	 * @return the groups of the user, may contain the {@link #getDefaultGroup() default group} depending on the cloud
	 */
	@Nonnull
	Collection<PermissionGroup> getUserGroups(@Nonnull PermissionUser user);

	/**
	 * @param user the user to get the highest group from
	 * @return the highest group of the user, may be {@code null} if the user has no groups
	 */
	PermissionGroup getHighestGroup(@Nonnull PermissionUser user);

	/**
	 * @return the default permission group every player has, may be {@code null} if removed
	 */
	PermissionGroup getDefaultGroup();

	/**
	 * Depending on the cloud, a new group may be created,
	 * if no group existed matching the {@link PermissionGroup#getName() name} of the group.
	 *
	 * @param group the group to update
	 */
	void updateGroup(@Nonnull PermissionGroup group);

	/**
	 * Deletes one group or all groups, depending on the cloud, matching the given name.
	 * This method may be case-sensitive, depending on the cloud.
	 *
	 * @param name the name of the group to delete, may be case-sensitive, depending on the cloud
	 */
	void deleteGroup(@Nonnull String name);

	/**
	 * Deletes one group or all groups, depending on the cloud, matching the given name.
	 *
	 * @param group the group to delete
	 */
	void deleteGroup(@Nonnull PermissionGroup group);

}
