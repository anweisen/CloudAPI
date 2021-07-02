package net.anweisen.cloudapi.driver.exceptions;

import net.anweisen.cloudapi.driver.cloud.SupportFlag;
import net.anweisen.cloudapi.driver.utils.CallerNameResolver;

import javax.annotation.Nullable;

/**
 * This exception is thrown when a feature is accessed which is not supported by this cloud.
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class UnsupportedCloudFeatureException extends UnsupportedOperationException {

	private final SupportFlag flag;

	public UnsupportedCloudFeatureException(int skipCallers, @Nullable SupportFlag flag) {
		super(CallerNameResolver.resolve(skipCallers) + (flag != null ? " -> SupportFlag." + flag : ""));
		this.flag = null;
	}

	public UnsupportedCloudFeatureException(int skipCallers) {
		this(skipCallers + 1, null);
	}

	public UnsupportedCloudFeatureException(@Nullable SupportFlag flag) {
		this(1, flag);
	}

	public UnsupportedCloudFeatureException() {
		this(1);
	}

	public SupportFlag getFlag() {
		return flag;
	}

}
