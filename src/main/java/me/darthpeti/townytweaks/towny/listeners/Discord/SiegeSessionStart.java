package me.darthpeti.townytweaks.towny.listeners.Discord;

import com.gmail.goosius.siegewar.events.BattleSessionStartedEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.DiscordWebhook;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.logging.Logger;

public class SiegeSessionStart implements Listener {

    private Logger logger;

    public SiegeSessionStart(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onTown(BattleSessionStartedEvent event) {
        String PrefixConfigName = "siegewar-session-start";
        FileConfiguration config = Main.getInstance().getCustomConfig();
        if (config.getString("notification-"+PrefixConfigName).equalsIgnoreCase("true")) {
            DiscordWebhook webhook = new DiscordWebhook(Main.getInstance().getCustomConfig().getString("webhook-url"));
            String messageConfig = config.getString("message-" + PrefixConfigName);
            if (config.getString("embed-" + PrefixConfigName).equalsIgnoreCase("true")) {
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setColor(new Color(214, 99, 84))
                        .setDescription(messageConfig)
                );
            } else {
                webhook.setContent(messageConfig);
            }
            try {
                webhook.execute();
            } catch (java.io.IOException e) {
                logger.severe(e.getStackTrace().toString());
            }
        }
    }
}

