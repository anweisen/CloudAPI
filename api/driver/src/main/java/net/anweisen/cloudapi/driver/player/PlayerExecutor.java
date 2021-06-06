package net.anweisen.cloudapi.driver.player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PlayerExecutor {

	/**
	 * Gets the uniqueId of the player this player executor handles.
	 *
	 * @return the UUID of the player
	 */
	@Nonnull
	UUID getPlayerUniqueId();

	/**
	 * Connects an online player to a specific service.
	 *
	 * @param serviceName the name of the service the player should be sent to
	 */
	void connect(@Nonnull String serviceName);

	/**
	 * Connects the player to a fallback service like in the /hub command
	 */
	void connectToFallback();

	/**
	 * Kicks an online player from the network with a specific reason.
	 *
	 * @param message the reason for the kick which will be displayed in the players client
	 */
	void kick(@Nonnull String message);

	/**
	 * Sends a plugin message to a specific online player.
	 *
	 * @param message the message to be sent to the player
	 */
	void sendChatMessage(@Nonnull String message);

	/**
	 * Sends a plugin message to a specific online player only if the player has the given permission.
	 * If the permission is null, the message will be sent.
	 *
	 * @param message    the message to be sent to the player
	 * @param permission the permission which will be checked before the message is sent to the player
	 */
	void sendChatMessage(@Nonnull String message, @Nullable String permission);

	/**
	 * Sends a message to a specific online player. The tag has to be registered in the proxy.
	 *
	 * @param tag  the tag of the plugin message
	 * @param data the data of the plugin message
	 */
	void sendPluginMessage(@Nonnull String tag, @Nonnull byte[] data);

	/**
	 * Dispatches a command on the proxy the player is connected with as the player.
	 *
	 * @param command the command to dispatch
	 */
	void dispatchProxyCommand(@Nonnull String command);

}
