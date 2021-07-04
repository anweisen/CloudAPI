package net.anweisen.cloudapi.driver.service.specific;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see ServiceInfo#getState()
 */
public enum ServiceState {

	/**
	 * This state will only be set for a short time, in some clouds unused.
	 * This state is a transition state between being created and {@link #PREPARED}
	 */
	DEFINED,

	/**
	 * This is the state directly after {@link #DEFINED} or creation, depends on the cloud.
	 * It will be prepared until it is started.
	 */
	PREPARED,

	/**
	 * The service is currently running.
	 * This state is set after {@link #PREPARED} when the service is started.
	 */
	RUNNING,

	/**
	 * This state will only be set for a short time, in some clouds unused.
	 * This is a transition state between {@link #RUNNING} and {@link #DELETED} / {@link #PREPARED} (if static service)
	 */
	STOPPED,

	/**
	 * This is the state after {@link #STOPPED}.
	 * When this state is set, the service is no longer registered in the cloud.
	 */
	DELETED

}
