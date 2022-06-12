package me.darthpeti.townytweaks.Towny.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.Towny.util.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

import static me.darthpeti.townytweaks.Towny.util.ConfigUtil.armorStandOnlyInPlotType;

public class ShulkerRestrictionInteract implements Listener {

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            Material type = Objects.requireNonNull(event.getClickedBlock()).getType();
            if (isClickableBlock(type)) {
                if (!player.hasPermission("townytweaks.admin.bypassshulkerbox")) {
                    if(!Objects.equals(Objects.requireNonNull(TownyAPI.getInstance().getTownBlock(event.getClickedBlock().getLocation())).getType(), armorStandOnlyInPlotType())){
                        event.setCancelled(true);
                        player.sendMessage(Main.prefix + "§cYou can only use shulker boxes in §e" + armorStandOnlyInPlotType().getName() + "§c plots.");
                    }
                }
            }
        }
    }

    public static boolean isClickableBlock(Material type) {
        if (type == null || !type.isBlock()) {
            assert type != null;
            return type.toString().endsWith("SHULKER_BOX");
        }
        return false;
    }
}
