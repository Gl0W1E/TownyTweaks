package me.darthpeti.townytweaks.Towny.commands;

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
                Main.instance.reloadConfig();
                if(sender instanceof Player){
                    sender.sendMessage(Main.prefix + "Configuration Reloaded.");
                } else {
                    Main.log.info("Configuration Reloaded");
                }
            } else if(args[0].equalsIgnoreCase("set")) {
                if(args[1].equalsIgnoreCase("enable-armorstand-restriction")){
                    if(args.length > 2) {
                        Main.instance.getConfig().set("enable-armorstand-restriction", args[2].toLowerCase());
                        Main.instance.saveConfig();
                        Main.instance.reloadConfig();
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§eenable-armorstand-restriction has been set to §b" + args[2].toLowerCase());
                        Main.log.info(Main.prefix + "§eenable-armorstand-restriction has been set to §b" + args[2].toLowerCase());
                    } else {
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§e/townytweaks set enable-armorstand-restriction [§atrue§e/§cfalse§e]");
                        Main.log.info(Main.prefix + "§e/townytweaks set enable-armorstand-restriction [§atrue§e/§cfalse§e]");
                    }
                } else if(args[1].equalsIgnoreCase("allow-armorstand-only-in-plottype")) {
                    if(args.length > 2) {
                        Main.instance.getConfig().set("allow-armorstand-only-in-plottype", args[2].toLowerCase());
                        Main.instance.saveConfig();
                        Main.instance.reloadConfig();
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§eallow-armorstand-only-in-plottype has been set to §b" + args[2].toLowerCase());
                        Main.log.info(Main.prefix + "§eallow-armorstand-only-in-plottype has been set to §b" + args[2].toLowerCase());
                    } else {
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§e/townytweaks set allow-armorstand-only-in-plottype [§aARENA,BANK,COMMERCIAL,EMBASSY,FARM,INN,JAIL,RESIDENTIAL,WILDS§e]");
                        Main.log.info(Main.prefix + "§e/townytweaks set allow-armorstand-only-in-plottype [§aARENA,BANK,COMMERCIAL,EMBASSY,FARM,INN,JAIL,RESIDENTIAL,WILDS§e]");
                    }
                } else if(args[1].equalsIgnoreCase("enable-shulker-restriction")) {
                    if(args.length > 2) {
                        Main.instance.getConfig().set("enable-shulker-restriction", args[2].toLowerCase());
                        Main.instance.saveConfig();
                        Main.instance.reloadConfig();
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§eenable-shulker-restriction has been set to §b" + args[2].toLowerCase());
                        Main.log.info(Main.prefix + "§eenable-shulker-restriction has been set to §b" + args[2].toLowerCase());
                    } else {
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§e/townytweaks set enable-shulker-restriction [§atrue§e/§cfalse§e]");
                        Main.log.info(Main.prefix + "§e/townytweaks set enable-shulker-restriction [§atrue§e/§cfalse§e]");
                    }
                } else if(args[1].equalsIgnoreCase("enable-brew-barrels-restriction")) {
                    if(args.length > 2) {
                        Main.instance.getConfig().set("enable-brew-barrels-restriction", args[2].toLowerCase());
                        Main.instance.saveConfig();
                        Main.instance.reloadConfig();
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§eenable-brew-barrels-restriction has been set to §b" + args[2].toLowerCase());
                        Main.log.info(Main.prefix + "§eenable-brew-barrels-restriction has been set to §b" + args[2].toLowerCase());
                    } else {
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§e/townytweaks set enable-brew-barrels-restriction [§atrue§e/§cfalse§e]");
                        Main.log.info(Main.prefix + "§e/townytweaks set enable-brew-barrels-restriction [§atrue§e/§cfalse§e]");
                    }
                } else if(args[1].equalsIgnoreCase("allow-barrels-only-in-plottype")) {
                    if(args.length > 2) {
                        Main.instance.getConfig().set("allow-barrels-only-in-plottype", args[2].toLowerCase());
                        Main.instance.saveConfig();
                        Main.instance.reloadConfig();
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§eallow-barrels-only-in-plottype has been set to §b" + args[2].toLowerCase());
                        Main.log.info(Main.prefix + "§eallow-barrels-only-in-plottype has been set to §b" + args[2].toLowerCase());
                    } else {
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§e/townytweaks set allow-barrels-only-in-plottype [§aARENA,BANK,COMMERCIAL,EMBASSY,FARM,INN,JAIL,RESIDENTIAL,WILDS§e]");
                        Main.log.info(Main.prefix + "§e/townytweaks set allow-barrels-only-in-plottype [§aARENA,BANK,COMMERCIAL,EMBASSY,FARM,INN,JAIL,RESIDENTIAL,WILDS§e]");
                    }
                } else if(args[1].equalsIgnoreCase("keep-inventory-in-sieges")) {
                    if(args.length > 2) {
                        Main.instance.getConfig().set("keep-inventory-in-sieges", args[2].toLowerCase());
                        Main.instance.saveConfig();
                        Main.instance.reloadConfig();
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§ekeep-inventory-in-sieges has been set to §b" + args[2].toLowerCase());
                        Main.log.info(Main.prefix + "§ekeep-inventory-in-sieges has been set to §b" + args[2].toLowerCase());
                    } else {
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§e/townytweaks set keep-inventory-in-sieges [§atrue§e/§cfalse§e]");
                        Main.log.info(Main.prefix + "§e/townytweaks set keep-inventory-in-sieges [§atrue§e/§cfalse§e]");
                    }
                }
                else if(args[1].equalsIgnoreCase("allow-pearls-in-besieged-towns")) {
                    if(args.length > 2) {
                        Main.instance.getConfig().set("allow-pearls-in-besieged-towns", args[2].toLowerCase());
                        Main.instance.saveConfig();
                        Main.instance.reloadConfig();
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§eallow-pearls-in-besieged-towns has been set to §b" + args[2].toLowerCase());
                        Main.log.info(Main.prefix + "§eallow-pearls-in-besieged-towns has been set to §b" + args[2].toLowerCase());
                    } else {
                        if(sender instanceof Player)
                            sender.sendMessage(Main.prefix + "§e/townytweaks set allow-pearls-in-besieged-towns [§atrue§e/§cfalse§e]");
                        Main.log.info(Main.prefix + "§e/townytweaks set allow-pearls-in-besieged-towns [§atrue§e/§cfalse§e]");
                    }
                }
            }
        }
        return false;
    }
}
