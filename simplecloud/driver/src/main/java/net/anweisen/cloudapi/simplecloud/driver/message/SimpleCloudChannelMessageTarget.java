package net.anweisen.cloudapi.simplecloud.driver.message;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SimpleCloudChannelMessageTarget {

	private final Type type;
	private final String name;

	public SimpleCloudChannelMessageTarget(@Nonnull Type type) {
		this(type, null);
	}

	public SimpleCloudChannelMessageTarget(@Nonnull Type type, @Nullable String name) {
		this.type = type;
		this.name = name;
	}

	@Nullable
	public String getName() {
		return name;
	}

	@Nonnull
	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Target(" + type + ':' + name + ')';
	}

	enum Type {
		ALL,
		NODE,
		SERVICE,
		TASK
	}
}
