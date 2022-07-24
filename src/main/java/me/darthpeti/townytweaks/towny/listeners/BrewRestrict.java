package me.darthpeti.townytweaks.towny.listeners;

import com.dre.brewery.api.events.barrel.BarrelCreateEvent;
import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.ConfigUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class BrewRestrict implements Listener {
    @EventHandler
    public void onCreateBarrel(BarrelCreateEvent event) {
        Player player = event.getPlayer();
        Location location = event.getSpigot().getLocation();
        if(ConfigUtil.brewBarrelRestrction()){
            if(!TownyAPI.getInstance().isWilderness(location) && !TownyAPI.getInstance().getTownBlock(event.getBarrel().getSpigot().getLocation()).getType().getName().equalsIgnoreCase(Main.getInstance().getConfig().getString("allow-barrels-only-in-plottype"))){
                event.setCancelled(true);
                player.sendMessage(Main.prefix + "§cYou can only create barrels in  §e" + ConfigUtil.allowBarrelsInPlotType().getName() + "§c plots.");
            }
        }
    }
}
