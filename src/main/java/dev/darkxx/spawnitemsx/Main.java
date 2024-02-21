package dev.darkxx.spawnitemsx;

import dev.darkxx.spawnitemsx.listeners.Click;
import dev.darkxx.spawnitemsx.listeners.Interact;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
public class Main extends JavaPlugin {
    private FileConfiguration spawnItemsConfig;
    private FileConfiguration config;
    @Override
    public void onEnable() {
        loadSpawnItemsConfig();
        getCommand("spawnitems").setExecutor(new SpawnItems(this));
        getServer().getPluginManager().registerEvents(new Interact(this), this);
        getServer().getPluginManager().registerEvents(new Click(this), this);
    }
    public void loadSpawnItemsConfig() {
        File configFile = new File(getDataFolder(), "spawnitems.yml");
        if (!configFile.exists()) {
            saveResource("spawnitems.yml", false);
        }
        spawnItemsConfig = YamlConfiguration.loadConfiguration(configFile);
    }
    public FileConfiguration getSpawnItemsConfig() {
        return spawnItemsConfig;
    }
}
