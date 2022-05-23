package net.anweisen.cloudapi.cloudnet3.node.commands;

import de.dytanic.cloudnet.command.sub.SubCommandBuilder;
import de.dytanic.cloudnet.command.sub.SubCommandHandler;
import net.anweisen.cloudapi.cloudnet3.node.launcher.CloudModule;
import net.anweisen.cloudapi.driver.cloud.CloudImplementationInfo;
import net.anweisen.cloudapi.node.module.ModuleConfig;
import net.anweisen.cloudapi.node.module.ModuleController;
import net.anweisen.cloudapi.node.module.ModuleState;

import javax.annotation.Nonnull;

import java.util.Collection;

import static de.dytanic.cloudnet.command.sub.SubCommandArgumentTypes.*;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CloudNet3ApiCommand extends SubCommandHandler {

	public CloudNet3ApiCommand(@Nonnull CloudModule module) {
		super(
			SubCommandBuilder.create()
				.generateCommand(
					(subCommand, sender, command, args, commandLine, properties, internalProperties) -> {
						CloudImplementationInfo cloud = module.getCloud().getCloudImplementation();

						sender.sendMessage("");
						sender.sendMessage("CloudAPI v" + cloud.getApiVersion() + ": " + cloud.getName() + " native v" + cloud.getNativeCloudVersion());
						sender.sendMessage("");
					},
					anyStringIgnoreCase("info")
				)
				.prefix(anyStringIgnoreCase("modules", "module"))
				.generateCommand(
					(subCommand, sender, command, args, commandLine, properties, internalProperties) -> {
						sender.sendMessage("Reloading all api modules..");
						module.getCloud().getModuleManager().reloadModules();
						sender.sendMessage("All api modules were reloaded");
					},
					anyStringIgnoreCase("reload")
				)
				.generateCommand(
					(subCommand, sender, command, args, commandLine, properties, internalProperties) -> {
						Collection<ModuleController> modules = module.getCloud().getModuleManager().getApiModules();
						for (ModuleController controller : modules) {
							ModuleConfig config = controller.getModuleConfig();
							sender.sendMessage(config.getFullName() + " by " + config.getAuthor() + " | " + config.getJarFile() + " | " + controller.getState());
						}

						sender.sendMessage("=> Showing " + modules.size() + " module(s); Enabled: " +  module.getCloud().getModuleManager().getApiModules(ModuleState.ENABLED).size());
					},
					anyStringIgnoreCase("list", "l")
				)
				.clearPrefixes()

			.getSubCommands(),
			"api"
		);

		prefix = "cloudapi";
		permission = "cloudapi.command";
	}
}
