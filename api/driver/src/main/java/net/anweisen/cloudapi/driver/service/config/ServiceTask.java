package net.anweisen.cloudapi.driver.service.config;

import net.anweisen.utilities.common.config.Document;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ServiceTask {

	@Nonnull
	String getName();

	void setName(@Nonnull String name);

	int getMinServiceCount();

	void setMinServiceCount(int count);

	int getStartPort();

	void setStartPort(int port);

	boolean isDisableIpRewrite();

	void setDisableIpRewrite(boolean disabled);

	boolean isMaintenance();

	void setMaintenance(boolean maintenance);

	boolean isAutoDeleteOnStop();

	void setAutoDeleteOnStop(boolean autoDeleteOnStop);

	boolean isStaticServices();

	void setStaticServices(boolean staticServices);

	@Nonnull
	Collection<String> getNodes();

	@Nonnull
	Collection<ServiceTemplate> getTemplates();

	/**
	 * Cloud intern rule if this service task is allowed to start more services.
	 *
	 * @return if this task is allowed to start more services
	 */
	boolean canStartNewServices();

	void forbidServiceStarting(long millis);

	default void forbidServiceStarting(long time, @Nonnull TimeUnit unit) {
		forbidServiceStarting(unit.toMillis(time));
	}

	@Nonnull
	Document getProperties();

	@Nonnull
	ServiceTask clone();

}
