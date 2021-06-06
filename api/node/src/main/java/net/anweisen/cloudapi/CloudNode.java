package net.anweisen.cloudapi;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.CloudEnvironment;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface CloudNode extends CloudDriver {

	@Nonnull
	@Override
	default CloudEnvironment getEnvironment() {
		return CloudEnvironment.NODE;
	}

}
