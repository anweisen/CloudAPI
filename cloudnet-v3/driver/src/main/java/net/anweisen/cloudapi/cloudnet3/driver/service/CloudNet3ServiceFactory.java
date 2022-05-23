package net.anweisen.cloudapi.cloudnet3.driver.service;

import de.dytanic.cloudnet.driver.provider.service.CloudServiceFactory;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.service.ServiceFactory;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ServiceFactory implements ServiceFactory {

	private final CloudServiceFactory factory;

	public CloudNet3ServiceFactory(@Nonnull CloudServiceFactory factory) {
		this.factory = factory;
	}

	@Nullable
	@Override
	public ServiceInfo createService(@Nonnull ServiceTask task) {
		return Mapping.mapServiceInfo(factory.createCloudService(Mapping.extractServiceTask(task)));
	}

	@Nullable
	@Override
	public ServiceInfo createService(@Nonnull ServiceTask task, int serviceNumber) {
		return Mapping.mapServiceInfo(factory.createCloudService(Mapping.extractServiceTask(task), serviceNumber));
	}

	@Nonnull
	@Override
	public Task<ServiceInfo> createServiceAsync(@Nonnull ServiceTask task) {
		return Mapping.mapTask(factory.createCloudServiceAsync(Mapping.extractServiceTask(task)).map(Mapping::mapServiceInfo));
	}

	@Nonnull
	@Override
	public Task<ServiceInfo> createServiceAsync(@Nonnull ServiceTask task, int serviceNumber) {
		return Mapping.mapTask(factory.createCloudServiceAsync(Mapping.extractServiceTask(task), serviceNumber).map(Mapping::mapServiceInfo));
	}
}
