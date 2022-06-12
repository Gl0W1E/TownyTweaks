package me.darthpeti.townytweaks.Towny.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ArmorStandRestriction implements Listener {
    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        Location armorstand = event.getRightClicked().getLocation();
        Player player = event.getPlayer();

        if (Main.instance.getConfig().getString("enable-armorstand-restriction").equalsIgnoreCase("true")) {
            if (!TownyAPI.getInstance().isWilderness(armorstand)) {
                if (!TownyAPI.getInstance().getTownBlock(armorstand).getType().getName().equalsIgnoreCase(Main.instance.getConfig().getString("allow-armorstand-only-in-plottype"))) {
                    if (!player.hasPermission("townytweaks.admin.bypassarmorstand")) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You can only use armor stands in " + Main.instance.getConfig().getString("allow-armorstand-only-in-plottype") + " plots.");
                    }
                }
            }
        }
    }
}
