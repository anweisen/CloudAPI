package net.anweisen.cloudapi.driver.player;

import net.anweisen.utilities.commons.config.Document;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface CloudPlayer extends OfflineCloudPlayer {

	@Nonnull
	PlayerExecutor getExecutor();

	@Nonnull
	Document getOnlineProperties();

}
