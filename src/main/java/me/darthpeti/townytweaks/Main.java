package me.darthpeti.townytweaks;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.palmergames.bukkit.towny.object.WorldCoord;
import me.darthpeti.townytweaks.towny.commands.TownyTweaks;
import me.darthpeti.townytweaks.towny.listeners.*;
import me.darthpeti.townytweaks.towny.listeners.Discord.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static Logger log = Bukkit.getLogger();
    public static final Cache<WorldCoord, Boolean> siegeZoneCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build();
    private File customConfigFile;
    private FileConfiguration customConfig;

    public static Main getInstance(){
        return instance;
    }

    public static String prefix;

    @Override
    public void onEnable() {
        instance = this;
        prefix = instance.getConfig().getString("townytweaks-prefix");
        createCustomConfig();
        loadConfig();
        registerListenersAndCommands();
    }

    public void createCustomConfig(){
        customConfigFile = new File(getDataFolder(), "webhook.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("webhook.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    public void registerListenersAndCommands(){
        PluginManager pM = instance.getServer().getPluginManager();

        if (getServer().getPluginManager().getPlugin("SiegeWar") != null) {
            pM.registerEvents(new KeepInvSiege(), instance);
            pM.registerEvents(new SiegeWarPearl(), instance);
            pM.registerEvents(new SiegeSessionEnd(getLogger()), instance);
            pM.registerEvents(new SiegeSessionStart(getLogger()), instance);
            pM.registerEvents(new SiegeStart(getLogger()), instance);
        }
        if (getServer().getPluginManager().getPlugin("Brewery") != null) {
            pM.registerEvents(new BrewRestrict(), instance);
        }
        pM.registerEvents(new ArmorStandRestriction(), instance);
        pM.registerEvents(new ShulkerRestriction(), instance);
        pM.registerEvents(new ShulkerRestrictionInteract(), instance);
        pM.registerEvents(new NewTown(getLogger()), instance);
        pM.registerEvents(new RuinedTown(getLogger()), instance);
        pM.registerEvents(new NewNation(getLogger()),instance);
        pM.registerEvents(new DeletedNation(getLogger()),instance);
        pM.registerEvents(new DeletedTown(getLogger()),instance);
        instance.getCommand("townytweaks").setExecutor(new TownyTweaks());
    }

    @Override
    public void onDisable() {

    }

    public void loadConfig(){
        instance.getConfig().options().copyDefaults(false);
        instance.saveDefaultConfig();
    }

}
