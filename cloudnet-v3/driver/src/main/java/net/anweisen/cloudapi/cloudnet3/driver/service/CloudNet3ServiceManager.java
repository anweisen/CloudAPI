package net.anweisen.cloudapi.cloudnet3.driver.service;

import de.dytanic.cloudnet.driver.provider.service.GeneralCloudServiceProvider;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.service.ServiceManager;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ServiceManager implements ServiceManager {

	private final GeneralCloudServiceProvider provider;

	public CloudNet3ServiceManager(@Nonnull GeneralCloudServiceProvider provider) {
		this.provider = provider;
	}

	@Nonnull
	@Override
	public Collection<ServiceInfo> getServices() {
		return Mapping.mapServiceInfos(provider.getCloudServices());
	}

	@Nonnull
	@Override
	public Task<Collection<ServiceInfo>> getServicesAsync() {
		return Mapping.mapTask(provider.getCloudServicesAsync().map(Mapping::mapServiceInfos));
	}

	@Nonnull
	@Override
	public Collection<UUID> getServiceUUIDs() {
		return provider.getServicesAsUniqueId();
	}

	@Nonnull
	@Override
	public Task<Collection<UUID>> getServiceUniqueIdsAsync() {
		return Mapping.mapTask(provider.getServicesAsUniqueIdAsync());
	}

	@Nonnull
	@Override
	public Collection<ServiceInfo> getStartedServices() {
		return Mapping.mapServiceInfos(provider.getStartedCloudServices());
	}

	@Nonnull
	@Override
	public Task<Collection<ServiceInfo>> getStartedServicesAsync() {
		return Mapping.mapTask(provider.getStartedCloudServicesAsync().map(Mapping::mapServiceInfos));
	}

	@Nonnull
	@Override
	public Collection<ServiceInfo> getServicesByTask(@Nonnull String taskName) {
		return Mapping.mapServiceInfos(provider.getCloudServices(taskName));
	}

	@Nonnull
	@Override
	public Task<Collection<ServiceInfo>> getServicesByTaskAsync(@Nonnull String taskName) {
		return Mapping.mapTask(provider.getCloudServicesAsync(taskName).map(Mapping::mapServiceInfos));
	}

	@Override
	public int getServiceCount() {
		return provider.getServicesCount();
	}

	@Nonnull
	@Override
	public Task<Integer> getServiceCountAsync() {
		return Mapping.mapTask(provider.getServicesCountAsync());
	}

	@Override
	public int getServiceCountByTask(@Nonnull String taskName) {
		return provider.getServicesCountByTask(taskName);
	}

	@Nonnull
	@Override
	public Task<Integer> getServiceCountByTaskAsync(@Nonnull String taskName) {
		return Mapping.mapTask(provider.getServicesCountByTaskAsync(taskName));
	}

	@Nullable
	@Override
	public ServiceInfo getServiceByName(@Nonnull String name) {
		return Mapping.mapServiceInfo(provider.getCloudServiceByName(name));
	}

	@Nonnull
	@Override
	public Task<ServiceInfo> getServiceByNameAsync(@Nonnull String name) {
		return Mapping.mapTask(provider.getCloudServiceByNameAsync(name).map(Mapping::mapServiceInfo));
	}

	@Nullable
	@Override
	public ServiceInfo getServiceById(@Nonnull UUID uuid) {
		return Mapping.mapServiceInfo(provider.getCloudService(uuid));
	}

	@Nonnull
	@Override
	public Task<ServiceInfo> getServiceByIdAsync(@Nonnull UUID uuid) {
		return Mapping.mapTask(provider.getCloudServiceAsync(uuid).map(Mapping::mapServiceInfo));
	}

}
