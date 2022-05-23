package net.anweisen.cloudapi.cloudnet3.driver.service;

import de.dytanic.cloudnet.driver.provider.ServiceTaskProvider;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.service.ServiceTaskManager;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

import static net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping.mapTask;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ServiceTaskManager implements ServiceTaskManager {

	private final ServiceTaskProvider provider;

	public CloudNet3ServiceTaskManager(@Nonnull ServiceTaskProvider provider) {
		this.provider = provider;
	}

	@Override
	public void reload() {
		provider.reload();
	}

	@Nonnull
	@Override
	public Task<Void> reloadAsync() {
		return mapTask(provider.reloadAsync());
	}

	@Nonnull
	@Override
	public Collection<ServiceTask> getTasks() {
		return Mapping.mapServiceTasks(provider.getPermanentServiceTasks());
	}

	@Nonnull
	@Override
	public Task<Collection<ServiceTask>> getTasksAsync() {
		return mapTask(provider.getPermanentServiceTasksAsync().map(Mapping::mapServiceTasks));
	}

	@Nullable
	@Override
	public ServiceTask getTask(@Nonnull String name) {
		return Mapping.mapServiceTask(provider.getServiceTask(name));
	}

	@Nonnull
	@Override
	public Task<ServiceTask> getTaskAsync(@Nonnull String name) {
		return mapTask(provider.getServiceTaskAsync(name).map(Mapping::mapServiceTask));
	}

	@Override
	public boolean containsTask(@Nonnull String name) {
		return provider.isServiceTaskPresent(name);
	}

	@Nonnull
	@Override
	public Task<Boolean> containsTaskAsync(@Nonnull String name) {
		return mapTask(provider.isServiceTaskPresentAsync(name));
	}

	@Override
	public void addTask(@Nonnull ServiceTask task) {
		provider.addPermanentServiceTask(Mapping.extractServiceTask(task));
	}

	@Nonnull
	@Override
	public Task<Void> addTaskAsync(@Nonnull ServiceTask task) {
		return mapTask(provider.addPermanentServiceTaskAsync(Mapping.extractServiceTask(task)).map(v -> null));
	}

	@Override
	public void removeTask(@Nonnull ServiceTask task) {
		provider.removePermanentServiceTask(Mapping.extractServiceTask(task));
	}

	@Nonnull
	@Override
	public Task<Void> removeTaskAsync(@Nonnull ServiceTask task) {
		return mapTask(provider.removePermanentServiceTaskAsync(Mapping.extractServiceTask(task)));
	}

	@Override
	public void removeTask(@Nonnull String name) {
		provider.removePermanentServiceTask(name);
	}

	@Nonnull
	@Override
	public Task<Void> removeTaskAsync(@Nonnull String name) {
		return mapTask(provider.removePermanentServiceTaskAsync(name));
	}
}
