package net.anweisen.cloudapi.cloudnet3.driver.database.action;

import de.dytanic.cloudnet.driver.database.Database;
import net.anweisen.utilities.database.action.DatabaseInsertion;
import net.anweisen.utilities.database.action.DatabaseInsertionOrUpdate;
import net.anweisen.utilities.database.action.DatabaseUpdate;
import net.anweisen.utilities.database.exceptions.DatabaseException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3DatabaseInsertionOrUpdate extends CloudNet3DatabaseAction implements DatabaseInsertionOrUpdate {

	public CloudNet3DatabaseInsertionOrUpdate(@Nonnull Database database) {
		super(database);
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate where(@Nonnull String field, @Nullable Object value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate where(@Nonnull String field, @Nullable Number value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate where(@Nonnull String field, @Nullable String value, boolean ignoreCase) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate where(@Nonnull String field, @Nullable String value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate whereNot(@Nonnull String field, @Nullable Object value) {
		throw new UnsupportedOperationException("CloudNet v3 does not support where not in their database api");
	}

	@Nonnull
	@Override
	public DatabaseInsertionOrUpdate set(@Nonnull String field, @Nullable Object value) {
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
	public boolean equals(@Nonnull DatabaseUpdate other) {
		return other instanceof CloudNet3DatabaseInsertionOrUpdate && equals((CloudNet3DatabaseInsertionOrUpdate) other);
	}

	@Override
	public boolean equals(@Nonnull DatabaseInsertion other) {
		return other instanceof CloudNet3DatabaseInsertionOrUpdate && equals((CloudNet3DatabaseInsertionOrUpdate) other);
	}

	@Override
	public boolean equals(@Nonnull DatabaseInsertionOrUpdate o) {
		if (!(o instanceof CloudNet3DatabaseInsertionOrUpdate)) return false;
		CloudNet3DatabaseInsertionOrUpdate other = (CloudNet3DatabaseInsertionOrUpdate) o;
		return Objects.equals(this.database, other.database)
				&& Objects.equals(this.where, other.where);
	}
}
