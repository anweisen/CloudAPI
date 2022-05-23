package net.anweisen.cloudapi.cloudnet3.driver.service.config;

import net.anweisen.cloudapi.driver.service.config.ServiceTemplate;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ServiceTemplate implements ServiceTemplate {

	public final de.dytanic.cloudnet.driver.service.ServiceTemplate template;

	public CloudNet3ServiceTemplate(@Nonnull de.dytanic.cloudnet.driver.service.ServiceTemplate template) {
		this.template = template;
	}

	@Nonnull
	@Override
	public String getName() {
		return template.getName();
	}

	@Nonnull
	@Override
	public String getPrefix() {
		return template.getPrefix();
	}

	@Nonnull
	@Override
	public String getTemplatePath() {
		return template.getTemplatePath();
	}

	@Override
	public boolean shouldCopyToStaticServices() {
		return template.shouldAlwaysCopyToStaticServices();
	}

}
