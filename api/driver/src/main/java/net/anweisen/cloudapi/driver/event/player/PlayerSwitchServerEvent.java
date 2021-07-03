package net.anweisen.cloudapi.driver.event.player;

import net.anweisen.cloudapi.driver.service.specific.ServiceInfo;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class PlayerSwitchServerEvent extends PlayerEvent {

	private final ServiceInfo service;

	public PlayerSwitchServerEvent(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull ServiceInfo service) {
		super(uniqueId, name);
		this.service = service;
	}

	/**
	 * @return the service the player switched to
	 */
	@Nonnull
	public ServiceInfo getService() {
		return service;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + getName() + ":" + getUniqueId() + " -> " + getService().getName() + "]";
	}
}
