package net.anweisen.cloudapi.cloudnet3.driver.database.action;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.database.Database;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class CloudNet3DatabaseAction {

	protected final Map<String, Object> where = new HashMap<>();
	protected final Database database;

	public CloudNet3DatabaseAction(@Nonnull Database database) {
		this.database = database;
	}

	@Nonnull
	@CheckReturnValue
	protected JsonDocument filter() {
		JsonDocument document = new JsonDocument();
		where.forEach(document::append);
		return document;
	}

	@Nullable
	@CheckReturnValue
	protected Object firstKey() {
		return where.isEmpty() ? null : new ArrayList<>(where.values()).get(0);
	}

}
