package me.darthpeti.townytweaks.towny.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.towny.util.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ShulkerRestriction implements Listener {
    @EventHandler
    public void onShulkerPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();
        if (ConfigUtil.shulkerRestriction()) {
            if (!TownyAPI.getInstance().isWilderness(location)) {
                if (!TownyAPI.getInstance().getTownBlock(location).getType().getName().equalsIgnoreCase(Main.getInstance().getConfig().getString("allow-shulkers-only-in-plottype"))) {
                    if (!event.getPlayer().hasPermission("townytweaks.admin.bypassshulkerbox")) {
                        if (event.getBlockPlaced().getState() instanceof ShulkerBox) {
                            event.setCancelled(true);
                            player.sendMessage(ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You can only use shulker boxes in " + Main.getInstance().getConfig().getString("allow-shulkers-only-in-plottype") + " plots.");
                        }
                    }
                }
            }
        }
    }
}
