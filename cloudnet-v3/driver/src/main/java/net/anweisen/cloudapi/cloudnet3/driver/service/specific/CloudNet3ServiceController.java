package net.anweisen.cloudapi.cloudnet3.driver.service.specific;

import de.dytanic.cloudnet.driver.provider.service.SpecificCloudServiceProvider;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.service.specific.ServiceController;
import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;
import net.anweisen.utilities.common.concurrent.task.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Queue;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ServiceController implements ServiceController {

	private final SpecificCloudServiceProvider provider;

	public CloudNet3ServiceController(@Nonnull SpecificCloudServiceProvider provider) {
		this.provider = provider;
	}

	@Nullable
	@Override
	public ServiceInfo getServiceInfo() {
		return Mapping.mapServiceInfo(provider.getServiceInfoSnapshot());
	}

	@Nonnull
	@Override
	public Task<ServiceInfo> getServiceInfoAsync() {
		return Mapping.mapTask(provider.getServiceInfoSnapshotAsync().map(Mapping::mapServiceInfo));
	}

	@Nullable
	@Override
	public ServiceInfo updateServiceInfo() {
		return Mapping.mapServiceInfo(provider.forceUpdateServiceInfo());
	}

	@Nonnull
	@Override
	public Task<ServiceInfo> updateServiceInfoAsync() {
		return Mapping.mapTask(provider.forceUpdateServiceInfoAsync().map(Mapping::mapServiceInfo));
	}

	@Override
	public void start() {
		provider.start();
	}

	@Nonnull
	@Override
	public Task<Void> startAsync() {
		return Mapping.mapTask(provider.startAsync());
	}

	@Override
	public void stop() {
		provider.stop();
	}

	@Nonnull
	@Override
	public Task<Void> stopAsync() {
		return Mapping.mapTask(provider.stopAsync());
	}

	@Override
	public void delete() {
		provider.delete();
	}

	@Nonnull
	@Override
	public Task<Void> deleteAsync() {
		return Mapping.mapTask(provider.deleteAsync());
	}

	@Override
	public void restart() {
		provider.restart();
	}

	@Nonnull
	@Override
	public Task<Void> restartAsync() {
		return Mapping.mapTask(provider.restartAsync());
	}

	@Override
	public void kill() {
		provider.kill();
	}

	@Nonnull
	@Override
	public Task<Void> killAsync() {
		return Mapping.mapTask(provider.killAsync());
	}

	@Override
	public void runCommand(@Nonnull String commandline) {
		provider.runCommand(commandline);
	}

	@Nonnull
	@Override
	public Task<Void> runCommandAsync(@Nonnull String commandline) {
		return Mapping.mapTask(provider.runCommandAsync(commandline));
	}

	@Nonnull
	@Override
	public Queue<String> getCachedLogMessages() {
		return provider.getCachedLogMessages();
	}

	@Nonnull
	@Override
	public Task<Queue<String>> getCachedLogMessagesAsync() {
		return Mapping.mapTask(provider.getCachedLogMessagesAsync());
	}
}
