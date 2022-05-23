package net.anweisen.cloudapi.cloudnet3.driver.node;

import de.dytanic.cloudnet.driver.network.cluster.NetworkClusterNode;
import net.anweisen.cloudapi.driver.node.NodeInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3NodeInfo implements NodeInfo {

	private final NetworkClusterNode node;

	public CloudNet3NodeInfo(@Nonnull NetworkClusterNode node) {
		this.node = node;
	}

	@Nonnull
	@Override
	public String getName() {
		return node.getUniqueId();
	}

	@Nonnull
	@Override
	public String getHost() {
		return node.getListeners()[0].getHost();
	}

	@Override
	public String toString() {
		return formatString();
	}
}
