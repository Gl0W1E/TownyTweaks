package me.darthpeti.townytweaks.Towny.listeners.Discord;

import com.gmail.goosius.siegewar.events.SiegeWarStartEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.Towny.util.DiscordWebhook;
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
        if (Main.instance.getCustomConfig().getString("notification-siegewar-session-start").equalsIgnoreCase("true")) {
            if (event.getTargetTown().hasNation()) {
                DiscordWebhook webhook = new DiscordWebhook(Main.instance.getCustomConfig().getString("webhook-url"));

                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setColor(new Color(255, 157, 0))
                        .setDescription("An army belonging to " + event.getTownOfSiegeStarter().getName() + " has attacked " + event.getTargetTown().getNationOrNull().getName() + " at " + event.getTargetTown().getName() + "! A " + event.getSiegeType() + " siege has begun!")
                );
                try {
                    webhook.execute();
                } catch (java.io.IOException e) {
                    logger.severe(e.getStackTrace().toString());
                }
            } else {
                DiscordWebhook webhook = new DiscordWebhook(Main.instance.getCustomConfig().getString("webhook-url"));

                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setColor(new Color(255, 157, 0))
                        .setDescription("An army belonging to " + event.getTownOfSiegeStarter().getName() + " has attacked " + event.getTargetTown().getName() + "! A " + event.getSiegeType() + " siege has begun!")
                );
                try {
                    webhook.execute();
                } catch (java.io.IOException e) {
                    logger.severe(e.getStackTrace().toString());
                }
            }
        }
    }
}

