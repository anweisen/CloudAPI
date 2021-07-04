package net.anweisen.cloudapi.driver.event.player;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.driver.player.CloudPlayer;
import net.anweisen.cloudapi.driver.player.PlayerManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class PlayerEvent implements Event {

	private final UUID uniqueId;
	private final String name;

	public PlayerEvent(@Nonnull UUID uniqueId, @Nonnull String name) {
		this.uniqueId = uniqueId;
		this.name = name;
	}

	/**
	 * @return the uuid of the player
	 */
	@Nonnull
	public UUID getUniqueId() {
		return uniqueId;
	}

	/**
	 * @return the name of the player
	 */
	@Nonnull
	public String getName() {
		return name;
	}

	/**
	 * Returns the online player object or {@code null} if the player is not cached (no longer / not yet) .
	 *
	 * @return the player object or {@code null}
	 */
	@Nullable
	public CloudPlayer getPlayer() {
		return getManager().getOnlinePlayer(uniqueId);
	}

	@Nonnull
	public PlayerManager getManager() {
		return getDriver().getPlayerManager();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + getName() + ":" + getUniqueId() + "]";
	}
}
