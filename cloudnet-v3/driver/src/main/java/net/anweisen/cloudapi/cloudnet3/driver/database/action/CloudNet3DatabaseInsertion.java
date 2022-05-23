package net.anweisen.cloudapi.cloudnet3.driver.database.action;

import de.dytanic.cloudnet.driver.database.Database;
import net.anweisen.utilities.database.action.DatabaseInsertion;
import net.anweisen.utilities.database.exceptions.DatabaseException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3DatabaseInsertion extends CloudNet3DatabaseAction implements DatabaseInsertion {

	public CloudNet3DatabaseInsertion(@Nonnull Database database) {
		super(database);
	}

	@Nonnull
	@Override
	public DatabaseInsertion set(@Nonnull String field, @Nullable Object value) {
		where.put(field, value);
		return this;
	}

	@Override
	public void execute() throws DatabaseException {
		try {
			Object keyObject = firstKey();
			database.insert(String.valueOf(keyObject), filter());
		} catch (Exception ex) {
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean equals(@Nonnull DatabaseInsertion other) {
		return false;
	}

}
