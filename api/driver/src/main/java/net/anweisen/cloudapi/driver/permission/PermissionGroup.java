package net.anweisen.cloudapi.driver.permission;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PermissionGroup extends Permissible {

	/**
	 * @return the sortId of this group
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support sortIds for permission groups
	 */
	int getSortId();

	/**
	 * Requires the group to be updated via {@link net.anweisen.cloudapi.driver.permission.PermissionManager#updateGroup(PermissionGroup)}.
	 *
	 * @param id the new sortId
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support group sortIds
	 */
	void setSortId(int id);

	boolean isDefaultGroup();

	/**
	 * Requires the group to be updated via {@link net.anweisen.cloudapi.driver.permission.PermissionManager#updateGroup(PermissionGroup)}.
	 *
	 * @param defaultGroup if this group should be the default group
	 */
	void setDefaultGroup(boolean defaultGroup);

	/**
	 * @return the prefix of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getPrefix();

	/**
	 * Requires the group to be updated via {@link net.anweisen.cloudapi.driver.permission.PermissionManager#updateGroup(PermissionGroup)}.
	 *
	 * @param prefix the new prefix
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support prefixes for permission groups
	 */
	void setPrefix(@Nonnull String prefix);

	/**
	 * @return the suffix of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getSuffix();

	/**
	 * Requires the group to be updated via {@link net.anweisen.cloudapi.driver.permission.PermissionManager#updateGroup(PermissionGroup)}.
	 *
	 * @param suffix the new prefix
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support suffixes for permission groups
	 */
	void setSuffix(@Nonnull String suffix);

	/**
	 * @return the color of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getColor();

	/**
	 * Requires the group to be updated via {@link net.anweisen.cloudapi.driver.permission.PermissionManager#updateGroup(PermissionGroup)}.
	 *
	 * @param color the new color
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support colors for permission groups
	 */
	void setColor(@Nonnull String color);

	/**
	 * @return the display name of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getDisplay();

	/**
	 * Requires the group to be updated via {@link net.anweisen.cloudapi.driver.permission.PermissionManager#updateGroup(PermissionGroup)}.
	 *
	 * @param display the new display name
	 *
	 * @throws UnsupportedOperationException
	 *         If this cloud does not support display names for permission groups
	 */
	void setDisplay(@Nonnull String display);

}
