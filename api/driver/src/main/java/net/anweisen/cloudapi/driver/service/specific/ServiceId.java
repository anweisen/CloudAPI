package net.anweisen.cloudapi.driver.service.specific;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface ServiceId {

	@Nonnull
	String getName();

	@Nonnull
	UUID getUniqueId();

	int getServiceTaskId();

	@Nonnull
	String getNodeName();

}
