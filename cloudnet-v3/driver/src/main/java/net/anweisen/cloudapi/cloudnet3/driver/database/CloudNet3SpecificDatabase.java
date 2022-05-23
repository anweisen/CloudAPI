package net.anweisen.cloudapi.cloudnet3.driver.database;

import net.anweisen.cloudapi.cloudnet3.driver.database.action.CloudNet3DatabaseDeletion;
import net.anweisen.cloudapi.cloudnet3.driver.database.action.CloudNet3DatabaseInsertion;
import net.anweisen.cloudapi.cloudnet3.driver.database.action.CloudNet3DatabaseInsertionOrUpdate;
import net.anweisen.cloudapi.cloudnet3.driver.database.action.CloudNet3DatabaseQuery;
import net.anweisen.utilities.database.Database;
import net.anweisen.utilities.database.SpecificDatabase;
import net.anweisen.utilities.database.action.*;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3SpecificDatabase implements SpecificDatabase {

	private final String name;
	private final Database parent;
	private final de.dytanic.cloudnet.driver.database.Database database;

	public CloudNet3SpecificDatabase(@Nonnull String name, @Nonnull Database parent, @Nonnull de.dytanic.cloudnet.driver.database.Database database) {
		this.name = name;
		this.parent = parent;
		this.database = database;
	}

	@Override
	public boolean isConnected() {
		return parent.isConnected();
	}

	@Nonnull
	@Override
	public String getName() {
		return name;
	}

	@Nonnull
	@Override
	public DatabaseQuery query() {
		return new CloudNet3DatabaseQuery(database);
	}

	@Nonnull
	@Override
	public DatabaseUpdate update() {
		return new CloudNet3DatabaseInsertionOrUpdate(database);
	}

	@Nonnull
	@Override
	public DatabaseInsertion insert() {
		return new CloudNet3DatabaseInsertion(database);
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate insertOrUpdate() {
		return new CloudNet3DatabaseInsertionOrUpdate(database);
	}

	@Nonnull
	@Override
	public DatabaseDeletion delete() {
		return new CloudNet3DatabaseDeletion(database);
	}

	@Nonnull
	@Override
	public Database getParent() {
		return parent;
	}
}
