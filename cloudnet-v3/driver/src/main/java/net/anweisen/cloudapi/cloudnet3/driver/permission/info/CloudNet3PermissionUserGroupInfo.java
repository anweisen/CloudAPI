package net.anweisen.cloudapi.cloudnet3.driver.permission.info;

import net.anweisen.cloudapi.driver.permission.info.PermissionUserGroupInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PermissionUserGroupInfo implements PermissionUserGroupInfo {

	private final de.dytanic.cloudnet.driver.permission.PermissionUserGroupInfo group;

	public CloudNet3PermissionUserGroupInfo(@Nonnull de.dytanic.cloudnet.driver.permission.PermissionUserGroupInfo group) {
		this.group = group;
	}

	@Nonnull
	@Override
	public String getName() {
		return group.getGroup();
	}

	@Override
	public long getTimeOutMillis() {
		return group.getTimeOutMillis();
	}

}
