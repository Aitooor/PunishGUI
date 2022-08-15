package me.duloxetina;

import me.duloxetina.FileHandler.messages;
import me.duloxetina.commands.punish;
import me.duloxetina.commands.punishments;
import me.duloxetina.listener.InventoryClick;
import me.duloxetina.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class nPunishment extends JavaPlugin {
    @Override
    public void onEnable(){
        CC.log("&a╭────────────────────────────────────────╮");
        CC.log("&a│                                        │");
        CC.log("&a│                                        │");
        CC.log("&a│         &9nPunishments Plugin &f- &7v1.0     &a│");
        CC.log("&a│           &9(Neptune Community)          &a│");
        CC.log("&a│                                        │");
        CC.log("&a╰────────────────────────────────────────╯");
        this.getCommand("punish").setExecutor(new punish(this));
        this.getCommand("punishments").setExecutor(new punishments(this));
        registerConfig();
        messages.registerMessages();
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
    }
    public void onDisable(){
        CC.log("&c╭────────────────────────────────────────╮");
        CC.log("&c│                                        │");
        CC.log("&c│                                        │");
        CC.log("&c│         &9nPunishments Plugin &f- &7v1.0     &c│");
        CC.log("&c│           &9(Neptune Community)          &c│");
        CC.log("&c│                                        │");
        CC.log("&c╰────────────────────────────────────────╯");
    }
    public void registerConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public static nPunishment get() {
        return nPunishment.getPlugin(nPunishment.class);
    }
}