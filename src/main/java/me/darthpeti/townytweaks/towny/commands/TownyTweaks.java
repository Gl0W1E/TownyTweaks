package me.darthpeti.townytweaks.towny.commands;

import me.darthpeti.townytweaks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TownyTweaks implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            if(args[0].equalsIgnoreCase("reload")){
                Main.getInstance().reloadCustomConfig();
                if(sender instanceof Player){
                    sender.sendMessage(Main.prefix + "Configuration Reloaded., if no changes are visible, please restart the server or use plugman to reload the plugin.");
                    return true;
                } else {
                    Main.log.info("Configuration Reloaded., if no changes are visible, please restart the server or use plugman to reload the plugin.");
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("version")){
                if(sender instanceof Player){
                    sender.sendMessage(Main.prefix + "TownyTweaks Version: " + Main.getInstance().getDescription().getVersion());
                    return true;
                } else {
                    Main.log.info("TownyTweaks Version: " + Main.getInstance().getDescription().getVersion());
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("help")) {
                if (sender instanceof Player) {
                    sender.sendMessage(Main.prefix + "TownyTweaks Help:");
                    sender.sendMessage(Main.prefix + "/townytweaks reload - Reloads the configuration file.");
                    sender.sendMessage(Main.prefix + "/townytweaks version - Shows the version of the plugin.");
                    return true;
                } else {
                    Main.log.info("TownyTweaks Help:");
                    Main.log.info("/townytweaks reload - Reloads the configuration file.");
                    Main.log.info("/townytweaks version - Shows the version of the plugin.");
                    return true;
                }
            }
        }
        return false;
    }
}
