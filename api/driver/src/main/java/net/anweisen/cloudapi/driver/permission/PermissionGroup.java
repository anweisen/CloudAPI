package net.anweisen.cloudapi.driver.permission;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.exceptions.UnsupportedCloudFeatureException;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PermissionGroup extends Permissible {

	@Override
	default void update() {
		CloudDriver.getInstance().getPermissionManager().updateGroup(this);
	}

	@Nonnull
	@Override
	default Task<Void> updateAsync() {
		return CloudDriver.getInstance().getPermissionManager().updateGroupAsync(this);
	}

	/**
	 * @return the sortId of this group
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support sortIds for permission groups
	 */
	int getSortId();

	/**
	 * Requires the group to be updated via {@link #update()}.
	 *
	 * @param id the new sortId
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support group sortIds
	 */
	void setSortId(int id);

	boolean isDefaultGroup();

	/**
	 * Requires the group to be updated via {@link #update()}.
	 *
	 * @param defaultGroup if this group should be the default group
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If the default group cannot be changed through code
	 */
	void setDefaultGroup(boolean defaultGroup);

	/**
	 * @return the prefix of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getPrefix();

	/**
	 * Requires the group to be updated via {@link #update()}.
	 *
	 * @param prefix the new prefix
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support prefixes for permission groups
	 */
	void setPrefix(@Nonnull String prefix);

	/**
	 * @return the suffix of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getSuffix();

	/**
	 * Requires the group to be updated via {@link #update()}.
	 *
	 * @param suffix the new prefix
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support suffixes for permission groups
	 */
	void setSuffix(@Nonnull String suffix);

	/**
	 * @return the color of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getColor();

	/**
	 * Requires the group to be updated via {@link #update()}.
	 *
	 * @param color the new color
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support colors for permission groups
	 */
	void setColor(@Nonnull String color);

	/**
	 * @return the display name of this group or an empty string if not supported or not set
	 */
	@Nonnull
	String getDisplay();

	/**
	 * Requires the group to be updated via {@link #update()}.
	 *
	 * @param display the new display name
	 *
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support display names for permission groups
	 */
	void setDisplay(@Nonnull String display);

}
