package net.anweisen.cloudapi.driver.player;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.common.config.Document;

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

	@Nonnull
	ServiceInfo getConnectedService();

	@Override
	default void update() {
		CloudDriver.getInstance().getPlayerManager().updateOnlinePlayer(this);
	}

}
