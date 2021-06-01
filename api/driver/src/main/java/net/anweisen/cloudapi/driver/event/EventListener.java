package net.anweisen.cloudapi.driver.event;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public @interface EventListener {

	/**
	 * @return the channel to listen on, will be ignored if not supported
	 */
	@Nonnull
	String channel() default "*";

	/**
	 * @return the priority of this listener, indicates the order, will translated to the cloud's priority system, will be ignored if not supported
	 */
	@Nonnull
	EventPriority priority() default EventPriority.NORMAL;

}
