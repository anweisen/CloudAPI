package net.anweisen.cloudapi.driver.player;

import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PlayerManager {

	int getOnlineCount();

	@Nonnull
	Task<Integer> getOnlineCountAsync();

	long getRegisteredCount();
	
	@Nonnull
	Task<Long> getRegisteredCountAsync();

	@Nonnull
	PlayerProvider provideOnlinePlayers();

	@Nonnull
	PlayerProvider provideTaskPlayers(@Nonnull String taskName);

	@Nonnull
	Collection<CloudPlayer> getOnlinePlayers();

	@Nonnull
	Task<Collection<CloudPlayer>> getOnlinePlayersAsync();

	@Nullable
	CloudPlayer getOnlinePlayer(@Nonnull UUID uniqueId);

	@Nonnull
	Task<CloudPlayer> getOnlinePlayerAsync(@Nonnull UUID uniqueId);

	@Nullable
	CloudPlayer getFirstOnlinePlayer(@Nonnull String name);

	@Nonnull
	Task<CloudPlayer> getFirstOnlinePlayerAsync(@Nonnull String name);

	@Nullable
	CloudOfflinePlayer getOfflinePlayer(@Nonnull UUID uniqueId);

	@Nonnull
	Task<CloudOfflinePlayer> getOfflinePlayerAsync(@Nonnull UUID uniqueId);

	@Nullable
	CloudOfflinePlayer getFirstOfflinePlayer(@Nonnull String name);

	@Nonnull
	Task<CloudOfflinePlayer> getFirstOfflinePlayerAsync(@Nonnull String name);

	void updateOfflinePlayer(@Nonnull CloudOfflinePlayer player);

	void updateOnlinePlayer(@Nonnull CloudPlayer player);

}
