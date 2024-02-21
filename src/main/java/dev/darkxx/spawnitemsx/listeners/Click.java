package dev.darkxx.spawnitemsx.listeners;

import dev.darkxx.spawnitemsx.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class Click implements Listener {
    private Main plugin;
    public Click(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            ItemMeta meta = event.getCurrentItem().getItemMeta();
            if (meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "right-click-command"), PersistentDataType.STRING)) {
                event.setCancelled(true);
            }
        }
    }
}

