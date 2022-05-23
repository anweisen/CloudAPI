package net.anweisen.cloudapi.cloudnet3.driver.database.action;

import de.dytanic.cloudnet.driver.database.Database;
import net.anweisen.utilities.database.action.DatabaseDeletion;
import net.anweisen.utilities.database.exceptions.DatabaseException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3DatabaseDeletion extends CloudNet3DatabaseAction implements DatabaseDeletion {

	public CloudNet3DatabaseDeletion(@Nonnull Database database) {
		super(database);
	}

	@Nonnull
	@Override
	public DatabaseDeletion where(@Nonnull String field, @Nullable Object value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseDeletion where(@Nonnull String field, @Nullable Number value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseDeletion where(@Nonnull String field, @Nullable String value, boolean ignoreCase) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseDeletion where(@Nonnull String field, @Nullable String value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseDeletion whereNot(@Nonnull String field, @Nullable Object value) {
		throw new UnsupportedOperationException("CloudNet v3 does not support where not in their database api");
	}

	@Override
	public void execute() throws DatabaseException {
		try {
			Object keyObject = firstKey();
			if (keyObject == null) {
				database.clear();
			} else {
				String key = String.valueOf(keyObject);
				database.delete(key);
			}
		} catch (Exception ex) {
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean equals(@Nonnull DatabaseDeletion o) {
		if (!(o instanceof CloudNet3DatabaseDeletion)) return false;
		CloudNet3DatabaseDeletion other = (CloudNet3DatabaseDeletion) o;
		return Objects.equals(this.database, other.database)
				&& Objects.equals(this.where, other.where);
	}

}
