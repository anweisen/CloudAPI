package net.anweisen.cloudapi.node;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.CloudEnvironment;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.cloudapi.node.module.ModuleManager;

import javax.annotation.Nonnull;

/**
 * The node is a {@link CloudDriver} instance which is running in the cloud application.
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see CloudDriver
 */
public interface CloudNode extends CloudDriver {

	@Nonnull
	@Override
	default CloudEnvironment getEnvironment() {
		return CloudEnvironment.NODE;
	}

	@Nonnull
	@Override
	default NetworkComponent getComponent() {
		return getNode();
	}

	@Nonnull
	ModuleManager getModuleManager();

	static CloudNode getInstance() {
		return (CloudNode) CloudDriver.getInstance();
	}

}
