package net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import net.anweisen.utilities.common.config.Document;
import net.anweisen.utilities.common.config.document.AbstractDocument;
import net.anweisen.utilities.common.config.document.GsonDocument;
import net.anweisen.utilities.common.misc.GsonUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.io.IOException;
import java.io.Writer;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Document extends AbstractDocument {

	private final JsonDocument document;

	public CloudNet3Document(@Nonnull JsonDocument document) {
		this.document = document;
	}

	public CloudNet3Document(@Nonnull Document root, @Nullable Document parent, JsonDocument document) {
		super(root, parent);
		this.document = document;
	}

	@Nonnull
	@Override
	protected Document getDocument0(@Nonnull String path, @Nonnull Document root, @Nullable Document parent) {
		return new CloudNet3Document(root, parent, document.getDocument(path));
	}

	@Override
	protected void set0(@Nonnull String path, @Nullable Object value) {
		document.append(path, value);
	}

	@Override
	protected void remove0(@Nonnull String path) {
		document.remove(path);
	}

	@Override
	protected void clear0() {
		document.clear();
	}

	@Nonnull
	@Override
	public List<Document> getDocumentList(@Nonnull String path) {
		JsonArray array = document.getJsonArray(path, new JsonArray());
		List<Document> documents = new ArrayList<>(array.size());
		for (JsonElement element : array) {
			documents.add(new GsonDocument(element.getAsJsonObject()));
		}
		return documents;
	}

	@Override
	public void write(@Nonnull Writer writer) throws IOException {
		document.write(writer);
	}

	@Override
	public boolean isReadonly() {
		return false;
	}

	@Nonnull
	@Override
	public String toJson() {
		return document.toJson();
	}

	@Nonnull
	@Override
	public String toPrettyJson() {
		return document.toPrettyJson();
	}

	@Nullable
	@Override
	public Object getObject(@Nonnull String path) {
		return document.get(path, Object.class);
	}

	@Nullable
	@Override
	public String getString(@Nonnull String path) {
		return document.getString(path);
	}

	@Override
	public long getLong(@Nonnull String path, long def) {
		return document.getLong(path, def);
	}

	@Override
	public int getInt(@Nonnull String path, int def) {
		return document.getInt(path, def);
	}

	@Override
	public short getShort(@Nonnull String path, short def) {
		return document.getShort(path, def);
	}

	@Override
	public byte getByte(@Nonnull String path, byte def) {
		return (byte) (short) document.getShort(path, (short) def);
	}

	@Override
	public float getFloat(@Nonnull String path, float def) {
		return document.getFloat(path, def);
	}

	@Override
	public double getDouble(@Nonnull String path, double def) {
		return document.getDouble(path, def);
	}

	@Override
	public boolean getBoolean(@Nonnull String path, boolean def) {
		return document.getBoolean(path, def);
	}

	@Nonnull
	@Override
	public List<String> getStringList(@Nonnull String path) {
		return GsonUtils.convertJsonArrayToStringList(document.getJsonArray(path, new JsonArray()));
	}

	@Nullable
	@Override
	public UUID getUUID(@Nonnull String path) {
		return document.get(path, UUID.class);
	}

	@Nullable
	@Override
	public OffsetDateTime getDateTime(@Nonnull String path) {
		return document.get(path, OffsetDateTime.class);
	}

	@Nullable
	@Override
	public Date getDate(@Nonnull String path) {
		return document.get(path, Date.class);
	}

	@Nullable
	@Override
	public Color getColor(@Nonnull String path) {
		return document.get(path, Color.class);
	}

	@Override
	public boolean isList(@Nonnull String path) {
		JsonElement element = document.get(path);
		return element != null && element.isJsonArray();
	}

	@Override
	public boolean isObject(@Nonnull String path) {
		JsonElement element = document.get(path);
		return element != null && element.isJsonPrimitive();
	}

	@Override
	public boolean isDocument(@Nonnull String path) {
		JsonElement element = document.get(path);
		return element != null && element.isJsonObject();
	}

	@Override
	public boolean contains(@Nonnull String path) {
		return document.contains(path);
	}

	@Override
	public int size() {
		return document.size();
	}

	@Nonnull
	@Override
	public Map<String, Object> values() {
		return GsonUtils.convertJsonObjectToMap(document.toJsonObject());
	}

	@Nonnull
	@Override
	public Collection<String> keys() {
		return document.keys();
	}

	@Override
	public void forEach(@Nonnull BiConsumer<? super String, ? super Object> action) {
		values().forEach(action);
	}

	@Nonnull
	public JsonDocument getJsonDocument() {
		return document;
	}

	@Override
	public String toString() {
		return document.toJson();
	}
}
