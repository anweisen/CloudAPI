package net.anweisen.cloudapi.driver.service;

import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ServiceFactory {

	/**
	 * Creates and prepares a new cloud service.
	 *
	 * @param task the task the service should be created from
	 * @return the info of the created service or {@code null} if the service could not be created
	 */
	@Nullable
	ServiceInfo createService(@Nonnull ServiceTask task);

	/**
	 * Creates and prepares a new cloud service.
	 *
	 * @param task the task the service should be created from
	 * @param serviceNumber the id of the new service, {@link ServiceInfo#getServiceNumber()}
	 * @return the info of the created service or {@code null} if the service could not be created
	 */
	@Nullable
	ServiceInfo createService(@Nonnull ServiceTask task, int serviceNumber);

	/**
	 * Creates and prepares a new cloud service.
	 *
	 * @param task the task the service should be created from
	 * @return the info of the created service or {@code null} if the service could not be created
	 */
	@Nonnull
	Task<ServiceInfo> createServiceAsync(@Nonnull ServiceTask task);

	/**
	 * Creates and prepares a new cloud service.
	 *
	 * @param task the task the service should be created from
	 * @param serviceNumber the id of the new service, {@link ServiceInfo#getServiceNumber()}
	 * @return the info of the created service or {@code null} if the service could not be created
	 */
	@Nonnull
	Task<ServiceInfo> createServiceAsync(@Nonnull ServiceTask task, int serviceNumber);

}
