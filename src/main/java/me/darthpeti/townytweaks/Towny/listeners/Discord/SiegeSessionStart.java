package me.darthpeti.townytweaks.Towny.listeners.Discord;

import com.gmail.goosius.siegewar.events.BattleSessionStartedEvent;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.Towny.util.DiscordWebhook;
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
        if (Main.instance.getCustomConfig().getString("notification-siegewar-session-start").equalsIgnoreCase("true")) {

            DiscordWebhook webhook = new DiscordWebhook(Main.instance.getCustomConfig().getString("webhook-url"));

            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setColor(new Color(255, 157, 0))
                    .setDescription("A new battle session has started!")
            );
            try {
                webhook.execute();
            } catch (java.io.IOException e) {
                logger.severe(e.getStackTrace().toString());
            }
        }
    }
}

