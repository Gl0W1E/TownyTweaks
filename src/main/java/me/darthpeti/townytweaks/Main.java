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
        instance.getServer().getPluginManager().registerEvents(new ArmorStandRestriction(), instance);
        instance.getServer().getPluginManager().registerEvents(new ShulkerRestriction(), instance);
        instance.getServer().getPluginManager().registerEvents(new ShulkerRestrictionInteract(), instance);
        instance.getServer().getPluginManager().registerEvents(new BrewRestrict(), instance);
        instance.getServer().getPluginManager().registerEvents(new KeepInvSiege(), instance);
        instance.getServer().getPluginManager().registerEvents(new SiegeWarPearl(), instance);
        instance.getServer().getPluginManager().registerEvents(new NewTown(getLogger()), instance);
        instance.getServer().getPluginManager().registerEvents(new RuinedTown(getLogger()), instance);
        instance.getServer().getPluginManager().registerEvents(new NewNation(getLogger()),instance);
        instance.getServer().getPluginManager().registerEvents(new DeletedNation(getLogger()),instance);
        instance.getServer().getPluginManager().registerEvents(new DeletedTown(getLogger()),instance);
        instance.getServer().getPluginManager().registerEvents(new SiegeSessionEnd(getLogger()), instance);
        instance.getServer().getPluginManager().registerEvents(new SiegeSessionStart(getLogger()), instance);
        instance.getServer().getPluginManager().registerEvents(new SiegeStart(getLogger()), instance);
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
