package net.anweisen.cloudapi.driver.exceptions;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.cloud.SupportFlag;
import net.anweisen.cloudapi.driver.utils.CallerNameResolver;
import net.anweisen.utilities.common.misc.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * This exception is thrown when a feature is accessed which is not supported by this cloud.
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class UnsupportedCloudFeatureException extends UnsupportedOperationException {

	private final SupportFlag[] flags;

	public UnsupportedCloudFeatureException(int skipCallers, @Nonnull SupportFlag... flags) {
		super(
			CallerNameResolver.resolve(skipCallers) +
			(flags.length > 0 ? " -> " + StringUtils.getIterableAsString(Arrays.asList(flags), " ", flag -> "SupportFlag." + flag) : "") +
			(CloudDriver.getInstance() != null ? ": " + CloudDriver.getInstance().getCloudImplementation() : "")
		);
		this.flags = flags;
	}

	public UnsupportedCloudFeatureException(int skipCallers) {
		this(skipCallers + 1, new SupportFlag[0]);
	}

	public UnsupportedCloudFeatureException(@Nonnull SupportFlag... flags) {
		this(1, flags);
	}

	public UnsupportedCloudFeatureException() {
		this(1);
	}

	@Nonnull
	public SupportFlag[] getFlags() {
		return flags;
	}
}
