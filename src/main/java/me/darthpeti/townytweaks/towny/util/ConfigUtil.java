package me.darthpeti.townytweaks.towny.util;

import com.palmergames.bukkit.towny.object.TownBlockType;
import me.darthpeti.townytweaks.Main;

public class ConfigUtil {

    public static boolean armorStandRestriction() {
        return Main.getInstance().getConfig().getBoolean("enable-armorstand-restriction");
    }

    public static TownBlockType armorStandOnlyInPlotType(){
        String plotType = Main.getInstance().getConfig().getString("allow-armorstand-only-in-plottype");
        return checkPlot(plotType);
    }

    public static boolean shulkerRestriction() {
        return Main.getInstance().getConfig().getBoolean("enable-shulker-restriction");
    }

    public static TownBlockType allowShulkersInPlotType(){
        String plotType = Main.getInstance().getConfig().getString("allow-shulkers-only-in-plottype");
        return checkPlot(plotType);
    }

    public static boolean brewBarrelRestrction(){
        return Main.getInstance().getConfig().getBoolean("enable-brew-barrels-restriction");
    }

    public static TownBlockType allowBarrelsInPlotType(){
        String plotType = Main.getInstance().getConfig().getString("allow-barrels-only-in-plottype");
        return checkPlot(plotType);
    }

    public static boolean keepInventoryInSiege(){
        return Main.getInstance().getConfig().getBoolean("keep-inventory-in-sieges");
    }

    public static boolean preventSpawnTrapInCapitals(){
        return Main.getInstance().getConfig().getBoolean("spawn-trap-prevention-in-capitals");
    }

    public static boolean allowPearlsInBesiegedTowns(){
        return Main.getInstance().getConfig().getBoolean("allow-pearl-in-besieged-towns");
    }

    public static TownBlockType checkPlot(String plotTypeName){
        //ARENA,BANK,COMMERCIAL,EMBASSY,FARM,INN,JAIL,RESIDENTIAL,WILDS
        if(plotTypeName.equalsIgnoreCase("ARENA")){
            return TownBlockType.ARENA;
        } else if(plotTypeName.equalsIgnoreCase("BANK")){
            return TownBlockType.BANK;
        } else if(plotTypeName.equalsIgnoreCase("COMMERCIAL")){
            return TownBlockType.COMMERCIAL;
        } else if(plotTypeName.equalsIgnoreCase("EMBASSY")){
            return TownBlockType.EMBASSY;
        } else if(plotTypeName.equalsIgnoreCase("FARM")){
            return TownBlockType.FARM;
        } else if(plotTypeName.equalsIgnoreCase("INN")){
            return TownBlockType.INN;
        } else if(plotTypeName.equalsIgnoreCase("JAIL")){
            return TownBlockType.JAIL;
        } else if(plotTypeName.equalsIgnoreCase("RESIDENTIAL")){
            return TownBlockType.RESIDENTIAL;
        } else if(plotTypeName.equalsIgnoreCase("WILDS")){
            return TownBlockType.WILDS;
        }
        return null;
    }
}
