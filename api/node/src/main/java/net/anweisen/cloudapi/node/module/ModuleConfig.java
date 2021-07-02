package net.anweisen.cloudapi.node.module;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see ModuleController#getModuleConfig()
 */
public final class ModuleConfig {

	private final String name, author, version, description, jarFile, mainClass, website;

	public ModuleConfig(@Nonnull String name, @Nonnull String author, @Nonnull String version, @Nonnull String description, @Nonnull String jarFileName, @Nonnull String mainClass, @Nonnull String website) {
		this.name = name;
		this.author = author;
		this.version = version;
		this.description = description;
		this.jarFile = jarFileName;
		this.mainClass = mainClass;
		this.website = website;
	}

	@Nonnull
	public String getName() {
		return name;
	}

	@Nonnull
	public String getAuthor() {
		return author;
	}

	@Nonnull
	public String getVersion() {
		return version;
	}

	@Nonnull
	public String getDescription() {
		return description;
	}

	@Nonnull
	public String getJarFile() {
		return jarFile;
	}

	@Nonnull
	public String getWebsite() {
		return website;
	}

	@Nonnull
	public String getMainClass() {
		return mainClass;
	}

	@Nonnull
	public String getFullName() {
		return getName() + " v" + getVersion();
	}

	@Override
	public String toString() {
		return "ModuleConfig[" + getName() + " v" + getVersion() + " by " + getAuthor() + ": " + getJarFile() + "]";
	}
}
