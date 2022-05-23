package net.anweisen.cloudapi.simplecloud.driver.node;

import eu.thesimplecloud.api.wrapper.IWrapperInfo;
import net.anweisen.cloudapi.driver.node.NodeInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SimpleCloudNodeInfo implements NodeInfo {

	private final IWrapperInfo wrapper;

	public SimpleCloudNodeInfo(@Nonnull IWrapperInfo wrapper) {
		this.wrapper = wrapper;
	}

	@Nonnull
	@Override
	public String getName() {
		return wrapper.getName();
	}

	@Nonnull
	@Override
	public String getHost() {
		return wrapper.getHost();
	}

	@Override
	public String toString() {
		return formatString();
	}
}
