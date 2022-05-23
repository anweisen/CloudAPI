package net.anweisen.cloudapi.cloudnet3.driver.utils.task;

import de.dytanic.cloudnet.common.concurrent.ITask;
import net.anweisen.cloudapi.driver.exceptions.UnsupportedCloudFeatureException;
import net.anweisen.utilities.common.concurrent.task.Task;
import net.anweisen.utilities.common.concurrent.task.TaskListener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Task<V> implements Task<V> {

	@Nonnull
	public static <V> Task<V> mapTask(@Nonnull ITask<V> task) {
		return new CloudNet3Task<>(task);
	}

	private final ITask<V> task;

	public CloudNet3Task(@Nonnull ITask<V> task) {
		this.task = task;
	}

	@Nonnull
	@Override
	public Task<V> addListener(@Nonnull TaskListener<V> listener) {
		task.addListener(new CloudNet3TaskListener<>(this, listener));
		return this;
	}

	@Nonnull
	@Override
	public <R> Task<R> map(@Nullable Function<? super V, ? extends R> mapper) {
		return new CloudNet3Task<>(task.map((Function<V, R>) mapper));
	}

	@Override
	public V call() throws Exception {
		return task.call();
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return task.cancel(mayInterruptIfRunning);
	}

	@Override
	public boolean isCancelled() {
		return task.isCancelled();
	}

	@Override
	public boolean isDone() {
		return task.isDone();
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return task.get();
	}

	@Override
	public V get(long timeout, @Nonnull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return task.get(timeout, unit);
	}

	@Override
	public V get(long timeout, @Nonnull TimeUnit unit, V def) {
		return task.get(timeout, unit, def);
	}

	@Nonnull
	@Override
	public Task<V> clearListeners() {
		task.clearListeners();
		return this;
	}

	@Nonnull
	@Override
	public CompletionStage<V> stage() {
		throw new UnsupportedCloudFeatureException();
	}
}
