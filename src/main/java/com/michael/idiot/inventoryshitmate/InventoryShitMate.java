package com.michael.idiot.inventoryshitmate;

import com.michael.idiot.inventoryshitmate.events.InventoryHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class - The entry point of the plugin.
 */
public class InventoryShitMate extends JavaPlugin {
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryHandler(this), this);
    }

    public void onDisable() {
        getServer().getLogger().info("Fuck Mooke");
    }
}
