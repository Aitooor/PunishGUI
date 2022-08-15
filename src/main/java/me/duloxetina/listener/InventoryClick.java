package me.duloxetina.listener;

import me.duloxetina.inventories.gui;
import me.duloxetina.inventories.subcategories;
import me.duloxetina.nPunishment;
import me.duloxetina.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class InventoryClick implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == null) { return; }
        if(e.getClickedInventory().getHolder() instanceof gui){
            e.setCancelled(true);
            for (final String serverName : nPunishment.get().getConfig().getConfigurationSection("inventory.gui.categories").getKeys(false)) {
                if(e.getSlot() == nPunishment.get().getConfig().getInt("inventory.gui.categories." + serverName + ".slot") - 1){
                    subcategories s = new subcategories(e.getClickedInventory().getName().split("Punishment")[1], serverName);
                    player.openInventory(s.getInventory());
                }
            }
        }
        if(e.getClickedInventory().getHolder() instanceof subcategories) {
            e.setCancelled(true);

            for (final String serverName : nPunishment.get().getConfig().getConfigurationSection("inventory.gui.categories").getKeys(false)) {
                for (final String serverName2 : nPunishment.get().getConfig().getConfigurationSection("inventory.gui.categories."+serverName+".submenus.items").getKeys(false)) {
                    String nameInv = nPunishment.get().getConfig().getString("inventory.gui.categories."+serverName+".submenus.name");
                    if(e.getSlot() == nPunishment.get().getConfig().getInt("inventory.gui.close.slot") - 1) {
                        player.closeInventory();
                    }
                    if(e.getClickedInventory().getName().startsWith(CC.translate(nameInv))){
                        if(e.getSlot() == nPunishment.get().getConfig().getInt("inventory.gui.categories."+serverName+".submenus.items."+serverName2+".slot") -1){
                            List<String> commands = nPunishment.get().getConfig().getStringList("inventory.gui.categories."+serverName+".submenus.items."+serverName2+".commands");
                            commands.forEach(action -> {
                                action = action.replace("%player%", e.getClickedInventory().getName().split(CC.translate(nameInv))[1]);
                                player.performCommand(action);
                                return;
                            });
                        }
                    }
                }
            }
        }
    }
}