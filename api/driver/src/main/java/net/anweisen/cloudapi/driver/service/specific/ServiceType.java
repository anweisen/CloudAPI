package net.anweisen.cloudapi.driver.service.specific;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public enum ServiceType {

	JAVA_PROXY,
	JAVA_SERVER,

	BEDROCK_PROXY,
	BEDROCK_SERVER,

	UNKNOWN;

	public boolean isMinecraftJava() {
		return this == JAVA_PROXY || this == JAVA_SERVER;
	}

	public boolean isMinecraftBedrock() {
		return this == BEDROCK_PROXY || this == BEDROCK_SERVER;
	}

	public boolean isMinecraftServer() {
		return this == JAVA_SERVER || this == BEDROCK_SERVER;
	}

	public boolean isMinecraftProxy() {
		return this == JAVA_PROXY || this == BEDROCK_PROXY;
	}

	public boolean isMinecraftJavaServer() {
		return this == JAVA_SERVER;
	}

	public boolean isMinecraftJavaProxy() {
		return this == JAVA_PROXY;
	}

	public boolean isMinecraftBedrockServer() {
		return this == BEDROCK_SERVER;
	}

	public boolean isMinecraftBedrockProxy() {
		return this == BEDROCK_PROXY;
	}
}
