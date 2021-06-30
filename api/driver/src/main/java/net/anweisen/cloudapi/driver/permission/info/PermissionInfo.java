package net.anweisen.cloudapi.driver.permission.info;

import net.anweisen.cloudapi.driver.exceptions.UnsupportedCloudFeatureException;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface PermissionInfo extends Comparable<PermissionInfo> {

	/**
	 * @return the name of this permission
	 */
	@Nonnull
	String getName();

	/**
	 * @return the potency of this permission or {@code 1} if this cloud does not support permission potencys
	 */
	int getPotency();

	/**
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support permission potencys
	 */
	void setPotency(int potency);

	/**
	 * @return the time out date of this permission in millis or {@code 0} or {@code -1}, depending on the cloud
	 *         if this permission does not have timeout or this cloud does not support permission timeouts
	 */
	long getTimeOutMillis();

	/**
	 * @throws UnsupportedCloudFeatureException
	 *         If this cloud does not support permission timeouts
	 */
	void setTimeOutMillis(long timeoutMillis);

}
