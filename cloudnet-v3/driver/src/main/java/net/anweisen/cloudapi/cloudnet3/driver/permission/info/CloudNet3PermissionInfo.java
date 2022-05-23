package net.anweisen.cloudapi.cloudnet3.driver.permission.info;

import de.dytanic.cloudnet.driver.permission.Permission;
import net.anweisen.cloudapi.driver.permission.info.PermissionInfo;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PermissionInfo implements PermissionInfo {

	public final Permission permission;

	public CloudNet3PermissionInfo(@Nonnull Permission permission) {
		this.permission = permission;
	}

	@Nonnull
	@Override
	public String getName() {
		return permission.getName();
	}

	@Override
	public int getPotency() {
		return permission.getPotency();
	}

	@Override
	public void setPotency(int potency) {
		permission.setPotency(potency);
	}

	@Override
	public long getTimeOutMillis() {
		return permission.getTimeOutMillis();
	}

	@Override
	public void setTimeOutMillis(long timeoutMillis) {
		permission.setTimeOutMillis(timeoutMillis);
	}

	@Override
	public int compareTo(@Nonnull PermissionInfo o) {
		return permission.compareTo(((CloudNet3PermissionInfo)o).permission);
	}
}
