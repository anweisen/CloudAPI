package net.anweisen.cloudapi.examples.wrapper;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.EventListener;
import net.anweisen.cloudapi.driver.event.channel.ChannelMessageReceiveEvent;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ExampleBungeePlugin extends Plugin implements Listener {

	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, this);
		CloudDriver.getInstance().getEventManager().registerListener(this);
		CloudDriver.getInstance().getMessenger().registerChannel("example");

//		System.out.println("Component: " + CloudDriver.getInstance().getComponentName());
//		System.out.println("Node: " + CloudDriver.getInstance().getNodeName());
	}

	@EventListener
	public void onMessage(@Nonnull ChannelMessageReceiveEvent event) {
		System.out.println("Received Message " + event.getChannelMessage());
	}

	@EventHandler
	public void onChat(@Nonnull ChatEvent event) {
//		CloudDriver.getInstance()
//				.getMessenger()
//				.createMessage()
//				.channel("example")
//				.message("chat_event")
//				.data(data -> data.set("t", System.currentTimeMillis()))
//				.targetLocalNode()
//				.build()
//				.send();
	}

}
