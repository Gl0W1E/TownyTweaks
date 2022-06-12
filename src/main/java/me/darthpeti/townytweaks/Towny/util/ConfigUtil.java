package me.darthpeti.townytweaks.Towny.util;

import com.palmergames.bukkit.towny.object.TownBlockType;
import me.darthpeti.townytweaks.Main;

public class ConfigUtil {

    public static boolean armorStandRestriction() {
        return Main.instance.getConfig().getBoolean("enable-armorstand-restriction");
    }

    public static TownBlockType armorStandOnlyInPlotType(){
        //ARENA,BANK,COMMERCIAL,EMBASSY,FARM,INN,JAIL,RESIDENTIAL,WILDS
        String plotType = Main.instance.getConfig().getString("allow-armorstand-only-in-plottype");
        if(plotType.equalsIgnoreCase("ARENA")){
            return TownBlockType.ARENA;
        } else if(plotType.equalsIgnoreCase("BANK")){
            return TownBlockType.BANK;
        } else if(plotType.equalsIgnoreCase("COMMERCIAL")){
            return TownBlockType.COMMERCIAL;
        } else if(plotType.equalsIgnoreCase("EMBASSY")){
            return TownBlockType.EMBASSY;
        } else if(plotType.equalsIgnoreCase("FARM")){
            return TownBlockType.FARM;
        } else if(plotType.equalsIgnoreCase("INN")){
            return TownBlockType.INN;
        } else if(plotType.equalsIgnoreCase("JAIL")){
            return TownBlockType.JAIL;
        } else if(plotType.equalsIgnoreCase("RESIDENTIAL")){
            return TownBlockType.RESIDENTIAL;
        } else if(plotType.equalsIgnoreCase("WILDS")){
            return TownBlockType.WILDS;
        }
        return null;
    }
}
