package net.anweisen.cloudapi.driver.event;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {

	/**
	 * @return the channel to listen on, will be ignored if not supported
	 */
	@Nonnull
	String channel() default "*";

	/**
	 * @return whether this listener should not be called when the event was cancelled
	 *
	 * @see Cancelable#isCancelled()
	 */
	boolean ignoreCancelled() default false;

	@Nonnull
	EventPriority priority() default EventPriority.NORMAL;

}
