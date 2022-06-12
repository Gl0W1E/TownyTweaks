package me.darthpeti.townytweaks.Towny.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.Towny.util.ConfigUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

import java.util.Objects;

import static me.darthpeti.townytweaks.Towny.util.ConfigUtil.armorStandOnlyInPlotType;

public class ArmorStandRestriction implements Listener {
    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        Location armorstandLoc = event.getRightClicked().getLocation();
        Player player = event.getPlayer();

        if (ConfigUtil.armorStandRestriction()) {
            if (!TownyAPI.getInstance().isWilderness(armorstandLoc)) {
                if (!player.hasPermission("townytweaks.admin.bypassarmorstand")) {
                    if(!Objects.equals(Objects.requireNonNull(TownyAPI.getInstance().getTownBlock(armorstandLoc)).getType(), armorStandOnlyInPlotType())){
                        event.setCancelled(true);
                        player.sendMessage(Main.prefix + "§cYou can only use armor stands in  §e" + armorStandOnlyInPlotType().getName() + "§c plots.");
                    }
                }
            }
        }
    }
}
