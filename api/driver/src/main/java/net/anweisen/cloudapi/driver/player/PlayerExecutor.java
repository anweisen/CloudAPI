package net.anweisen.cloudapi.driver.player;

import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PlayerExecutor {

	/**
	 * Gets the uniqueId of the player this executor handles.
	 *
	 * @return the UUID of the player
	 */
	@Nonnull
	UUID getPlayerUniqueId();

	/**
	 * Connect the player to the given service.
	 *
	 * @param serviceName the name of the service the player should be sent to
	 */
	void connect(@Nonnull String serviceName);

	/**
	 * Connect the player to the given service.
	 *
	 * @param service the service the player should be sent to
	 */
	default void connect(@Nonnull ServiceInfo service) {
		connect(service.getName());
	}

	/**
	 * Connects the player to a fallback service (lobby) like in the /hub command.
	 * This will do nothing if the player is already on a fallback service.
	 */
	void connectToFallback();

	/**
	 * Kicks the player from the network with a specific reason.
	 *
	 * @param message the reason for the kick which will be displayed in the players client
	 */
	void kick(@Nonnull String message);

	/**
	 * Sends a chat message to the player.
	 *
	 * @param message the message to be sent to the player
	 */
	default void sendChatMessage(@Nonnull String message) {
		sendChatMessage(message, null);
	}

	/**
	 * Sends a chat message to the player only if the player has the given permission.
	 * If the permission is {@code null}, the message will always be sent.
	 *
	 * @param message    the message to be sent to the player
	 * @param permission the permission which will be checked before the message is sent to the player
	 */
	void sendChatMessage(@Nonnull String message, @Nullable String permission);

	/**
	 * Sends a plugin message to the player. The tag has to be registered in the proxy.
	 *
	 * @param tag the tag of the plugin message
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
