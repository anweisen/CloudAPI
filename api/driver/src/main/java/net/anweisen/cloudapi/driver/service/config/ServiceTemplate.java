package net.anweisen.cloudapi.driver.service.config;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ServiceTemplate {

	@Nonnull
	String getName();

	@Nonnull
	String getPrefix();

	@Nonnull
	String getTemplatePath();

	boolean shouldCopyToStaticServices();

}
