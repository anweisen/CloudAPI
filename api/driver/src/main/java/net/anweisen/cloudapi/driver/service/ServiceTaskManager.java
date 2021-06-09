package net.anweisen.cloudapi.driver.service;

import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see net.anweisen.cloudapi.driver.service.config.ServiceTask
 */
public interface ServiceTaskManager {

	/**
	 * Reloads all tasks, nothing if unsupported
	 */
	void reload();

	/**
	 * Reloads all tasks, nothing if unsupported
	 */
	@Nonnull
	Task<Void> reloadAsync();

	@Nonnull
	Collection<ServiceTask> getTasks();

	@Nonnull
	Task<Collection<ServiceTask>> getTasksAsync();

	@Nullable
	ServiceTask getTask(@Nonnull String name);

	@Nonnull
	Task<ServiceTask> getTaskAsync(@Nonnull String name);

	boolean containsTask(@Nonnull String name);

	@Nonnull
	Task<Boolean> containsTaskAsync(@Nonnull String name);

	void addTask(@Nonnull ServiceTask task);

	@Nonnull
	Task<Void> addTaskAsync(@Nonnull ServiceTask task);

	void removeTask(@Nonnull ServiceTask task);

	@Nonnull
	Task<Void> removeTaskAsync(@Nonnull ServiceTask task);

	void removeTask(@Nonnull String name);

	@Nonnull
	Task<Void> removeTaskAsync(@Nonnull String name);

}
