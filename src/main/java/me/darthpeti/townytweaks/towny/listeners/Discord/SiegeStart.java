package me.darthpeti.townytweaks.towny.listeners.Discord;

import com.gmail.goosius.siegewar.events.SiegeWarStartEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.DiscordWebhook;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.logging.Logger;

public class SiegeStart implements Listener {

    private Logger logger;

    public SiegeStart(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onTown(SiegeWarStartEvent event) {
         String PrefixConfigName = "siegewar-siege-start";
        FileConfiguration config = Main.getInstance().getCustomConfig();
        if (config.getString("notification-"+PrefixConfigName).equalsIgnoreCase("true")) {
            DiscordWebhook webhook = new DiscordWebhook(Main.getInstance().getCustomConfig().getString("webhook-url"));
            if (event.getTargetTown().hasNation()) {
                String attacker = event.getTownOfSiegeStarter().getName();
                String defender = event.getTargetTown().getNationOrNull().getName();
                String townName = event.getTargetTown().getName();
                String siegeType = event.getSiegeType();
                String messageConfig = config.getString("message-" + PrefixConfigName).replace("{attacker}", attacker).replace("{defender}", defender).replace("{townName}", townName).replace("{siegeType}", siegeType);
                if (config.getString("embed-" + PrefixConfigName).equalsIgnoreCase("true")) {
                    webhook.addEmbed(new DiscordWebhook.EmbedObject()
                            .setColor(new Color(255, 157, 0))
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
            } else {
                String attacker = event.getTownOfSiegeStarter().getName();
                String townName = event.getTargetTown().getName();
                String siegeType = event.getSiegeType();
                String messageConfig = config.getString("message-" + PrefixConfigName).replace("{attacker}", attacker).replace("{townName}", townName).replace("{siegeType}", siegeType);
                if (config.getString("embed-" + PrefixConfigName).equalsIgnoreCase("true")) {
                    webhook.addEmbed(new DiscordWebhook.EmbedObject()
                            .setColor(new Color(255, 157, 0))
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
}

