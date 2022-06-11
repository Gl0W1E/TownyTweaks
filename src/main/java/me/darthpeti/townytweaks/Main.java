package me.darthpeti.townytweaks;

import me.darthpeti.townytweaks.Towny.ArmorStandRestriction;
import me.darthpeti.townytweaks.Towny.BrewRestrict;
import me.darthpeti.townytweaks.Towny.ShulkerRestriction;
import me.darthpeti.townytweaks.Towny.ShulkerRestrictionInteract;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        Main.instance = this;
    loadConfig();

    getServer().getPluginManager().registerEvents(new ArmorStandRestriction(), this);
    getServer().getPluginManager().registerEvents(new ShulkerRestriction(), this);
    getServer().getPluginManager().registerEvents(new ShulkerRestrictionInteract(), this);
    getServer().getPluginManager().registerEvents(new BrewRestrict(), this);
    }

    @Override
    public void onDisable() {

    }

    public void loadConfig(){
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }
}
