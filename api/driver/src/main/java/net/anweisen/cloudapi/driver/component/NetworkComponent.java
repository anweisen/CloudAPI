package net.anweisen.cloudapi.driver.component;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see net.anweisen.cloudapi.driver.service.specific.ServiceInfo
 * @see net.anweisen.cloudapi.driver.node.NodeInfo
 */
public interface NetworkComponent {

	@Nonnull
	String getName();

	@Nonnull
	String getHost();

	@Nonnull
	ComponentType getComponentType();

}
