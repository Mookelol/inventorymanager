package com.michael.idiot.inventoryshitmate.events;

import com.michael.idiot.inventoryshitmate.InventoryShitMate;
import com.michael.idiot.inventoryshitmate.Ref;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * The handler class - Controls most aspects of inventory manipulation.
 */
public class InventoryHandler implements Listener {
    private InventoryShitMate mPlugin;
    private ItemStack mItemBlocker = new ItemStack(Material.STAINED_GLASS_PANE, 1);

    public InventoryHandler(InventoryShitMate plugin) {
        mPlugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // TODO: Evaluate player inventory and convert inventory to mirrors if required.
        Player player = event.getPlayer();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            // TODO: Do required task (Evaluate inventory)
            PlayerInventory inventory = player.getInventory();
            if (inventory.getHelmet() == null) {
                // Drop first few items and replace with mirrors. (Helmet holds 4 items)
                for (int i = 9; i <= 12; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (!item.equals(mItemBlocker)) {
                        player.getWorld().dropItem(player.getLocation(), item);
                        inventory.setItem(i, mItemBlocker);
                    }
                }
            }
            if (inventory.getChestplate() == null) {
                // Drop first few items and replace with mirrors. (Chest holds 12 items)
                for (int i = 13; i <= 24; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (!item.equals(mItemBlocker)) {
                        player.getWorld().dropItem(player.getLocation(), item);
                        inventory.setItem(i, mItemBlocker);
                    }
                }
            }
            if (inventory.getLeggings() == null) {
                // Drop first few items and replace with mirrors. (Leggings hold 6 items)
                for (int i = 25; i <= 30; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (!item.equals(mItemBlocker)) {
                        player.getWorld().dropItem(player.getLocation(), item);
                        inventory.setItem(i, mItemBlocker);
                    }
                }
            }
            if (inventory.getBoots() == null) {
                // Drop first few items and replace with mirrors. (Boots hold 5 items)
                for (int i = 31; i <= 35; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (!item.equals(mItemBlocker)) {
                        player.getWorld().dropItem(player.getLocation(), item);
                        inventory.setItem(i, mItemBlocker);
                    }
                }
            }
        } else {
            // TODO: Do alternative task (Leave?)
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event) {
        // TODO: Stop player from moving our glass panes.
        Player player = (Player) event.getWhoClicked();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            // TODO: Do required task (Make sure mirrors aren't moved)
            PlayerInventory inventory = player.getInventory();
            int slotid = event.getSlot();
            if (slotid <= 12) {
                if (inventory.getHelmet() == null) {
                    event.setCancelled(true);
                }
            } else if (slotid <= 24) {
                if (inventory.getChestplate() == null) {
                    event.setCancelled(true);
                }
            } else if (slotid <= 30) {
                if (inventory.getLeggings() == null) {
                    event.setCancelled(true);
                }
            } else if (slotid <= 35) {
                if (inventory.getBoots() == null) {
                    event.setCancelled(true);
                }
            }
        } else {
            // TODO: Do alternative task (Leave?)
        }
    }
}
