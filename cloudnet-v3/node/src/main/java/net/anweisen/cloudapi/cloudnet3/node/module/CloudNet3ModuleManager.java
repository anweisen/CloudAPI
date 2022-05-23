package net.anweisen.cloudapi.cloudnet3.node.module;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import net.anweisen.cloudapi.node.module.DefaultModuleManager;
import net.anweisen.cloudapi.node.module.ModuleController;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ModuleManager extends DefaultModuleManager {

	public CloudNet3ModuleManager(@Nonnull File modulesFolder) {
		super(modulesFolder);
	}

	@Nonnull
	@Override
	public Collection<ModuleController> getWrappedModules() {
		return Collections.unmodifiableCollection(
			CloudNetDriver.getInstance().getModuleProvider().getModules().stream().map(wrapper -> new CloudNet3WrappedModuleController(wrapper, this)).collect(Collectors.toList())
		);
	}

}
