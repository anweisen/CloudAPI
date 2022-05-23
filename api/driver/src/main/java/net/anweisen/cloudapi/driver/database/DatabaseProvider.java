package net.anweisen.cloudapi.driver.database;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.utilities.database.Database;
import net.anweisen.utilities.database.SpecificDatabase;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see CloudDriver#getDatabaseProvider()
 */
public interface DatabaseProvider {

	@Nonnull
	Database getDatabase();

	@Nonnull
	SpecificDatabase getDatabase(@Nonnull String name);

}
