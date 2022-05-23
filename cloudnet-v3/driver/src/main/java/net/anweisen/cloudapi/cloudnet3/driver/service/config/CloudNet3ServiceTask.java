package net.anweisen.cloudapi.cloudnet3.driver.service.config;

import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.driver.service.config.ServiceTask;
import net.anweisen.cloudapi.driver.service.config.ServiceTemplate;
import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ServiceTask implements ServiceTask {

	public final de.dytanic.cloudnet.driver.service.ServiceTask task;

	public CloudNet3ServiceTask(@Nonnull de.dytanic.cloudnet.driver.service.ServiceTask task) {
		this.task = task;
	}

	@Nonnull
	@Override
	public String getName() {
		return task.getName();
	}

	@Override
	public void setName(@Nonnull String name) {
		task.setName(name);
	}

	@Override
	public int getMinServiceCount() {
		return task.getMinServiceCount();
	}

	@Override
	public void setMinServiceCount(int count) {
		task.setMinServiceCount(count);
	}

	@Override
	public int getStartPort() {
		return task.getStartPort();
	}

	@Override
	public void setStartPort(int port) {
		task.setStartPort(port);
	}

	@Override
	public boolean isDisableIpRewrite() {
		return task.isDisableIpRewrite();
	}

	@Override
	public void setDisableIpRewrite(boolean disabled) {
		task.setDisableIpRewrite(disabled);
	}

	@Override
	public boolean isMaintenance() {
		return task.isMaintenance();
	}

	@Override
	public void setMaintenance(boolean maintenance) {
		task.setMaintenance(maintenance);
	}

	@Override
	public boolean isAutoDeleteOnStop() {
		return task.isAutoDeleteOnStop();
	}

	@Override
	public void setAutoDeleteOnStop(boolean autoDeleteOnStop) {
		task.setAutoDeleteOnStop(autoDeleteOnStop);
	}

	@Override
	public boolean isStaticServices() {
		return task.isStaticServices();
	}

	@Override
	public void setStaticServices(boolean staticServices) {
		task.setStaticServices(staticServices);
	}

	@Nonnull
	@Override
	public Collection<String> getNodes() {
		return task.getAssociatedNodes();
	}

	@Nonnull
	@Override
	public Collection<ServiceTemplate> getTemplates() {
		return task.getTemplates().stream().map(CloudNet3ServiceTemplate::new).collect(Collectors.toList());
	}

	@Override
	public boolean canStartNewServices() {
		return task.canStartServices();
	}

	@Override
	public void forbidServiceStarting(long millis) {
		task.forbidServiceStarting(millis);
	}

	@Nonnull
	@Override
	public Document getProperties() {
		return Mapping.mapDocument(task.getProperties());
	}

	@Nonnull
	@Override
	public ServiceTask clone() {
		return new CloudNet3ServiceTask(task.makeClone());
	}
}
