package net.anweisen.cloudapi.driver.utils.task;

import net.anweisen.utilities.commons.function.ExceptionallyFunction;

import javax.annotation.Nonnull;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface Task<V> extends Future<V>, Callable<V> {

	@Nonnull
	default Task<V> onComplete(@Nonnull Consumer<? super V> action) {
		return onComplete((task, value) -> action.accept(value));
	}

	@Nonnull
	default Task<V> onComplete(@Nonnull BiConsumer<? super Task<V>, ? super V> action) {
		return addListener(new TaskListener<V>() {
			@Override
			public void onComplete(@Nonnull Task<V> task, V value) {
				action.accept(task, value);
			}
		});
	}

	@Nonnull
	default Task<V> onFailure(@Nonnull Consumer<? super Throwable> action) {
		return onFailure((task, ex) -> action.accept(ex));
	}

	@Nonnull
	default Task<V> onFailure(@Nonnull BiConsumer<? super Task<V>, ? super Throwable> action) {
		return addListener(new TaskListener<V>() {
			@Override
			public void onFailure(@Nonnull Task<V> task, @Nonnull Throwable ex) {
				action.accept(task, ex);
			}
		});
	}

	@Nonnull
	default Task<V> onCancelled(@Nonnull Runnable action) {
		return onCancelled(task -> action.run());
	}

	@Nonnull
	default Task<V> onCancelled(@Nonnull Consumer<? super Task<V>> action) {
		return addListener(new TaskListener<V>() {
			@Override
			public void onCancelled(@Nonnull Task<V> task) {
				action.accept(task);
			}
		});
	}

	@Nonnull
	default Task<V> fireExceptionOnFailure() {
		return onFailure(ex -> ex.printStackTrace());
	}

	@Nonnull
	default Task<V> addListeners(@Nonnull TaskListener<? super V>... listeners) {
		for (TaskListener<? super V> listener : listeners)
			addListener(listener);

		return this;
	}

	@Nonnull
	Task<V> addListener(@Nonnull TaskListener<? super V> listener);

	@Nonnull
	<R> Task<R> map(@Nonnull Function<? super V, ? extends R> mapper);

	@Nonnull
	default <R> Task<R> mapExceptionally(@Nonnull ExceptionallyFunction<? super V, ? extends R> mapper) {
		return map(mapper);
	}

}
