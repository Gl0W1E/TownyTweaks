package me.darthpeti.townytweaks;

import me.darthpeti.townytweaks.Towny.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static Logger log = Bukkit.getLogger();
    public static String prefix = "§e[§bTownyTweaks§e]:§f ";


    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        registerListeners();
    }

    public void registerListeners(){
        instance.getServer().getPluginManager().registerEvents(new ArmorStandRestriction(), instance);
        instance.getServer().getPluginManager().registerEvents(new ShulkerRestriction(), instance);
        instance.getServer().getPluginManager().registerEvents(new ShulkerRestrictionInteract(), instance);
        instance.getServer().getPluginManager().registerEvents(new BrewRestrict(), instance);
        instance.getServer().getPluginManager().registerEvents(new KeepInvSiege(), instance);
        instance.getServer().getPluginManager().registerEvents(new SiegeWarPearl(), instance);
    }

    @Override
    public void onDisable() {
        instance.saveConfig();
    }

    public void loadConfig(){
        instance.getConfig().options().copyDefaults(false);
        instance.saveDefaultConfig();
    }


}
