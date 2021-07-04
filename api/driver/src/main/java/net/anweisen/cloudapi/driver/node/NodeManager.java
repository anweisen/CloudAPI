package net.anweisen.cloudapi.driver.node;

import net.anweisen.cloudapi.driver.CloudDriver;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see CloudDriver#getNodeManager()
 */
public interface NodeManager {

	@Nonnull
	List<NodeInfo> getNodes();

	@Nullable
	NodeInfo getNode(@Nonnull String nodeName);

}
