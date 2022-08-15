package me.duloxetina.inventories;

import me.duloxetina.nPunishment;
import me.duloxetina.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class gui implements InventoryHolder {
    private final Inventory inv;
    public static ItemStack BLANK;


    public gui(String target) {
        inv = Bukkit.createInventory(this, nPunishment.get().getConfig().getInt("inventory.gui.size"), nPunishment.get().getConfig().getString("inventory.gui.name").replace("%player%", target));
        init(target);

    }




    private void init(String target) {
        String player = target;
        ItemStack profile;
        FileConfiguration config = nPunishment.get().getConfig();
        if(nPunishment.get().getConfig().getBoolean("inventory.gui.fill.active")){
            for (int i = 0; i < inv.getSize(); ++i) {
                String material = nPunishment.get().getConfig().getString("inventory.gui.fill.material");
                int id = nPunishment.get().getConfig().getInt("inventory.gui.fill.id");
                String name = nPunishment.get().getConfig().getString("inventory.gui.fill.name");
                ItemStack item = new ItemStack(Material.getMaterial(material), 1, (short)id);
                ItemMeta meta = item.getItemMeta();

                List<String> lore = new ArrayList<>();
                for(String lore2 : nPunishment.get().getConfig().getStringList("inventory.gui.fill.lore")){
                    lore.add(CC.translate(lore2));
                }
                meta.setDisplayName(CC.translate(name));
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(i, item);
            }
        }
        for (final String serverName : config.getConfigurationSection("inventory.gui.categories").getKeys(false)) {
            inv.setItem(config.getInt("inventory.gui.categories." + serverName + ".slot") - 1, createItem(serverName));
        }

    }


    private ItemStack createItem(String serverName) {
        String material = nPunishment.get().getConfig().getString("inventory.gui.categories."+ serverName + ".material");
        int id = nPunishment.get().getConfig().getInt("inventory.gui.categories."+ serverName + ".id");
        String name = nPunishment.get().getConfig().getString("inventory.gui.categories."+ serverName + ".name");
        ItemStack item = new ItemStack(Material.getMaterial(material), 1, (short)id);
        List<String> lore = new ArrayList<>();
        for(String lore2 : nPunishment.get().getConfig().getStringList("inventory.gui.categories."+ serverName + ".lore")){
            lore.add(CC.translate(lore2));
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(name));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    private ItemStack createSkull(Player player, String name, Material mat, int id, List<String> lore){
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(name);
        meta.setOwner(player.getName());
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
