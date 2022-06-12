package me.darthpeti.townytweaks.Towny.Listeners;

import com.dre.brewery.api.events.barrel.BarrelCreateEvent;
import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BrewRestrict implements Listener {
    @EventHandler
    public void onCreateBarrel(BarrelCreateEvent event) {
        Player player = event.getPlayer();
        Location location = event.getSpigot().getLocation();
        if(Main.instance.getConfig().getString("enable-brew-barrels-restriction").equalsIgnoreCase("true")) {
            if(!player.hasPermission("townytweaks.admin.bypassbrew")) {
                if(!TownyAPI.getInstance().isWilderness(location)) {
                    if(!TownyAPI.getInstance().getTownBlock(location).getType().getName().equalsIgnoreCase(Main.instance.getConfig().getString("allow-barrels-only-in-plottype"))) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You can only create barrels in " + Main.instance.getConfig().getString("allow-barrels-only-in-plottype") + " plots.");
                    }
                }
            }
        }
    }
}
