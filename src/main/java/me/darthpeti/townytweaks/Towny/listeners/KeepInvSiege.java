package me.darthpeti.townytweaks.Towny.listeners;

import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.Towny.util.ConfigUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KeepInvSiege implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if(ConfigUtil.keepInventoryInSiege()) {
            Location deathloc = event.getPlayer().getLocation();
            Player player = event.getPlayer();
            if(player.hasPermission("siegewar.nation.siege.battle.points") || player.hasPermission("siegewar.town.siege.battle.points")){
                if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(deathloc)) {
                    event.setKeepInventory(true);
                }
            }
        }
    }
}