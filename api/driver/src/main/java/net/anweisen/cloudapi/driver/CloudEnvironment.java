package net.anweisen.cloudapi.driver;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public enum CloudEnvironment {

	/**
	 * The driver is running as a cloud wrapper instance.
	 * This means that the driver is running on a cloud service instance like a minecraft server.
	 *
	 * The driver will be a {@code CloudWrapper} instance.
	 */
	WRAPPER,

	/**
	 * The driver is running on the cloud application directly.
	 * If the cloud uses a master/wrapper, master/salve system, it is not specified if this is the master or a wrapper/slave.
	 *
	 * The driver will be a {@code CloudNode} instance.
	 */
	NODE

}
