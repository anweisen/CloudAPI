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

	/**
	 * @return the unique identifier of the player from the Minecraft Java or Bedrock Edition
	 */
	@Nonnull
	UUID getUniqueId();

	/**
	 * @return the XBoxId of the player if the player has to connect from the Minecraft Bedrock Edition
	 */
	String getXBoxId();

	@Nonnull
	String getName();

	@Nonnull
	Document getProperties();

	/**
	 * @return the time in millis
	 */
	long getFirstLogin();

	/**
	 * @return the time in millis
	 */
	long getLastLogin();

	default void update() {
		CloudDriver.getInstance().getPlayerManager().updateOfflinePlayer(this);
	}

	@Nonnull
	default String formatString() {
		return "CloudPlayer[" + getName() + ":" + getUniqueId() + "]";
	}

}
