package com.michael.idiot.inventoryshitmate;

import com.michael.idiot.inventoryshitmate.events.InventoryHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryShitMate extends JavaPlugin {
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
    }

    public void onDisable() {
        getServer().getLogger().info("Fuck Mooke");
    }
}
