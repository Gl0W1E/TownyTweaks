package me.darthpeti.townytweaks.towny.listeners.Discord;

import com.gmail.goosius.siegewar.events.BattleSessionEndedEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.DiscordWebhook;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.logging.Logger;

public class SiegeSessionEnd implements Listener {

    private Logger logger;

    public SiegeSessionEnd(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onTown(BattleSessionEndedEvent event) {
        // siegewar-session-end
        String PrefixConfigName = "siegewar-session-end";
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

