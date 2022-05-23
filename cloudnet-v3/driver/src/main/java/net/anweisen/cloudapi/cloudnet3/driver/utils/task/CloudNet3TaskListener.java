package net.anweisen.cloudapi.cloudnet3.driver.utils.task;

import de.dytanic.cloudnet.common.concurrent.ITask;
import de.dytanic.cloudnet.common.concurrent.ITaskListener;
import net.anweisen.utilities.common.concurrent.task.Task;
import net.anweisen.utilities.common.concurrent.task.TaskListener;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3TaskListener<T> implements ITaskListener<T> {

	private final Task<T> task;
	private final TaskListener<T> listener;

	public CloudNet3TaskListener(@Nonnull Task<T> task, @Nonnull TaskListener<T> listener) {
		this.task = task;
		this.listener = listener;
	}

	@Override
	public void onCancelled(ITask<T> task) {
		listener.onCancelled(this.task);
	}

	@Override
	public void onComplete(ITask<T> task, T t) {
		listener.onComplete(this.task, t);
	}

	@Override
	public void onFailure(ITask<T> task, Throwable ex) {
		listener.onFailure(this.task, ex);
	}

}
