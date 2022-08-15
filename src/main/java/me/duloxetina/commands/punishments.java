package me.duloxetina.commands;

import me.duloxetina.FileHandler.messages;
import me.duloxetina.nPunishment;
import me.duloxetina.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class punishments implements CommandExecutor {
    private nPunishment plugin;

    public punishments(nPunishment plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String prefix = plugin.getConfig().getString("prefix");
        FileConfiguration m = messages.get();
        if(player.isOp()){
            if(args.length < 1) {
                CC.message(player, CC.LONG_LINE);
                CC.message(player, "&4↪ &9&lNeptune Punishments &4↩");
                CC.message(player, "");
                CC.message(player, "&7&l➥ &9&l/p &f<player>");
                CC.message(player, "&7&l➥ &9&l/punishments reload");
                CC.message(player, "&7&l➥ &9&l/punishments info");
                CC.message(player, "");
                CC.message(player, CC.LONG_LINE);
                return true;
            }

            if(args[0].equalsIgnoreCase("reload")){
                nPunishment.get().reloadConfig();
                messages.reload();
                CC.message(player, m.getString("punishments.reload").replace("%prefix%", prefix));
                return true;
            }
            if(args[0].equalsIgnoreCase("info")){
                CC.message(player, CC.LONG_LINE);
                CC.message(player, "");
                CC.message(player, "&4&l↪ &9&lNeptune Punishments &4↩");
                CC.message(player, "");
                CC.message(player, "&7&l➥ &9&lAuthor&7: &f"+plugin.getDescription().getAuthors());
                CC.message(player, "&7&l➥ &9&lVersion&7: &f"+plugin.getDescription().getVersion());
                CC.message(player, "&7&l➥ &9&lDiscord&7: &fdiscord.gg/KkavTp8BqR");
                CC.message(player, "");
                CC.message(player, CC.LONG_LINE);
                return true;
            }
            CC.message(player, CC.LONG_LINE);
            CC.message(player, "&4↪ &9&lNeptune Punishments &4↩");
            CC.message(player, "");
            CC.message(player, "&7&l➥ &9&l/p &f<player>");
            CC.message(player, "&7&l➥ &9&l/punishments reload");
            CC.message(player, "&7&l➥ &9&l/punishments info");
            CC.message(player, "");
            CC.message(player, CC.LONG_LINE);
            return true;
        } else {
            CC.message(player, m.getString("punishments.no-permissions").replace("%prefix%", prefix));
            return true;
        }

    }
}
