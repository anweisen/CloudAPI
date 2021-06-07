package net.anweisen.cloudapi.driver.service.specific;

import net.anweisen.cloudapi.driver.service.ServiceManager;
import net.anweisen.cloudapi.driver.utils.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Queue;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see ServiceInfo
 * @see ServiceManager
 */
public interface ServiceController {

	/**
	 * @return the info or {@code null} if the service is not connected, depends on cloud
	 */
	@Nullable
	ServiceInfo getServiceInfo();

	/**
	 * @return the info or {@code null} if the service is not connected, depends on cloud
	 */
	@Nonnull
	Task<ServiceInfo> getServiceInfoAsync();

	/**
	 * Forces the service to update its {@link ServiceInfo}.
	 * Instead of {@link #getServiceInfo()} this will always return the current info.
	 *
	 * @return the current info or {@code null} if the service is not connected, depends on cloud
	 */
	@Nullable
	ServiceInfo updateServiceInfo();

	/**
	 * Forces the service to update its {@link ServiceInfo}.
	 * Instead of {@link #getServiceInfo()} this will always return the current info.
	 *
	 * @return the current info or {@code null} if the service is not connected, depends on cloud
	 */
	@Nonnull
	Task<ServiceInfo> updateServiceInfoAsync();

	void start();

	@Nonnull
	Task<Void> startAsync();

	void stop();

	@Nonnull
	Task<Void> stopAsync();

	void delete();

	@Nonnull
	Task<Void> deleteAsync();

	void restart();

	@Nonnull
	Task<Void> restartAsync();

	void kill();

	@Nonnull
	Task<Void> killAsync();

	void runCommand(@Nonnull String commandline);

	@Nonnull
	Task<Void> runCommandAsync(@Nonnull String commandline);

	@Nonnull
	Queue<String> getCachedLogMessages();

	@Nonnull
	Task<Queue<String>> getCachedLogMessagesAsync();

}
