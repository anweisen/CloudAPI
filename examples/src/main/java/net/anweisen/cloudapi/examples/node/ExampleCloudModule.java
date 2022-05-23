package net.anweisen.cloudapi.examples.node;

import net.anweisen.cloudapi.driver.event.Event;
import net.anweisen.cloudapi.driver.event.channel.ChannelMessageReceiveEvent;
import net.anweisen.cloudapi.driver.event.player.PlayerEvent;
import net.anweisen.cloudapi.node.module.CloudModule;
import net.anweisen.utilities.common.config.Document;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ExampleCloudModule extends CloudModule {

	@Override
	protected void onLoad() {
		getLogger().error(getDriver().getCloudImplementation());
//		getEventManager().on(PlayerEvent.class, event -> getLogger().error(event + " | " + event.getPlayer()));
//		getEventManager().on(Event.class, event -> getLogger().error(event));

		getEventManager().on(ChannelMessageReceiveEvent.class, event -> {
			if (!event.getChannel().equals("example")) return;

			System.err.println(event);
			event.setJsonResponse(Document.newJsonDocument().set("some", "data"));
		});
	}

	@Override
	protected void onEnable() {
	}

	@Override
	protected void onDisable() {
	}

}
