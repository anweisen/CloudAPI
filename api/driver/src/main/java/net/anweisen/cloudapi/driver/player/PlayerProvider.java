package net.anweisen.cloudapi.driver.player;

import net.anweisen.cloudapi.driver.utils.task.Task;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PlayerProvider {

	@Nonnull
	Collection<CloudPlayer> asPlayers();

	@Nonnull
	Task<Collection<CloudPlayer>> asPlayersAsync();

	@Nonnull
	Collection<String> asNames();

	@Nonnull
	Task<Collection<String>> asNamesAsync();

	@Nonnull
	Collection<UUID> asUUIDs();

	@Nonnull
	Task<Collection<UUID>> asUUIDsAsync();

	int count();

	@Nonnull
	Task<Integer> countAsync();

}
