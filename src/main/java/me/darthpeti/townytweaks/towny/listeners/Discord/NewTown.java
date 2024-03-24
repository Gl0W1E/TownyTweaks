package me.darthpeti.townytweaks.towny.listeners.Discord;

import com.palmergames.bukkit.towny.event.NewTownEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.DiscordWebhook;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.logging.Logger;

public class NewTown implements Listener {

    private Logger logger;

    public NewTown(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onTown(NewTownEvent event) {
        String PrefixConfigName = "town-creation";
        FileConfiguration config = Main.getInstance().getCustomConfig();
        if (config.getString("notification-"+PrefixConfigName).equalsIgnoreCase("true")) {
            String townName = event.getTown().getName();
            String mayorName = event.getTown().getMayor().getName();
            DiscordWebhook webhook = new DiscordWebhook(config.getString("webhook-url"));
            String messageConfig = config.getString("message-" + PrefixConfigName).replace("{townName}", townName).replace("{mayorName}", mayorName);
            
            if (config.getString("embed-" + PrefixConfigName).equalsIgnoreCase("true")) {
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setColor(new Color(0, 81, 255))
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
