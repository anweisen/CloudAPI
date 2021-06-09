package net.anweisen.cloudapi.driver.node;

import net.anweisen.cloudapi.driver.component.NetworkComponent;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface NodeInfo extends NetworkComponent {

	@Nonnull
	String getName();

}
