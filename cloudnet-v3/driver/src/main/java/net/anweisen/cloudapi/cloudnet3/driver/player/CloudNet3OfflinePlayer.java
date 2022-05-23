package net.anweisen.cloudapi.cloudnet3.driver.player;

import de.dytanic.cloudnet.ext.bridge.player.ICloudOfflinePlayer;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.player.CloudOfflinePlayer;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3OfflinePlayer implements CloudOfflinePlayer {

	public final ICloudOfflinePlayer player;

	public CloudNet3OfflinePlayer(@Nonnull ICloudOfflinePlayer player) {
		this.player = player;
	}

	@Nonnull
	@Override
	public UUID getUniqueId() {
		return player.getUniqueId();
	}

	@Override
	public String getXBoxId() {
		return player.getXBoxId();
	}

	@Nonnull
	@Override
	public String getName() {
		return player.getName();
	}

	@Nonnull
	@Override
	public Document getProperties() {
		return Mapping.mapDocument(player.getProperties());
	}

	@Override
	public long getFirstLogin() {
		return player.getFirstLoginTimeMillis();
	}

	@Override
	public long getLastLogin() {
		return player.getLastLoginTimeMillis();
	}

	@Override
	public String toString() {
		return formatString();
	}
}
