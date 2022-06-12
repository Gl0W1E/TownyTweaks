package me.darthpeti.townytweaks.Towny.util;

import com.gmail.goosius.siegewar.SiegeController;
import com.gmail.goosius.siegewar.objects.Siege;
import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.WorldCoord;
import me.darthpeti.townytweaks.Main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class LocationUtil {

    public static boolean isSafe(Location location) {
        // Ensure the player's feet aren't in a block
        Block feet = location.getBlock();
        if (!feet.getType().isAir()) {
            return false; // not transparent (will suffocate)
        }

        // Ensure the player's head isn't in a block
        Block head = feet.getRelative(BlockFace.UP);
        if (!head.getType().isAir()) {
            return false; // not transparent (will suffocate)
        }

        // Ensure the block under the player is solid
        Block ground = feet.getRelative(BlockFace.DOWN);
        if (!ground.getType().isSolid()) {
            return false; // not solid
        }
        return true;
    }

    public static boolean isCloseToNationSpawn(Location location) {
        return isCloseToNationSpawn(WorldCoord.parseWorldCoord(location));
    }

    public static boolean isCloseToNationSpawn(WorldCoord worldCoord) {
        for (WorldCoord coord : selectArea(worldCoord)) {
            Town town = coord.getTownOrNull();
            try {
                if (town != null && town.isCapital() && WorldCoord.parseWorldCoord(Objects.requireNonNull(town.getNationOrNull()).getSpawn()).equals(coord))
                    return true;
            } catch (TownyException ignored) {}
        }
        return false;
    }

    public static Set<WorldCoord> selectArea(WorldCoord centre) {
        Set<WorldCoord> coords = new HashSet<>();

        coords.add(centre.add(-1, -1));
        coords.add(centre.add(-1, 0));
        coords.add(centre.add(-1, 1));
        coords.add(centre.add(0, -1));
        coords.add(centre);
        coords.add(centre.add(0, 1));
        coords.add(centre.add(1, -1));
        coords.add(centre.add(1, 0));
        coords.add(centre.add(1, 1));

        return coords;
    }

    private static Location toLocation(WorldCoord coord) {
        int tbSize = TownySettings.getTownBlockSize();
        return new Location(coord.getBukkitWorld(), coord.getX() * tbSize, 64, coord.getZ() * tbSize);
    }

    public static Siege getActiveSiegeInLocation(Location location) {
        for (Siege siege : SiegeController.getSieges()) {
            if (siege.getStatus().isActive() && SiegeWarDistanceUtil.isInSiegeZone(location, siege)) {
                return siege;
            }
        }
        return null;
    }

    public static boolean notInSiegeZone(WorldCoord worldCoord) {
        try {
            return Main.instance.siegeZoneCache.get(worldCoord, () -> !SiegeWarDistanceUtil.isLocationInActiveSiegeZone(toLocation(worldCoord)));
        } catch (ExecutionException e) {
            e.printStackTrace();
            return !SiegeWarDistanceUtil.isLocationInActiveSiegeZone(toLocation(worldCoord));
        }
    }
}