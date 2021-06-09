package net.anweisen.cloudapi.driver.component;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface NetworkComponent {

	@Nonnull
	String getName();

	@Nonnull
	String getHost();

}
