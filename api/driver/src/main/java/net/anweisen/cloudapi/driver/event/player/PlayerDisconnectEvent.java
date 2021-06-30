package net.anweisen.cloudapi.driver.event.player;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class PlayerDisconnectEvent extends PlayerEvent {

	public PlayerDisconnectEvent(@Nonnull UUID uniqueId, @Nonnull String name) {
		super(uniqueId, name);
	}
}
