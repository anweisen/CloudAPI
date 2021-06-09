package net.anweisen.cloudapi;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.CloudEnvironment;
import net.anweisen.cloudapi.driver.component.NetworkComponent;
import net.anweisen.utilities.commons.logging.ILogger;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface CloudNode extends CloudDriver {

	@Nonnull
	@Override
	default ILogger getLogger() { // TODO
		return ILogger.forThisClass();
	}

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

}
