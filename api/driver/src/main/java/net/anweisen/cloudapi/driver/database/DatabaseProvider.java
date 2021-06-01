package net.anweisen.cloudapi.driver.database;

import net.anweisen.utilities.database.Database;
import net.anweisen.utilities.database.SpecificDatabase;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface DatabaseProvider {

	@Nonnull
	Database getDatabase();

	@Nonnull
	SpecificDatabase getDatabase(@Nonnull String name);

}
