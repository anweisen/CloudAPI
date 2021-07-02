package net.anweisen.cloudapi.driver.exceptions;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class IllegalModuleException extends IllegalStateException {

	public IllegalModuleException(@Nonnull String description) {
		super(description);
	}

}
