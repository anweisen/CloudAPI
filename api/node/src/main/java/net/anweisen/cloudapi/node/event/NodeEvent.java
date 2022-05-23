package net.anweisen.cloudapi.node.event;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.node.CloudNode;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface NodeEvent extends Event {

	@Nonnull
	default CloudNode getCloud() {
		return CloudNode.getInstance();
	}

}
