package net.anweisen.cloudapi.cloudnet3.driver.utils;

import de.dytanic.cloudnet.common.concurrent.ITask;
import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.player.ICloudOfflinePlayer;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import net.anweisen.cloudapi.cloudnet3.driver.permission.CloudNet3PermissionGroup;
import net.anweisen.cloudapi.cloudnet3.driver.permission.CloudNet3PermissionUser;
import net.anweisen.cloudapi.cloudnet3.driver.player.CloudNet3OfflinePlayer;
import net.anweisen.cloudapi.cloudnet3.driver.player.CloudNet3Player;
import net.anweisen.cloudapi.cloudnet3.driver.player.CloudNet3PlayerProvider;
import net.anweisen.cloudapi.cloudnet3.driver.service.config.CloudNet3ServiceTask;
import net.anweisen.cloudapi.cloudnet3.driver.service.specific.CloudNet3ServiceInfo;
import net.anweisen.cloudapi.cloudnet3.driver.utils.task.CloudNet3Task;
import net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper.CloudNet3Document;
import net.anweisen.cloudapi.driver.permission.PermissionGroup;
import net.anweisen.cloudapi.driver.permission.PermissionUser;
import net.anweisen.cloudapi.driver.player.CloudOfflinePlayer;
import net.anweisen.cloudapi.driver.player.CloudPlayer;
import net.anweisen.cloudapi.driver.player.PlayerProvider;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.common.concurrent.task.Task;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public final class Mapping {

	private Mapping() {}

	@Nonnull
	public static <V> Task<V> mapTask(@Nonnull ITask<V> task) {
		return new CloudNet3Task<>(task);
	}

	public static ServiceInfo mapServiceInfo(@Nullable ServiceInfoSnapshot info) {
		return info == null ? null : new CloudNet3ServiceInfo(info);
	}

	@Nonnull
	public static List<ServiceInfo> mapServiceInfos(@Nonnull Collection<? extends ServiceInfoSnapshot> infos) {
		return map(infos, Mapping::mapServiceInfo);
	}

	@Nonnull
	public static de.dytanic.cloudnet.driver.service.ServiceTask extractServiceTask(@Nonnull ServiceTask task) {
		return ((CloudNet3ServiceTask)task).task;
	}

	public static ServiceTask mapServiceTask(@Nullable de.dytanic.cloudnet.driver.service.ServiceTask task) {
		return task == null ? null : new CloudNet3ServiceTask(task);
	}

	@Nonnull
	public static List<ServiceTask> mapServiceTasks(@Nonnull Collection<? extends de.dytanic.cloudnet.driver.service.ServiceTask> tasks) {
		return map(tasks, Mapping::mapServiceTask);
	}

	public static CloudPlayer mapPlayer(@Nullable ICloudPlayer player) {
		return map(player, Mapping::mapPlayer);
	}

	@Nonnull
	public static List<CloudPlayer> mapPlayers(@Nonnull Collection<? extends ICloudPlayer> players) {
		return map(players, Mapping::mapPlayer);
	}

	@Nonnull
	public static ICloudPlayer extractPlayer(@Nonnull CloudPlayer player) {
		return ((CloudNet3Player)player).player;
	}

	public static CloudOfflinePlayer mapOfflinePlayer(@Nullable ICloudOfflinePlayer player) {
		return map(player, Mapping::mapOfflinePlayer);
	}

	@Nonnull
	public static List<CloudOfflinePlayer> mapOfflinePlayers(@Nonnull Collection<? extends ICloudOfflinePlayer> players) {
		return map(players, Mapping::mapOfflinePlayer);
	}

	@Nonnull
	public static PlayerProvider mapProvider(@Nonnull de.dytanic.cloudnet.ext.bridge.player.PlayerProvider provider) {
		return new CloudNet3PlayerProvider(provider);
	}

	@Nonnull
	public static ICloudOfflinePlayer extractPlayer(@Nonnull CloudOfflinePlayer player) {
		return ((CloudNet3OfflinePlayer)player).player;
	}

	public static PermissionUser mapUser(@Nullable IPermissionUser user) {
		return map(user, CloudNet3PermissionUser::new);
	}

	@Nonnull
	public static List<PermissionUser> mapUsers(@Nonnull Collection<? extends IPermissionUser> users) {
		return map(users, Mapping::mapUser);
	}

	@Nonnull
	public static IPermissionUser extractUser(@Nonnull PermissionUser user) {
		return ((CloudNet3PermissionUser)user).user;
	}

	@Nonnull
	public static List<PermissionGroup> mapGroups(@Nonnull Collection<? extends IPermissionGroup> groups) {
		return map(groups, Mapping::mapGroup);
	}

	public static PermissionGroup mapGroup(@Nullable IPermissionGroup group) {
		return map(group, CloudNet3PermissionGroup::new);
	}

	@Nonnull
	public static IPermissionGroup extractGroup(@Nonnull PermissionGroup group) {
		return ((CloudNet3PermissionGroup)group).group;
	}

	@Nonnull
	public static Document mapDocument(@Nonnull JsonDocument document) {
		return new CloudNet3Document(document);
	}

	@Nonnull
	protected static <I, O> List<O> map(@Nonnull Collection<? extends I> collection, @Nonnull Function<? super I, ? extends O> mapper) {
		return collection.stream().map(mapper).collect(Collectors.toList());
	}

	protected static <I, O> O map(@Nullable I value, @Nonnull Function<? super I, ? extends O> mapper) {
		return value == null ? null : mapper.apply(value);
	}
}
