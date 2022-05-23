package net.anweisen.cloudapi.examples.wrapper;

import net.anweisen.cloudapi.driver.CloudDriver;
import net.anweisen.cloudapi.driver.event.EventListener;
import net.anweisen.cloudapi.driver.event.channel.ChannelMessageReceiveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ExampleBukkitPlugin extends JavaPlugin implements Listener {

	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		CloudDriver.getInstance().getEventManager().registerListener(this);
		CloudDriver.getInstance().getMessenger().registerChannel("example");
	}

	@EventListener
	public void onMessage(@Nonnull ChannelMessageReceiveEvent event) {
		System.err.println("event -> " + event);
	}

	@EventHandler
	public void onChat(@Nonnull AsyncPlayerChatEvent event) {
		CloudDriver.getInstance().getMessenger()
				.createMessage()
				.channel("example")
				.message("chat_event")
				.data(data -> data.set("t", System.currentTimeMillis()))
				.data(data -> data.set("some", "data"))
				.build()
				.sendQueryAsync().onComplete(messages -> System.err.println(messages));
	}

}
