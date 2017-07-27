package com.michael.idiot.inventoryshitmate.events;

import com.michael.idiot.inventoryshitmate.InventoryShitMate;
import com.michael.idiot.inventoryshitmate.Ref;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * The handler class - Controls most aspects of inventory manipulation.
 */
public class InventoryHandler implements Listener {
    private InventoryShitMate mPlugin;

    public InventoryHandler(InventoryShitMate plugin) {
        mPlugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // TODO: Evaluate player inventory and convert inventory to mirrors if required.
        Player player = event.getPlayer();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            // TODO: Do required task (Evaluate inventory)
        } else {
            // TODO: Do alternative task (Leave?)
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // TODO: Stop player from moving our glass panes.
        Player player = (Player) event.getWhoClicked();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            // TODO: Do required task (Make sure mirrors aren't moved)
        } else {
            // TODO: Do alternative task (Leave?)
        }
    }
}
