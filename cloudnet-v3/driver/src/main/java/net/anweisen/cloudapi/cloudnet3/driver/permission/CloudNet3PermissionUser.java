package net.anweisen.cloudapi.cloudnet3.driver.permission;

import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import net.anweisen.cloudapi.cloudnet3.driver.permission.info.CloudNet3PermissionUserGroupInfo;
import net.anweisen.cloudapi.driver.permission.PermissionUser;
import net.anweisen.cloudapi.driver.permission.info.PermissionUserGroupInfo;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PermissionUser extends CloudNet3Permissible implements PermissionUser {

	public final IPermissionUser user;

	public CloudNet3PermissionUser(@Nonnull IPermissionUser user) {
		super(user);
		this.user = user;
	}

	@Nonnull
	@Override
	public UUID getUniqueId() {
		return user.getUniqueId();
	}

	@Nonnull
	@Override
	public Collection<PermissionUserGroupInfo> getGroups() {
		return user.getGroups().stream().map(CloudNet3PermissionUserGroupInfo::new).collect(Collectors.toList());
	}

	@Override
	public void addGroup(@Nonnull String group) {
		user.addGroup(group);
	}

	@Override
	public void addGroup(@Nonnull String group, long time, @Nonnull TimeUnit unit) {
		user.addGroup(group, time, unit);
	}

	@Override
	public void removeGroup(@Nonnull String group) {
		user.removeGroup(group);
	}

	@Override
	public boolean hasGroup(@Nonnull String group) {
		return user.inGroup(group);
	}
}
