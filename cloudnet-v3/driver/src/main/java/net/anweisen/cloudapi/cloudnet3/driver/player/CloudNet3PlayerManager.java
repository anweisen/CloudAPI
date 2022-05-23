package net.anweisen.cloudapi.cloudnet3.driver.player;

import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.player.CloudPlayer;
import net.anweisen.cloudapi.driver.player.CloudOfflinePlayer;
import net.anweisen.cloudapi.driver.player.PlayerManager;
import net.anweisen.cloudapi.driver.player.PlayerProvider;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PlayerManager implements PlayerManager {

	private final IPlayerManager manager;

	public CloudNet3PlayerManager(@Nonnull IPlayerManager manager) {
		this.manager = manager;
	}

	@Override
	public int getOnlineCount() {
		return manager.getOnlineCount();
	}

	@Nonnull
	@Override
	public Task<Integer> getOnlineCountAsync() {
		return Mapping.mapTask(manager.getOnlineCountAsync());
	}

	@Override
	public long getRegisteredCount() {
		return manager.getRegisteredCount();
	}

	@Nonnull
	@Override
	public Task<Long> getRegisteredCountAsync() {
		return Mapping.mapTask(manager.getRegisteredCountAsync());
	}

	@Nonnull
	@Override
	public PlayerProvider provideOnlinePlayers() {
		return Mapping.mapProvider(manager.onlinePlayers());
	}

	@Nonnull
	@Override
	public PlayerProvider provideTaskPlayers(@Nonnull String taskName) {
		return Mapping.mapProvider(manager.taskOnlinePlayers(taskName));
	}

	@Nonnull
	@Override
	public Collection<CloudPlayer> getOnlinePlayers() {
		return Mapping.mapPlayers(manager.onlinePlayers().asPlayers());
	}

	@Nonnull
	@Override
	public Task<Collection<CloudPlayer>> getOnlinePlayersAsync() {
		return Mapping.mapTask(manager.onlinePlayers().asPlayersAsync().map(Mapping::mapPlayers));
	}

	@Nullable
	@Override
	public CloudPlayer getOnlinePlayer(@Nonnull UUID uniqueId) {
		return Mapping.mapPlayer(manager.getOnlinePlayer(uniqueId));
	}

	@Nonnull
	@Override
	public Task<CloudPlayer> getOnlinePlayerAsync(@Nonnull UUID uniqueId) {
		return Mapping.mapTask(manager.getOnlinePlayerAsync(uniqueId).map(Mapping::mapPlayer));
	}

	@Nullable
	@Override
	public CloudPlayer getFirstOnlinePlayer(@Nonnull String name) {
		return Mapping.mapPlayer(manager.getFirstOnlinePlayer(name));
	}

	@Nonnull
	@Override
	public Task<CloudPlayer> getFirstOnlinePlayerAsync(@Nonnull String name) {
		return Mapping.mapTask(manager.getFirstOnlinePlayerAsync(name).map(Mapping::mapPlayer));
	}

	@Nullable
	@Override
	public CloudOfflinePlayer getOfflinePlayer(@Nonnull UUID uniqueId) {
		return Mapping.mapOfflinePlayer(manager.getOfflinePlayer(uniqueId));
	}

	@Nonnull
	@Override
	public Task<CloudOfflinePlayer> getOfflinePlayerAsync(@Nonnull UUID uniqueId) {
		return Mapping.mapTask(manager.getOfflinePlayerAsync(uniqueId).map(Mapping::mapOfflinePlayer));
	}

	@Nullable
	@Override
	public CloudOfflinePlayer getFirstOfflinePlayer(@Nonnull String name) {
		return Mapping.mapOfflinePlayer(manager.getFirstOfflinePlayer(name));
	}

	@Nonnull
	@Override
	public Task<CloudOfflinePlayer> getFirstOfflinePlayerAsync(@Nonnull String name) {
		return Mapping.mapTask(manager.getFirstOfflinePlayerAsync(name).map(Mapping::mapOfflinePlayer));
	}

	@Override
	public void updateOfflinePlayer(@Nonnull CloudOfflinePlayer player) {
		manager.updateOfflinePlayer(Mapping.extractPlayer(player));
	}

	@Override
	public void updateOnlinePlayer(@Nonnull CloudPlayer player) {
		manager.updateOnlinePlayer(Mapping.extractPlayer(player));
	}
}
