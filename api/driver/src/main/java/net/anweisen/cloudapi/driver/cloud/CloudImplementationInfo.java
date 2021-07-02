package net.anweisen.cloudapi.driver.cloud;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public final class CloudImplementationInfo {

	private static final String apiVersion = "1.0";
	private final String name;
	private final String nativeCloudVersion;

	public CloudImplementationInfo(@Nonnull String name, @Nonnull String nativeCloudVersion) {
		this.name = name;
		this.nativeCloudVersion = nativeCloudVersion;
	}

	@Nonnull
	public String getName() {
		return name;
	}

	@Nonnull
	public String getNativeCloudVersion() {
		return nativeCloudVersion;
	}

	@Nonnull
	public String getApiVersion() {
		return apiVersion;
	}

	@Override
	public String toString() {
		return "CloudImplementation[" + getName() + " native v" + getNativeCloudVersion() + ", API v" + getApiVersion() + "]";
	}
}
