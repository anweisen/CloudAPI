package net.anweisen.cloudapi.cloudnet3.driver.player;

import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.player.CloudPlayer;
import net.anweisen.cloudapi.driver.player.PlayerProvider;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PlayerProvider implements PlayerProvider {

	private final de.dytanic.cloudnet.ext.bridge.player.PlayerProvider provider;

	public CloudNet3PlayerProvider(@Nonnull de.dytanic.cloudnet.ext.bridge.player.PlayerProvider provider) {
		this.provider = provider;
	}

	@Nonnull
	@Override
	public Collection<CloudPlayer> asPlayers() {
		return Mapping.mapPlayers(provider.asPlayers());
	}

	@Nonnull
	@Override
	public Collection<String> asNames() {
		return provider.asNames();
	}

	@Nonnull
	@Override
	public Collection<UUID> asUUIDs() {
		return provider.asUUIDs();
	}

	@Override
	public int count() {
		return provider.count();
	}

	@Nonnull
	@Override
	public Task<Collection<CloudPlayer>> asPlayersAsync() {
		return Mapping.mapTask(provider.asPlayersAsync().map(Mapping::mapPlayers));
	}

	@Nonnull
	@Override
	public Task<Collection<String>> asNamesAsync() {
		return Mapping.mapTask(provider.asNamesAsync());
	}

	@Nonnull
	@Override
	public Task<Collection<UUID>> asUUIDsAsync() {
		return Mapping.mapTask(provider.asUUIDsAsync());
	}

	@Nonnull
	@Override
	public Task<Integer> countAsync() {
		return Mapping.mapTask(provider.countAsync());
	}
}
