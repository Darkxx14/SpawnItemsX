package dev.darkxx.spawnitemsx;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Set;
public class SpawnItems implements CommandExecutor {
    private Main plugin;
    public SpawnItems(Main plugin) {
        this.plugin = plugin;
    }
    private String formatColors(String message) {
        return IridiumColorAPI.process(ChatColor.translateAlternateColorCodes('&', message));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " give <player> OR /" + label + " reload");
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.loadSpawnItemsConfig();
            sender.sendMessage(ChatColor.GREEN + "SpawnItems configuration reloaded!");
            return true;
        }
        if (!args[0].equalsIgnoreCase("give") || args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " give <player>");
            return true;
        }
        String playerName = args[1];
        Player player = plugin.getServer().getPlayer(playerName);
        if (player == null || !player.isOnline()) {
            sender.sendMessage(ChatColor.RED + "Player " + playerName + " is not online!");
            return true;
        }
        Set<String> itemList = plugin.getSpawnItemsConfig().getConfigurationSection("items").getKeys(false);
        if (itemList == null || itemList.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "No items configured in the plugin!");
            return true;
        }
        for (String itemKey : itemList) {
            String path = "items." + itemKey;
            String materialName = plugin.getSpawnItemsConfig().getString(path + ".item");
            String name = plugin.getSpawnItemsConfig().getString(path + ".name");
            int slot = plugin.getSpawnItemsConfig().getInt(path + ".slot");
            boolean hideAttributes = plugin.getSpawnItemsConfig().getBoolean(path + ".hide-attributes");
            String rightClickCommand = plugin.getSpawnItemsConfig().getString(path + ".right-click-command");
            Material material = Material.matchMaterial(materialName);
            if (material == null) {
                plugin.getLogger().warning("Invalid material for item " + itemKey + ": " + materialName);
                continue;
            }
            ItemStack itemStack = new ItemStack(material);
            ItemMeta meta = itemStack.getItemMeta();
            String formattedName = formatColors(name);
            meta.setDisplayName(formattedName);
            if (hideAttributes) {
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            }
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "right-click-command"), PersistentDataType.STRING, rightClickCommand);
            itemStack.setItemMeta(meta);
            player.getInventory().setItem(slot, itemStack);
        }
        return true;
    }
}