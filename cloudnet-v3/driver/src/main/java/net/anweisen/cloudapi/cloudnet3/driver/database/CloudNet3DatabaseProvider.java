package net.anweisen.cloudapi.cloudnet3.driver.database;

import net.anweisen.cloudapi.driver.database.DatabaseProvider;
import net.anweisen.utilities.database.Database;
import net.anweisen.utilities.database.SpecificDatabase;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3DatabaseProvider implements DatabaseProvider {

	private final Database database;

	public CloudNet3DatabaseProvider(de.dytanic.cloudnet.driver.database.DatabaseProvider provider) {
		this.database = new CloudNet3Database(provider);
	}

	@Nonnull
	@Override
	public Database getDatabase() {
		return database;
	}

	@Nonnull
	@Override
	public SpecificDatabase getDatabase(@Nonnull String name) {
		return getDatabase().getSpecificDatabase(name);
	}

}
