package net.anweisen.cloudapi.driver.utils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public final class CallerNameResolver {

	@Nonnull
	public static String resolve(int skipCallers) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		StackTraceElement element = trace[3 + skipCallers];

		String className = getApiClassName(element.getClassName());
		return (className == null ? element.getClassName() : className + ".") + element.getMethodName() + "(..)";
	}

	private static String getApiClassName(@Nonnull String className) {
		Class<?> caller;
		try {
			caller = Class.forName(className);
		} catch (ClassNotFoundException ex) {
			return null;
		}

		if (caller.isInterface())
			return caller.getSimpleName();

		Class<?>[] interfaces = caller.getInterfaces();
		List<Class<?>> apiInterfaces = new ArrayList<>();
		for (Class<?> implemented : interfaces) {
			if (implemented.getName().startsWith("net.anweisen.cloudapi."))
				apiInterfaces.add(implemented);
		}

		if (apiInterfaces.size() == 1)
			return apiInterfaces.get(0).getSimpleName();

		return null;
	}

}
