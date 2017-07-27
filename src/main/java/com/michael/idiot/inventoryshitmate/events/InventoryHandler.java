package com.michael.idiot.inventoryshitmate.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryHandler implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Bukkit.broadcastMessage(ChatColor.GRAY + "Oh, you clicked something in your inventory. :^)");
    }
}
