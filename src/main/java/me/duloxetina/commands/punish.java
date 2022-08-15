package me.duloxetina.commands;

import me.duloxetina.FileHandler.messages;
import me.duloxetina.nPunishment;
import me.duloxetina.inventories.gui;
import me.duloxetina.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class punish implements CommandExecutor {

    private nPunishment plugin;

    public punish(nPunishment plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        Player player = (Player) sender;
        String prefix = plugin.getConfig().getString("prefix");
        FileConfiguration m = messages.get();
        if(player.hasPermission("npunishments.punish")){
            if(args.length < 1) {
                CC.message(player, m.getString("punish.no-args").replace("%prefix%", prefix));
                return true;
            } else {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                if(player.getName() == target.getName()){
                    CC.message(player, m.getString("punish.antipunish").replace("%prefix%", prefix));
                } else {
                    gui gui = new gui(args[0]);
                    player.openInventory(gui.getInventory());
                }
            }
        } else {
            CC.message(player, m.getString("punishments.no-permissions").replace("%prefix%", prefix));
        }
        return false;
    }
}
