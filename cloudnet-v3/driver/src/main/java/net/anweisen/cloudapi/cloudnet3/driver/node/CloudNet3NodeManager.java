package net.anweisen.cloudapi.cloudnet3.driver.node;

import de.dytanic.cloudnet.driver.network.cluster.NetworkClusterNode;
import de.dytanic.cloudnet.driver.provider.NodeInfoProvider;
import net.anweisen.cloudapi.driver.node.NodeInfo;
import net.anweisen.cloudapi.driver.node.NodeManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3NodeManager implements NodeManager {

	private final NodeInfoProvider provider;

	public CloudNet3NodeManager(@Nonnull NodeInfoProvider provider) {
		this.provider = provider;
	}

	@Nonnull
	@Override
	public List<NodeInfo> getNodes() {
		return Arrays.stream(provider.getNodes()).map(CloudNet3NodeInfo::new).collect(Collectors.toList());
	}

	@Nullable
	@Override
	public NodeInfo getNode(@Nonnull String nodeName) {
		NetworkClusterNode node = provider.getNode(nodeName);
		return node == null ? null : new CloudNet3NodeInfo(node);
	}

}
