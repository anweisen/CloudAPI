package net.anweisen.cloudapi.driver.service;

import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.cloudapi.driver.utils.task.Task;

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
	 * Reloads all tasks
	 */
	void reload();

	/**
	 * Reloads all tasks
	 */
	@Nonnull
	Task<Void> reloadAsync();

	@Nonnull
	Collection<ServiceTask> getPermanentServiceTasks();

	@Nonnull
	Task<Collection<ServiceTask>> getPermanentServiceTasksAsync();

	@Nullable
	ServiceTask getServiceTask(@Nonnull String name);

	@Nonnull
	Task<ServiceTask> getServiceTaskAsync(@Nonnull String name);

	boolean isServiceTaskPresent(@Nonnull String name);

	@Nonnull
	Task<Boolean> isServiceTaskPresentAsync(@Nonnull String name);

	void addPermanentServiceTask(@Nonnull ServiceTask task);

	@Nonnull
	Task<Void> addPermanentServiceTaskAsync(@Nonnull ServiceTask task);

	void removePermanentServiceTask(@Nonnull ServiceTask task);

	@Nonnull
	Task<Void> removePermanentServiceTaskAsync(@Nonnull ServiceTask task);

}
