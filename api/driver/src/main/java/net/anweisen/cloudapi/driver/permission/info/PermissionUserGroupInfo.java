package net.anweisen.cloudapi.driver.permission.info;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PermissionUserGroupInfo {

	/**
	 * @return the name of the corresponding group
	 */
	@Nonnull
	String getName();

	/**
	 * @return the time out date of this permission in millis or {@code 0} or {@code -1}, depending on the cloud
	 *         if this user group does not have timeout or this cloud does not support user group timeouts
	 */
	long getTimeOutMillis();

}
