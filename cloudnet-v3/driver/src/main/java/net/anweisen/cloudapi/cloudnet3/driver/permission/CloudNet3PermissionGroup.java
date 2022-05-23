package net.anweisen.cloudapi.cloudnet3.driver.permission;

import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import net.anweisen.cloudapi.driver.permission.PermissionGroup;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3PermissionGroup extends CloudNet3Permissible implements PermissionGroup {

	public final IPermissionGroup group;

	public CloudNet3PermissionGroup(@Nonnull IPermissionGroup group) {
		super(group);
		this.group = group;
	}

	@Override
	public int getSortId() {
		return group.getSortId();
	}

	@Override
	public void setSortId(int id) {
		group.setSortId(id);
	}

	@Override
	public boolean isDefaultGroup() {
		return group.isDefaultGroup();
	}

	@Override
	public void setDefaultGroup(boolean defaultGroup) {
		group.setDefaultGroup(defaultGroup);
	}

	@Nonnull
	@Override
	public String getPrefix() {
		return group.getPrefix();
	}

	@Override
	public void setPrefix(@Nonnull String prefix) {
		group.setPrefix(prefix);
	}

	@Nonnull
	@Override
	public String getSuffix() {
		return group.getSuffix();
	}

	@Override
	public void setSuffix(@Nonnull String suffix) {
		group.setSuffix(suffix);
	}

	@Nonnull
	@Override
	public String getColor() {
		return group.getColor();
	}

	@Override
	public void setColor(@Nonnull String color) {
		group.setColor(color);
	}

	@Nonnull
	@Override
	public String getDisplay() {
		return group.getDisplay();
	}

	@Override
	public void setDisplay(@Nonnull String display) {
		group.setDisplay(display);
	}
}
