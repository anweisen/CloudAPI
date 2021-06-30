package net.anweisen.cloudapi.driver.event;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface Cancelable {

	boolean isCancelled();

	void setCancelled(boolean cancelled);

}