package net.anweisen.cloudapi.cloudnet3.driver.player;

import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.player.CloudPlayer;
import net.anweisen.cloudapi.driver.player.PlayerExecutor;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Player extends CloudNet3OfflinePlayer implements CloudPlayer {

	public final ICloudPlayer player;

	public CloudNet3Player(@Nonnull ICloudPlayer player) {
		super(player);
		this.player = player;
	}

	@Nonnull
	@Override
	public PlayerExecutor getExecutor() {
		return new CloudNet3PlayerExecutor(player.getPlayerExecutor());
	}

	@Nonnull
	@Override
	public ServiceInfo getConnectedService() {
		return Objects.requireNonNull(CloudDriver.getInstance().getServiceManager().getServiceById(player.getConnectedService().getUniqueId()));
	}

	@Nonnull
	@Override
	public Document getOnlineProperties() {
		return Mapping.mapDocument(player.getOnlineProperties());
	}
}
