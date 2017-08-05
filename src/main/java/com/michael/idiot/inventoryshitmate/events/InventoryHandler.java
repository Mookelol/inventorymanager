package com.michael.idiot.inventoryshitmate.events;

import com.michael.idiot.inventoryshitmate.InventoryShitMate;
import com.michael.idiot.inventoryshitmate.Ref;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * The handler class - Controls most aspects of inventory manipulation.
 */
public class InventoryHandler implements Listener {
    private InventoryShitMate mPlugin;
    private ItemStack mItemBlocker;

    public InventoryHandler(InventoryShitMate plugin) {
        mPlugin = plugin;
        mItemBlocker = new ItemStack(Material.STAINED_GLASS_PANE, 1);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // TODO: Evaluate player inventory and convert inventory to mirrors if required.
        mPlugin.getServer().getLogger().info("test: " + mItemBlocker.toString());
        Player player = event.getPlayer();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            // TODO: Do required task (Evaluate inventory)
            PlayerInventory inventory = player.getInventory();
            if (inventory.getHelmet() == null) {
                // Drop first few items and replace with mirrors. (Helmet holds 4 items)
                for (int i = 9; i <= 12; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (item != null) {
                        if (!item.equals(mItemBlocker)) {
                            player.getWorld().dropItem(player.getLocation(), item);
                        }
                    }
                    inventory.setItem(i, mItemBlocker);
                }
            }
            if (inventory.getChestplate() == null) {
                // Drop first few items and replace with mirrors. (Chest holds 12 items)
                for (int i = 13; i <= 24; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (item != null) {
                        if (!item.equals(mItemBlocker)) {
                            player.getWorld().dropItem(player.getLocation(), item);
                        }
                    }
                    inventory.setItem(i, mItemBlocker);
                }
            }
            if (inventory.getLeggings() == null) {
                // Drop first few items and replace with mirrors. (Leggings hold 6 items)
                for (int i = 25; i <= 30; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (item != null) {
                        if (!item.equals(mItemBlocker)) {
                            player.getWorld().dropItem(player.getLocation(), item);
                        }
                    }
                    inventory.setItem(i, mItemBlocker);
                }
            }
            if (inventory.getBoots() == null) {
                // Drop first few items and replace with mirrors. (Boots hold 5 items)
                for (int i = 31; i <= 35; i++) {
                    ItemStack item = inventory.getItem(i);
                    if (item != null) {
                        if (!item.equals(mItemBlocker)) {
                            player.getWorld().dropItem(player.getLocation(), item);
                        }
                    }
                    inventory.setItem(i, mItemBlocker);
                }
            }
        } else {
            // TODO: Do alternative task (Leave?)
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        mPlugin.getServer().getLogger().info("Player interacted.");
        Player player = event.getPlayer();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            PlayerInventory inventory = event.getPlayer().getInventory();
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Material material = event.getMaterial();
                switch (material) {
                    case LEATHER_HELMET:
                    case IRON_HELMET:
                    case GOLD_HELMET:
                    case DIAMOND_HELMET:
                        if (player.hasPermission(Ref.PERM_HELMET_ACCESS)) {
                            for (int i = 9; i <= 12; i++) {
                                ItemStack item = inventory.getItem(i);
                                if (item.equals(mItemBlocker)) {
                                    inventory.setItem(i, new ItemStack(Material.AIR, 1));
                                }
                            }
                        } else {
                            mPlugin.getServer().getLogger().info(player.getDisplayName() + " does not have " + Ref.PERM_HELMET_ACCESS);
                        }
                        break;
                    case LEATHER_CHESTPLATE:
                    case IRON_CHESTPLATE:
                    case GOLD_CHESTPLATE:
                    case DIAMOND_CHESTPLATE:
                        if (player.hasPermission(Ref.PERM_CHESTPLATE_ACCESS)) {
                            for (int i = 13; i <= 24; i++) {
                                ItemStack item = inventory.getItem(i);
                                if (item.equals(mItemBlocker)) {
                                    inventory.setItem(i, new ItemStack(Material.AIR, 1));
                                }
                            }
                        } else {
                            mPlugin.getServer().getLogger().info(player.getDisplayName() + " does not have " + Ref.PERM_CHESTPLATE_ACCESS);
                        }
                        break;
                    case LEATHER_LEGGINGS:
                    case IRON_LEGGINGS:
                    case GOLD_LEGGINGS:
                    case DIAMOND_LEGGINGS:
                        if (player.hasPermission(Ref.PERM_LEGGINGS_ACCESS)) {
                            for (int i = 25; i <= 30; i++) {
                                ItemStack item = inventory.getItem(i);
                                if (item.equals(mItemBlocker)) {
                                    inventory.setItem(i, new ItemStack(Material.AIR, 1));
                                }
                            }
                        } else {
                            mPlugin.getServer().getLogger().info(player.getDisplayName() + " does not have " + Ref.PERM_LEGGINGS_ACCESS);
                        }
                        break;
                    case LEATHER_BOOTS:
                    case IRON_BOOTS:
                    case GOLD_BOOTS:
                    case DIAMOND_BOOTS:
                        if (player.hasPermission(Ref.PERM_BOOTS_ACCESS)) {
                            for (int i = 31; i <= 35; i++) {
                                ItemStack item = inventory.getItem(i);
                                if (item.equals(mItemBlocker)) {
                                    inventory.setItem(i, new ItemStack(Material.AIR, 1));
                                }
                            }
                        } else {
                            mPlugin.getServer().getLogger().info(player.getDisplayName() + " does not have " + Ref.PERM_BOOTS_ACCESS);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event) {
        mPlugin.getServer().getLogger().info("Inventory clicked.");
        mPlugin.getServer().getLogger().info("Slot: " + event.getSlot());
        InventoryAction action = event.getAction();
        // TODO: Stop player from moving our glass panes.
        Player player = (Player) event.getWhoClicked();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            // TODO: Do required task (Make sure mirrors aren't moved)
            PlayerInventory inventory = player.getInventory();
            int slotID = event.getSlot();
            if (slotID >= 9 && slotID <= 12) {
                if (inventory.getHelmet() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 9 && slotID <= 24) {
                if (inventory.getChestplate() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 9 && slotID <= 30) {
                if (inventory.getLeggings() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 9 && slotID <= 35) {
                if (inventory.getBoots() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 9 && slotID <= 39) {
                // Armour slots (From bottom to top: 36 = boots, 37 = leggings, 38 = chestplate, 39 = helmet)

            }
        } else {
            // TODO: Do alternative task (Leave?)
        }
    }
}
