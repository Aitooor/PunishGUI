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

public class subcategories implements InventoryHolder {
    private final Inventory inv;



    public subcategories(String target, String serverName) {
        inv = Bukkit.createInventory(this, nPunishment.get().getConfig().getInt("inventory.gui.categories."+serverName+".submenus.size"), nPunishment.get().getConfig().getString("inventory.gui.categories."+serverName+".submenus.name") +target);
        init(target, serverName);

    }




    private void init(String target, String serverName) {
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
        for (final String serverName2 : config.getConfigurationSection("inventory.gui.categories."+serverName+".submenus.items").getKeys(false)) {
            inv.setItem(config.getInt("inventory.gui.categories."+ serverName + ".submenus.items."+serverName2+".slot") - 1, createItem(serverName, serverName2, target));
        }
    }


    private ItemStack createItem(String serverName, String serverName2, String target) {
        String material = nPunishment.get().getConfig().getString("inventory.gui.categories."+ serverName + ".submenus.items."+serverName2+".material");
        int id = nPunishment.get().getConfig().getInt("inventory.gui.categories."+ serverName + ".submenus.items."+serverName2+".id");
        String name = nPunishment.get().getConfig().getString("inventory.gui.categories."+ serverName + ".submenus.items."+serverName2+".name");
        ItemStack item = new ItemStack(Material.getMaterial(material), 1, (short)id);
        List<String> lore = new ArrayList<>();
        for(String lore2 : nPunishment.get().getConfig().getStringList("inventory.gui.categories."+ serverName + ".submenus.items."+serverName2+".lore")){
            lore.add(CC.translate(lore2.replaceAll("%player%", target)));
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