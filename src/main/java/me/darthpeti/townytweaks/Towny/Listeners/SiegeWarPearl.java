package me.darthpeti.townytweaks.Towny.Listeners;

import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import com.palmergames.bukkit.towny.event.actions.TownyItemuseEvent;
import me.darthpeti.townytweaks.Towny.util.LocationUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SiegeWarPearl implements Listener {
    @EventHandler
    public void townyItemuseEvent (TownyItemuseEvent event) {
        //Setup this way to check Ender_Pearl first so we don't check every PlayerTeleport location and Pearl Status.
        if(event.getMaterial().equals(Material.ENDER_PEARL)){
            if(SiegeWarDistanceUtil.isLocationInActiveSiegeZone(event.getLocation())){
                event.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void playerTeleportEvent(PlayerTeleportEvent event){
        //Setup this way to check Ender_Pearl first so we don't check every PlayerTeleport location and Pearl Status.
        if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            if((SiegeWarDistanceUtil.isLocationInActiveSiegeZone(event.getPlayer().getLocation()))){
                if(!LocationUtil.isSafe(event.getTo())){
                    event.setCancelled(true);
                }
            }
        }
    }
}
