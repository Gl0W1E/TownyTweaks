package me.darthpeti.townytweaks.Towny.listeners;

import com.gmail.goosius.siegewar.SiegeController;
import com.gmail.goosius.siegewar.objects.Siege;
import com.gmail.goosius.siegewar.utils.SiegeWarAllegianceUtil;
import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import me.darthpeti.townytweaks.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KeepInvSiege implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (Main.instance.getConfig().getString("keep-inventory-ing-sieges").equalsIgnoreCase("true")) {
            Location deathloc = event.getPlayer().getLocation();
            Player player = event.getPlayer();
            Resident resident = TownyUniverse.getInstance().getResident(player.getName());
            if (player.hasPermission("siegewar.nation.siege.battle.points") || player.hasPermission("siegewar.town.siege.battle.points")) {
                if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(deathloc)) {
                    Siege siege = SiegeController.getActiveSiegeAtLocation(deathloc);
                    if (SiegeWarAllegianceUtil.isPlayerOnTownFriendlySide(player, resident, siege) || SiegeWarAllegianceUtil.isPlayerOnTownHostileSide(player, resident, siege)) {
                        event.getDrops().clear();
                        event.setKeepInventory(true);
                    }
                }
            }
        }
    }
}