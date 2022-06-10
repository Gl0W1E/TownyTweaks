package me.darthpeti.townytweaks.Towny;

import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShulkerRestrictionInteract implements Listener {
    @EventHandler
    public void onInteractWithShulk(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if(block instanceof ShulkerBox) {
            if(!TownyAPI.getInstance().isWilderness(block.getLocation())) {
                if(!TownyAPI.getInstance().getTownBlock(block.getLocation()).getType().getName().equalsIgnoreCase(Main.instance.getConfig().getString("allow-shulkers-only-in-plottype"))) {
                    if(!player.hasPermission("townytweaks.admin.bypassshulkerbox")) {
                        if(Main.instance.getConfig().getString("enable-shulker-restriction").equalsIgnoreCase("true")) {
                            event.setCancelled(true);
                            player.sendMessage(ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You can only use shulker boxes in " + Main.instance.getConfig().getString("allow-shulkers-only-in-plottype") + " plots.");
                        }
                    }
                }
            }
        }
    }
}
