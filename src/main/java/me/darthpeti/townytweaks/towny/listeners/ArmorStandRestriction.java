package me.darthpeti.townytweaks.towny.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.ConfigUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ArmorStandRestriction implements Listener {
    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        Location armorstandLoc = event.getRightClicked().getLocation();
        Player player = event.getPlayer();

        if (ConfigUtil.armorStandRestriction()) {
            if (!TownyAPI.getInstance().isWilderness(armorstandLoc)) {
                if (!player.hasPermission("townytweaks.admin.bypassarmorstand")) {
                    if(TownyAPI.getInstance().getTownBlock(event.getRightClicked().getLocation()).getType().getName() == Main.getInstance().getConfig().getString("allow-armorstand-only-in-plottype")){
                        event.setCancelled(true);
                        player.sendMessage(Main.prefix + "§cYou can only use armor stands in  §e" + ConfigUtil.armorStandOnlyInPlotType().getName() + "§c plots.");
                    }
                }
            }
        }
    }
}
