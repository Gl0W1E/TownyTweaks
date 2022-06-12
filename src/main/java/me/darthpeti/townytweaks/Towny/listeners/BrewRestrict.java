package me.darthpeti.townytweaks.Towny.listeners;

import com.dre.brewery.api.events.barrel.BarrelCreateEvent;
import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.Towny.util.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

import static me.darthpeti.townytweaks.Towny.util.ConfigUtil.armorStandOnlyInPlotType;

public class BrewRestrict implements Listener {
    @EventHandler
    public void onCreateBarrel(BarrelCreateEvent event) {
        Player player = event.getPlayer();
        Location location = event.getSpigot().getLocation();
        if(ConfigUtil.brewBarrelRestrction() && !player.hasPermission("townytweaks.admin.bypassbrew")){
            if(!TownyAPI.getInstance().isWilderness(location) && !Objects.requireNonNull(TownyAPI.getInstance().getTownBlock(location)).getType().equals(ConfigUtil.allowBarrelsInPlotType())){
                event.setCancelled(true);
                player.sendMessage(Main.prefix + "§cYou can only create barrels in  §e" + ConfigUtil.allowBarrelsInPlotType().getName() + "§c plots.");
            }
        }
    }
}
