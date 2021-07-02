package net.anweisen.cloudapi.driver.exceptions;

import net.anweisen.cloudapi.driver.utils.CallerNameResolver;

import javax.annotation.Nullable;

/**
 * This exception is thrown when a feature is access which is not ready to use yet.
 * (may require some plugins/modules to be loaded or connections to be setup)
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class UnavailableCloudFeatureException extends IllegalStateException {

	public UnavailableCloudFeatureException() {
		super(CallerNameResolver.resolve(0));
	}

	public UnavailableCloudFeatureException(@Nullable String state) {
		super(CallerNameResolver.resolve(0) + (state == null ? "" : " -> "  + state));
	}
}
