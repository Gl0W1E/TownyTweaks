package me.darthpeti.townytweaks.Towny.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.damage.TownyPlayerDamagePlayerEvent;
import com.palmergames.bukkit.towny.object.WorldCoord;
import me.darthpeti.townytweaks.Main;
import me.darthpeti.townytweaks.Towny.util.ConfigUtil;
import me.darthpeti.townytweaks.Towny.util.LocationUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class SpawnTrapPrevention implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerDamagePlayer(TownyPlayerDamagePlayerEvent event){
        if(ConfigUtil.preventSpawnTrapInCapitals()){
            if(!event.isInWilderness() && LocationUtil.isCloseToNationSpawn(event.getLocation()) && LocationUtil.notInSiegeZone(Objects.requireNonNull(event.getTownBlock()).getWorldCoord()))
                event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if(TownyAPI.getInstance().isWilderness(event.getEntity().getLocation()))
            return;
        if(Main.instance.getConfig().getString("spawn-trap-prevention-in-capital").equalsIgnoreCase("true")){
            if (LocationUtil.isCloseToNationSpawn(event.getEntity().getLocation()) && LocationUtil.notInSiegeZone(WorldCoord.parseWorldCoord(event.getEntity())))
                event.setKeepInventory(true);
                event.setKeepLevel(true);
                event.getDrops().clear();
                event.setDroppedExp(0);
        }
    }
}
