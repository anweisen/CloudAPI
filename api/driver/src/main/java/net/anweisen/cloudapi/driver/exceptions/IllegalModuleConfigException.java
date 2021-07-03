package net.anweisen.cloudapi.driver.exceptions;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class IllegalModuleConfigException extends IllegalModuleException {

	public IllegalModuleConfigException(@Nonnull String description) {
		super(description);
	}

	public IllegalModuleConfigException(@Nonnull String message, @Nonnull Throwable cause) {
		super(message, cause);
	}

}
