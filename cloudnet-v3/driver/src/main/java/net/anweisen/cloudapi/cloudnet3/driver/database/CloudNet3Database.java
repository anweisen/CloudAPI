package net.anweisen.cloudapi.cloudnet3.driver.database;

import de.dytanic.cloudnet.driver.database.DatabaseProvider;
import net.anweisen.utilities.database.Database;
import net.anweisen.utilities.database.DatabaseConfig;
import net.anweisen.utilities.database.SQLColumn;
import net.anweisen.utilities.database.SpecificDatabase;
import net.anweisen.utilities.database.action.*;
import net.anweisen.utilities.database.exceptions.DatabaseException;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Database implements Database {

	private final Map<String, CloudNet3SpecificDatabase> databases = new HashMap<>();
	private final DatabaseProvider provider;

	public CloudNet3Database(@Nonnull DatabaseProvider provider) {
		this.provider = provider;
	}

	@Override
	public boolean isConnected() {
		return true;
	}

	@Override
	public void connect() throws DatabaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean connectSafely() {
		return false;
	}

	@Override
	public void disconnect() throws DatabaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean disconnectSafely() {
		return false;
	}

	@Override
	public void createTable(@Nonnull String name, @Nonnull SQLColumn... columns) throws DatabaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createTableSafely(@Nonnull String name, @Nonnull SQLColumn... columns) {
		throw new UnsupportedOperationException();
	}

	@Nonnull
	@Override
	public DatabaseQuery query(@Nonnull String table) {
		return getSpecificDatabase(table).query();
	}

	@Nonnull
	@Override
	public DatabaseUpdate update(@Nonnull String table) {
		return getSpecificDatabase(table).update();
	}

	@Nonnull
	@Override
	public DatabaseInsertion insert(@Nonnull String table) {
		return getSpecificDatabase(table).insert();
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate insertOrUpdate(@Nonnull String table) {
		return getSpecificDatabase(table).insertOrUpdate();
	}

	@Nonnull
	@Override
	public DatabaseDeletion delete(@Nonnull String table) {
		return getSpecificDatabase(table).delete();
	}

	@Nonnull
	@Override
	public SpecificDatabase getSpecificDatabase(@Nonnull String name) {
		return databases.computeIfAbsent(name, key -> new CloudNet3SpecificDatabase(name, this, provider.getDatabase(name)));
	}

	@Nonnull
	@Override
	public DatabaseConfig getConfig() {
		throw new UnsupportedOperationException();
	}

}
