package net.anweisen.cloudapi.cloudnet3.driver.database.action;

import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.database.internal.abstraction.AbstractExecutedQuery;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ExecutedQuery extends AbstractExecutedQuery {

	public CloudNet3ExecutedQuery(@Nonnull List<Document> results) {
		super(results);
	}

}
