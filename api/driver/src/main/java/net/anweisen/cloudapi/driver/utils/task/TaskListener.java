package net.anweisen.cloudapi.driver.utils.task;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface TaskListener<T> {

	default void onComplete(@Nonnull Task<T> task, T value) {
	}

	default void onCancelled(@Nonnull Task<T> task) {
	}

	default void onFailure(@Nonnull Task<T> task, @Nonnull Throwable ex) {
	}

}
