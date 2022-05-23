package net.anweisen.cloudapi.cloudnet3.driver.database.action;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.database.Database;
import net.anweisen.cloudapi.cloudnet3.driver.utils.Mapping;
import net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper.CloudNet3Document;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.database.Order;
import net.anweisen.utilities.database.action.DatabaseQuery;
import net.anweisen.utilities.database.action.ExecutedQuery;
import net.anweisen.utilities.database.exceptions.DatabaseException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3DatabaseQuery extends CloudNet3DatabaseAction implements DatabaseQuery {

	private String orderBy;
	private Order order;

	public CloudNet3DatabaseQuery(@Nonnull Database database) {
		super(database);
	}

	@Nonnull
	@Override
	public DatabaseQuery where(@Nonnull String field, @Nullable Object value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseQuery where(@Nonnull String field, @Nullable Number value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseQuery where(@Nonnull String field, @Nullable String value, boolean ignoreCase) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseQuery where(@Nonnull String field, @Nullable String value) {
		where.put(field, value);
		return this;
	}

	@Nonnull
	@Override
	public DatabaseQuery whereNot(@Nonnull String field, @Nullable Object value) {
		throw new UnsupportedOperationException("CloudNet v3 does not support where not in their database api");
	}

	@Nonnull
	@Override
	public DatabaseQuery select(@Nonnull String... selection) {
		return this;
	}

	@Nonnull
	@Override
	public DatabaseQuery orderBy(@Nonnull String field, @Nonnull Order order) {
		this.orderBy = field;
		this.order = order;
		return this;
	}

	@Nonnull
	@Override
	public ExecutedQuery execute() throws DatabaseException {
		List<JsonDocument> documents;
		try {
			documents = database.get(filter());
		} catch (Exception ex) {
			throw new DatabaseException(ex);
		}

		List<Document> result = new ArrayList<>(documents.size());
		documents.forEach(document -> result.add(Mapping.mapDocument(document)));

		return new CloudNet3ExecutedQuery(result);
	}

	@Override
	public boolean equals(@Nonnull ExecutedQuery o) {
		if (!(o instanceof CloudNet3DatabaseQuery)) return false;
		CloudNet3DatabaseQuery other = (CloudNet3DatabaseQuery) o;
		return Objects.equals(this.database, other.database)
				&& Objects.equals(this.where, other.where)
				&& Objects.equals(this.order, other.order)
				&& Objects.equals(this.orderBy, other.orderBy);
	}

}
