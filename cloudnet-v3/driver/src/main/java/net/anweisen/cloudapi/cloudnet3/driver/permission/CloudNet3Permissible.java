package net.anweisen.cloudapi.cloudnet3.driver.permission;

import de.dytanic.cloudnet.driver.permission.IPermissible;
import de.dytanic.cloudnet.driver.permission.Permission;
import net.anweisen.cloudapi.cloudnet3.driver.permission.info.CloudNet3PermissionInfo;
import net.anweisen.cloudapi.driver.permission.Permissible;
import net.anweisen.cloudapi.driver.permission.info.PermissionInfo;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class CloudNet3Permissible implements Permissible {

	private final IPermissible permissible;

	public CloudNet3Permissible(@Nonnull IPermissible permissible) {
		this.permissible = permissible;
	}

	@Nonnull
	@Override
	public String getName() {
		return permissible.getName();
	}

	@Nonnull
	@Override
	public Collection<String> getGroupNames() {
		return permissible.getGroupNames();
	}

	@Nonnull
	@Override
	public Collection<PermissionInfo> getPermissions() {
		return permissible.getPermissions().stream().map(CloudNet3PermissionInfo::new).collect(Collectors.toList());
	}

	@Nonnull
	@Override
	public Collection<String> getPermissionNames() {
		return permissible.getPermissionNames();
	}

	@Nonnull
	@Override
	public Map<String, Collection<PermissionInfo>> getTaskPermissions() {
		Map<String, Collection<Permission>> permissions = permissible.getGroupPermissions();
		Map<String, Collection<PermissionInfo>> result = new HashMap<>();

		permissions.forEach((task, perms) -> {
			Collection<PermissionInfo> permsResult = result.computeIfAbsent(task, key -> new ArrayList<>(perms.size()));
			perms.forEach(perm -> permsResult.add(new CloudNet3PermissionInfo(perm)));
		});

		return result;
	}

	@Override
	public boolean hasPermission(@Nonnull String permission) {
		return permissible.hasPermission(permission).asBoolean();
	}

	@Override
	public boolean hasPermission(@Nonnull PermissionInfo permission) {
		return permissible.hasPermission(((CloudNet3PermissionInfo)permission).permission).asBoolean();
	}

	@Override
	public boolean hasPermission(@Nonnull String taskGroup, @Nonnull String permission) {
		return permissible.hasPermission(taskGroup, new Permission(permission)).asBoolean();
	}

	@Override
	public boolean hasPermission(@Nonnull String taskGroup, @Nonnull PermissionInfo permission) {
		return permissible.hasPermission(taskGroup, ((CloudNet3PermissionInfo)permission).permission).asBoolean();
	}

	@Override
	public void addPermission(@Nonnull String permission) {
		permissible.addPermission(permission);
	}

	@Override
	public void addPermission(@Nonnull String permission, long time, @Nonnull TimeUnit unit) {
		addPermission(permission, 0, time, unit);
	}

	@Override
	public void addPermission(@Nonnull String permission, int potency) {
		permissible.addPermission(permission, potency);
	}

	@Override
	public void addPermission(@Nonnull String permission, int potency, long time, @Nonnull TimeUnit unit) {
		permissible.addPermission(permission, new Permission(permission, potency, time, unit));
	}

	@Override
	public void addPermission(@Nonnull PermissionInfo permission) {
		permissible.addPermission(((CloudNet3PermissionInfo)permission).permission);
	}

	@Override
	public void addPermission(@Nonnull String taskGroup, @Nonnull String permission) {
		permissible.addPermission(taskGroup, permission);
	}

	@Override
	public void addPermission(@Nonnull String taskGroup, @Nonnull String permission, long time, @Nonnull TimeUnit unit) {
		addPermission(taskGroup, permission, 0 , time, unit);
	}

	@Override
	public void addPermission(@Nonnull String taskGroup, @Nonnull String permission, int potency) {
		permissible.addPermission(permission, permission, potency);
	}

	@Override
	public void addPermission(@Nonnull String taskGroup, @Nonnull String permission, int potency, long time, @Nonnull TimeUnit unit) {
		permissible.addPermission(permission, new Permission(permission, potency, time, unit));
	}

	@Override
	public boolean removePermission(@Nonnull String permission) {
		return permissible.removePermission(permission);
	}

	@Override
	public boolean removePermission(@Nonnull String taskGroup, @Nonnull String permission) {
		return permissible.removePermission(taskGroup, permission);
	}

}
