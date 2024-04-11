package me.darthpeti.townytweaks.towny.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args){
        if(args.length == 1 && sender.hasPermission("townytweaks.admin")) {
            return List.of("reload", "version");
        }
        return null;
    }
}