package net.anweisen.cloudapi.cloudnet3.driver.player;

import net.anweisen.cloudapi.driver.player.PlayerExecutor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PlayerExecutor implements PlayerExecutor {

	private final de.dytanic.cloudnet.ext.bridge.player.executor.PlayerExecutor executor;

	public CloudNet3PlayerExecutor(@Nonnull de.dytanic.cloudnet.ext.bridge.player.executor.PlayerExecutor executor) {
		this.executor = executor;
	}

	@Nonnull
	@Override
	public UUID getPlayerUniqueId() {
		return executor.getPlayerUniqueId();
	}

	@Override
	public void connect(@Nonnull String serviceName) {
		executor.connect(serviceName);
	}

	@Override
	public void connectToFallback() {
		executor.connectToFallback();
	}

	@Override
	public void kick(@Nonnull String message) {
		executor.kick(message);
	}

	@Override
	public void sendChatMessage(@Nonnull String message, @Nullable String permission) {
		executor.sendChatMessage(message, permission);
	}

	@Override
	public void sendPluginMessage(@Nonnull String tag, @Nonnull byte[] data) {
		executor.sendPluginMessage(tag, data);
	}

	@Override
	public void dispatchProxyCommand(@Nonnull String command) {
		executor.dispatchProxyCommand(command);
	}
}
