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
                Main.getInstance().saveConfig();
                if(sender instanceof Player){
                    sender.sendMessage(Main.prefix + "Configuration Reloaded.");
                    return true;
                } else {
                    Main.log.info("TownyTweaks Config Reloaded");
                    return true;
                }
            }
        }
        return false;
    }
}
