package net.anweisen.cloudapi.driver.player;

import net.anweisen.utilities.commons.config.Document;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface OfflineCloudPlayer {

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

}
