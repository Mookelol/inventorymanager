package com.michael.idiot.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryHandler
        implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Bukkit.broadcastMessage(ChatColor.GRAY + "Ryan is a mother fucking God. Straight up Nigga.");
        Bukkit.broadcastMessage(ChatColor.GRAY + "Yeah, straight up my Nigga.");
        Bukkit.broadcastMessage(ChatColor.GRAY + "Oh and by the way, you clicked something in your inventory :^)");
    }
}
