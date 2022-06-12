package me.darthpeti.townytweaks.Towny.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

import static me.darthpeti.townytweaks.Towny.util.ConfigUtil.allowShulkersInPlotType;

public class ShulkerRestrictionInteract implements Listener {

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            Material type = Objects.requireNonNull(event.getClickedBlock()).getType();
            if (isClickableBlock(type)) {
                if (!player.hasPermission("townytweaks.admin.bypassshulkerbox")) {
                    if(!Objects.equals(Objects.requireNonNull(TownyAPI.getInstance().getTownBlock(event.getClickedBlock().getLocation())).getType(), allowShulkersInPlotType())){
                        event.setCancelled(true);
                        player.sendMessage(Main.prefix + "§cYou can only use shulker boxes in §e" + allowShulkersInPlotType().getName() + "§c plots.");
                    }
                }
            }
        }
    }

    public static boolean isClickableBlock(Material type) {
        return type.toString().endsWith("SHULKER_BOX");
    }
}
