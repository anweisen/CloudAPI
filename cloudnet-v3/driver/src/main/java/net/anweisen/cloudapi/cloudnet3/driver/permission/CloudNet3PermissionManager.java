package net.anweisen.cloudapi.cloudnet3.driver.permission;

import de.dytanic.cloudnet.driver.permission.IPermissionManagement;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.permission.PermissionGroup;
import net.anweisen.cloudapi.driver.permission.PermissionManager;
import net.anweisen.cloudapi.driver.permission.PermissionUser;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping.mapTask;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PermissionManager implements PermissionManager {

	private final IPermissionManagement management;

	public CloudNet3PermissionManager(@Nonnull IPermissionManagement management) {
		this.management = management;
	}

	@Override
	public void reload() {
		management.reload();
	}

	@Nonnull
	@Override
	public Collection<PermissionUser> getUsers() {
		return Mapping.mapUsers(management.getUsers());
	}

	@Nonnull
	@Override
	public Task<Collection<PermissionUser>> getUsersAsync() {
		return mapTask(management.getUsersAsync().map(Mapping::mapUsers));
	}

	@Nonnull
	@Override
	public Collection<PermissionUser> getUsersWithGroup(@Nonnull String group) {
		return Mapping.mapUsers(management.getUsersByGroup(group));
	}

	@Nonnull
	@Override
	public Task<Collection<PermissionUser>> getUsersWithGroupAsync(@Nonnull String group) {
		return mapTask(management.getUsersByGroupAsync(group).map(Mapping::mapUsers));
	}

	@Nonnull
	@Override
	public List<PermissionUser> getUsers(@Nonnull String name) {
		return Mapping.mapUsers(management.getUsers(name));
	}

	@Nonnull
	@Override
	public Task<List<PermissionUser>> getUsersAsync(@Nonnull String name) {
		return mapTask(management.getUsersAsync(name).map(Mapping::mapUsers));
	}

	@Nullable
	@Override
	public PermissionUser getFirstUser(@Nonnull String name) {
		return Mapping.mapUser(management.getFirstUser(name));
	}

	@Nonnull
	@Override
	public Task<PermissionUser> getFirstUserAsync(@Nonnull String name) {
		return mapTask(management.getFirstUserAsync(name).map(Mapping::mapUser));
	}

	@Nullable
	@Override
	public PermissionUser getUser(@Nonnull UUID uniqueId) {
		return Mapping.mapUser(management.getUser(uniqueId));
	}

	@Nonnull
	@Override
	public Task<PermissionUser> getUserAsync(@Nonnull UUID uniqueId) {
		return mapTask(management.getUserAsync(uniqueId).map(Mapping::mapUser));
	}

	@Override
	public void updateUser(@Nonnull PermissionUser user) {
		management.updateUser(Mapping.extractUser(user));
	}

	@Nonnull
	@Override
	public Task<Void> updateUserAsync(@Nonnull PermissionUser user) {
		return mapTask(management.updateUserAsync(Mapping.extractUser(user)));
	}

	@Override
	public void deleteUser(@Nonnull String name) {
		management.deleteUser(name);
	}

	@Nonnull
	@Override
	public Task<Void> deleteUserAsync(@Nonnull String name) {
		return mapTask(management.deleteUserAsync(name).map(v -> null));
	}

	@Override
	public void deleteUser(@Nonnull PermissionUser user) {
		management.deleteUser(Mapping.extractUser(user));
	}

	@Nonnull
	@Override
	public Task<Void> deleteUserAsync(@Nonnull PermissionUser user) {
		return mapTask(management.deleteUserAsync(Mapping.extractUser(user)).map(v -> null));
	}

	@Nonnull
	@Override
	public Collection<PermissionGroup> getUserGroups(@Nonnull PermissionUser user) {
		return Mapping.mapGroups(management.getGroups(Mapping.extractUser(user)));
	}

	@Override
	public PermissionGroup getHighestGroup(@Nonnull PermissionUser user) {
		return Mapping.mapGroup(management.getHighestPermissionGroup(Mapping.extractUser(user)));
	}

	@Nonnull
	@Override
	public Collection<PermissionGroup> getGroups() {
		return Mapping.mapGroups(management.getGroups());
	}

	@Nonnull
	@Override
	public Task<Collection<PermissionGroup>> getGroupsAsync() {
		return mapTask(management.getGroupsAsync().map(Mapping::mapGroups));
	}

	@Override
	public PermissionGroup getDefaultGroup() {
		return Mapping.mapGroup(management.getDefaultPermissionGroup());
	}

	@Nonnull
	@Override
	public Task<PermissionGroup> getDefaultGroupAsync() {
		return mapTask(management.getDefaultPermissionGroupAsync().map(Mapping::mapGroup));
	}

	@Override
	public void updateGroup(@Nonnull PermissionGroup group) {
		management.updateGroup(Mapping.extractGroup(group));
	}

	@Nonnull
	@Override
	public Task<Void> updateGroupAsync(@Nonnull PermissionGroup group) {
		return mapTask(management.updateGroupAsync(Mapping.extractGroup(group)));
	}

	@Override
	public void deleteGroup(@Nonnull String name) {
		management.deleteGroup(name);
	}

	@Nonnull
	@Override
	public Task<Void> deleteGroupAsync(@Nonnull String name) {
		return mapTask(management.deleteGroupAsync(name));
	}

	@Override
	public void deleteGroup(@Nonnull PermissionGroup group) {
		management.deleteGroup(Mapping.extractGroup(group));
	}

	@Nonnull
	@Override
	public Task<Void> deleteGroupAsync(@Nonnull PermissionGroup group) {
		return mapTask(management.deleteGroupAsync(Mapping.extractGroup(group)));
	}

}
