package me.darthpeti.townytweaks.towny.listeners.Discord;

import com.palmergames.bukkit.towny.event.town.TownRuinedEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.DiscordWebhook;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.logging.Logger;

public class RuinedTown implements Listener {

    private Logger logger;

    public RuinedTown(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onTown(TownRuinedEvent event) {
        // town-ruins
        String PrefixConfigName = "town-ruins";
        FileConfiguration config = Main.getInstance().getCustomConfig();
        if (config.getString("notification-"+PrefixConfigName).equalsIgnoreCase("true")) {
            String townName = event.getTown().getName();
            DiscordWebhook webhook = new DiscordWebhook(config.getString("webhook-url"));
            String messageConfig = config.getString("message-" + PrefixConfigName).replace("{townName}", townName);
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

