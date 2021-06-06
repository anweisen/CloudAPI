package net.anweisen.cloudapi.driver.player;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.utilities.commons.config.Document;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface CloudPlayer extends CloudOfflinePlayer {

	@Nonnull
	PlayerExecutor getExecutor();

	@Nonnull
	Document getOnlineProperties();

	@Override
	default void update() {
		CloudDriver.getInstance().getPlayerManager().updateOnlinePlayer(this);
	}

}
