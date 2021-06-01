package net.anweisen.cloudapi.driver.utils.exceptions;

import net.anweisen.cloudapi.driver.support.SupportFlag;
import net.anweisen.utilities.commons.misc.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class UnsupportedCloudFeatureException extends UnsupportedOperationException {

	private final SupportFlag flag;

	public UnsupportedCloudFeatureException() {
		super(getCaller());
		this.flag = null;
	}

	public UnsupportedCloudFeatureException(@Nullable SupportFlag flag) {
		super(getCaller() + (flag != null ? " SupportFlag." + flag : ""));
		this.flag = flag;
	}

	@Nonnull
	private static String getCaller() {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		StackTraceElement element = trace[3];

		String className = StringUtils.getAfterLastIndex(element.getClassName(), ".");
		return className + "." + element.getMethodName();
	}

	@Nullable
	public SupportFlag getFlag() {
		return flag;
	}

}
