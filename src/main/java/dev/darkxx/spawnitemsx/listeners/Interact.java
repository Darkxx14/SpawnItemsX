package dev.darkxx.spawnitemsx.listeners;

import dev.darkxx.spawnitemsx.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
public class Interact implements Listener {
    private Main plugin;
    public Interact(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = event.getItem();
            if (item != null && item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "right-click-command"), PersistentDataType.STRING)) {
                    String command = meta.getPersistentDataContainer().get(new NamespacedKey(plugin, "right-click-command"), PersistentDataType.STRING);
                    Bukkit.dispatchCommand(event.getPlayer(), command);
                }
            }
        }
    }
}

