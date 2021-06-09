package net.anweisen.cloudapi.driver.player;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface CloudOfflinePlayer {

	@Nonnull
	UUID getUniqueId();

	/**
	 * @return the XBoxId from the offlinePlayer if the player has to connect from the Minecraft Bedrock Edition
	 */
	String getXBoxId();

	@Nonnull
	String getName();

	@Nonnull
	Document getProperties();

	long getFirstLoginTimeMillis();

	long getLastLoginTimeMillis();

	default void update() {
		CloudDriver.getInstance().getPlayerManager().updateOfflinePlayer(this);
	}

}
