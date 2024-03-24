package me.darthpeti.townytweaks.towny.listeners.Discord;

import com.palmergames.bukkit.towny.event.DeleteNationEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.DiscordWebhook;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.logging.Logger;

public class DeletedNation implements Listener {

    private Logger logger;

    public DeletedNation(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onTown(DeleteNationEvent event) {
        String PrefixConfigName = "nation-disband";
        FileConfiguration config = Main.getInstance().getCustomConfig();
        if (config.getString("notification-"+PrefixConfigName).equalsIgnoreCase("true")) {
            String nationName = event.getNationName();
            DiscordWebhook webhook = new DiscordWebhook(config.getString("webhook-url"));
            String messageConfig = config.getString("message-" + PrefixConfigName).replace("{nationName}", nationName);
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

