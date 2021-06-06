package net.anweisen.cloudapi.driver.support;

import net.anweisen.cloudapi.driver.utils.exceptions.UnsupportedCloudFeatureException;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public final class SupportInfo {

	private final EnumSet<SupportFlag> flags;

	public SupportInfo(@Nonnull EnumSet<SupportFlag> flags) {
		this.flags = flags;
	}

	public SupportInfo(@Nonnull SupportFlag... flags) {
		this(flags.length == 0 ? EnumSet.noneOf(SupportFlag.class) : EnumSet.copyOf(Arrays.asList(flags)));
	}

	@Nonnull
	public Set<SupportFlag> getFlags() {
		return Collections.unmodifiableSet(flags);
	}

	public boolean supports(@Nonnull SupportFlag flag) {
		return flags.contains(flag);
	}

	public boolean supportsAll(@Nonnull SupportFlag... flags) {
		return supportsAll(Arrays.asList(flags));
	}

	public boolean supportsAll(@Nonnull Collection<? extends SupportFlag> flags) {
		return this.flags.containsAll(flags);
	}

	public boolean supportsAny(@Nonnull SupportFlag... flags) {
		for (SupportFlag flag : flags) {
			if (this.flags.contains(flag))
				return true;
		}
		return false;
	}

	public boolean supportsAny(@Nonnull Collection<? extends SupportFlag> flags) {
		for (SupportFlag flag : flags) {
			if (this.flags.contains(flag))
				return true;
		}
		return false;
	}

	public void require(@Nonnull SupportFlag flag) {
		if (!supports(flag))
			throw new UnsupportedCloudFeatureException(1, flag);
	}

	public void requireAll(@Nonnull SupportFlag... flags) {
		if (!supportsAll(flags))
			throw new UnsupportedCloudFeatureException(1);
	}

	public void requireAll(@Nonnull Collection<? extends SupportFlag> flags) {
		if (!supportsAll(flags))
			throw new UnsupportedCloudFeatureException(1);
	}

	public void requireAny(@Nonnull SupportFlag... flags) {
		if (!supportsAny(flags))
			throw new UnsupportedCloudFeatureException(1);
	}

	public void requireAny(@Nonnull Collection<? extends SupportFlag> flags) {
		if (!supportsAny(flags))
			throw new UnsupportedCloudFeatureException(1);
	}

}
