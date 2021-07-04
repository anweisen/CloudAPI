package net.anweisen.cloudapi.driver.service.specific;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see ServiceInfo#getType()
 */
public enum ServiceType {

	/**
	 * The type of the service is not known to the api
	 */
	OTHER,

	/**
	 * The service is proxy. Might be bedrock or java.
	 */
	PROXY,

	/**
	 * The service is a minecraft server. Might be bedrock or java.
	 */
	SERVER

}
