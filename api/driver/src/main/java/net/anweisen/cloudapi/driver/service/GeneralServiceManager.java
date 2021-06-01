package net.anweisen.cloudapi.driver.service;

import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.cloudapi.driver.utils.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface GeneralServiceManager {

	@Nonnull
	Collection<ServiceInfo> getServices();

	@Nonnull
	Task<Collection<ServiceInfo>> getServicesAsync();

	@Nonnull
	Collection<UUID> getServiceUniqueIds();

	@Nonnull
	Task<Collection<UUID>> getServiceUniqueIdsAsync();

	@Nonnull
	Collection<ServiceInfo> getStartedServices();

	@Nonnull
	Task<Collection<ServiceInfo>> getStartedServicesAsync();

	@Nonnull
	Collection<ServiceInfo> getServicesByTask(@Nonnull String taskName);

	@Nonnull
	Task<Collection<ServiceInfo>> getServicesByTaskAsync(@Nonnull String taskName);

	/**
	 * @throws UnsupportedOperationException
	 *         If this cloud does not have a group system
	 */
	@Nonnull
	Collection<ServiceInfo> getServicesByGroup(@Nonnull String groupName);

	/**
	 * @throws UnsupportedOperationException
	 *         If this cloud does not have a group system
	 */
	@Nonnull
	Task<Collection<ServiceInfo>> getServicesByGroupAsync(@Nonnull String groupName);

	int getServiceCount();

	@Nonnull
	Task<Integer> getServiceCountAsync();

	int getServiceCountByTask(@Nonnull String taskName);

	@Nonnull
	Task<Integer> getServiceCountByTaskAsync(@Nonnull String taskName);

	/**
	 * @throws UnsupportedOperationException
	 *         If this cloud does not have a group system
	 */
	int getServiceCountByGroup(@Nonnull String groupName);

	/**
	 * @throws UnsupportedOperationException
	 *         If this cloud does not have a group system
	 */
	@Nonnull
	Task<Integer> getServiceCountByGroupAsync(@Nonnull String groupName);

	@Nullable
	ServiceInfo getServiceByName(@Nonnull String name);

	@Nonnull
	Task<ServiceInfo> getServiceByNameAsync(@Nonnull String name);

	@Nullable
	ServiceInfo getServiceById(@Nonnull UUID id);

	@Nonnull
	Task<ServiceInfo> getServiceByIdAsync(@Nonnull UUID id);

}
