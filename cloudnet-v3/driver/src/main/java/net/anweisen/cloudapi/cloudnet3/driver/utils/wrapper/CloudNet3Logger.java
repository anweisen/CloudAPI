package net.anweisen.cloudapi.cloudnet3.driver.utils.wrapper;

import net.anweisen.utilities.common.logging.ILogger;
import net.anweisen.utilities.common.logging.LogLevel;
import net.anweisen.utilities.common.logging.internal.FallbackLogger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3Logger implements ILogger {

	private static de.dytanic.cloudnet.common.logging.LogLevel convert(@Nonnull LogLevel level) {
		switch (level) {
			case ERROR:     return de.dytanic.cloudnet.common.logging.LogLevel.ERROR;
			case WARN:      return de.dytanic.cloudnet.common.logging.LogLevel.WARNING;
			case TRACE:     return de.dytanic.cloudnet.common.logging.LogLevel.EXTENDED;
			case DEBUG:     return de.dytanic.cloudnet.common.logging.LogLevel.DEBUG;
			default:        return de.dytanic.cloudnet.common.logging.LogLevel.INFO;
		}
	}

	private final de.dytanic.cloudnet.common.logging.ILogger logger;

	public CloudNet3Logger(@Nonnull de.dytanic.cloudnet.common.logging.ILogger logger) {
		this.logger = logger;
	}

	@Override
	public void log(@Nonnull LogLevel level, @Nullable String message, @Nonnull Object... args) {
		Throwable ex = null;
		for (Object arg : args) {
			if (arg instanceof Throwable)
				ex = (Throwable) arg;
		}

		logger.log(convert(level), FallbackLogger.formatMessage(message, args) + (ex != null ? ": " : ""), ex);
	}

	@Nonnull
	@Override
	public LogLevel getMinLevel() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Nonnull
	@Override
	public ILogger setMinLevel(@Nonnull LogLevel level) {
		logger.setLevel(level.getValue()); // TODO this is not right
		return this;
	}

	@Nullable
	@Override
	public String getName() {
		return "CloudNet-Logger"; // CloudNet loggers dont have names
	}
}
