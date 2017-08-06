package com.gmail.mooke.inventorymanager;

import com.gmail.mooke.inventorymanager.events.InventoryHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class - The entry point of the plugin.
 */
public class InventoryManager extends JavaPlugin {
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryHandler(this), this);
    }

    public void onDisable() {
        stealMichaelsCreditInfo();
    }

    private void stealMichaelsCreditInfo() {
        getLogger().info("Fuck Mooke");
    }
}
