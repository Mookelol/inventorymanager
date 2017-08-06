package com.michael.idiot.inventorymanager.events;

import com.michael.idiot.inventorymanager.InventoryManager;
import com.michael.idiot.inventorymanager.Ref;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * The handler class - Controls most aspects of inventory manipulation.
 */
public class InventoryHandler implements Listener {
    private InventoryManager mPlugin;
    private ItemStack mItemBlocker;

    public InventoryHandler(InventoryManager plugin) {
        mPlugin = plugin;
        mItemBlocker = new ItemStack(Material.STAINED_GLASS_PANE, 1);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            // Scan through armour slots to see if armour has changed then drop/reformat.
            checkAndDropInventory(player);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            PlayerInventory inventory = event.getPlayer().getInventory();
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (isHelmet(event.getItem())) {
                    if (player.hasPermission(Ref.PERM_HELMET_ACCESS)) {
                        for (int i = 9; i <= 12; i++) {
                            ItemStack item = inventory.getItem(i);
                            if (item.equals(mItemBlocker)) {
                                inventory.setItem(i, new ItemStack(Material.AIR, 1));
                            }
                        }
                    } else {
                        mPlugin.getServer().getLogger().warning(player.getDisplayName() + " does not have " + Ref.PERM_HELMET_ACCESS);
                    }
                } else if (isChestplate(event.getItem())) {
                    if (player.hasPermission(Ref.PERM_CHESTPLATE_ACCESS)) {
                        for (int i = 13; i <= 24; i++) {
                            ItemStack item = inventory.getItem(i);
                            if (item.equals(mItemBlocker)) {
                                inventory.setItem(i, new ItemStack(Material.AIR, 1));
                            }
                        }
                    } else {
                        mPlugin.getServer().getLogger().warning(player.getDisplayName() + " does not have " + Ref.PERM_CHESTPLATE_ACCESS);
                    }
                } else if (isLeggings(event.getItem())) {
                    if (player.hasPermission(Ref.PERM_LEGGINGS_ACCESS)) {
                        for (int i = 25; i <= 30; i++) {
                            ItemStack item = inventory.getItem(i);
                            if (item.equals(mItemBlocker)) {
                                inventory.setItem(i, new ItemStack(Material.AIR, 1));
                            }
                        }
                    } else {
                        mPlugin.getServer().getLogger().warning(player.getDisplayName() + " does not have " + Ref.PERM_LEGGINGS_ACCESS);
                    }
                } else if (isBoots(event.getItem())) {
                    if (player.hasPermission(Ref.PERM_BOOTS_ACCESS)) {
                        for (int i = 31; i <= 35; i++) {
                            ItemStack item = inventory.getItem(i);
                            if (item.equals(mItemBlocker)) {
                                inventory.setItem(i, new ItemStack(Material.AIR, 1));
                            }
                        }
                    } else {
                        mPlugin.getServer().getLogger().warning(player.getDisplayName() + " does not have " + Ref.PERM_BOOTS_ACCESS);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerInventory inventory = player.getInventory();
        if (!player.hasPermission(Ref.PERM_INVENTORY_BYPASS)) {
            int slotID = event.getSlot();
            if (event.isShiftClick() && (slotID >= 36 && slotID <= 39 || isArmour(inventory.getItem(slotID)))) {
                event.setCancelled(true);
                return;
            }
            if (slotID >= 9 && slotID <= 12) {
                if (inventory.getHelmet() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 13 && slotID <= 24) {
                if (inventory.getChestplate() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 25 && slotID <= 30) {
                if (inventory.getLeggings() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 31 && slotID <= 35) {
                if (inventory.getBoots() == null) {
                    event.setCancelled(true);
                }
            } else if (slotID >= 36 && slotID <= 39) {
                // Armour slots (From bottom to top: 36 = boots, 37 = leggings, 38 = chestplate, 39 = helmet)
                switch (slotID) {
                    case 36:
                        if (event.isRightClick() || event.isLeftClick()) {
                            if (isBoots(event.getCursor())) {
                                for (int i = 31; i <= 35; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (item.equals(mItemBlocker)) {
                                            inventory.setItem(i, new ItemStack(Material.AIR));
                                        }
                                    }
                                }
                            } else if (event.getCursor().getType() == Material.AIR) {
                                for (int i = 31; i <= 35; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (!item.equals(mItemBlocker)) {
                                            player.getWorld().dropItem(player.getLocation(), item);
                                        } else if (item.getAmount() != mItemBlocker.getAmount()) {
                                            item.setAmount(1);
                                        }
                                    }
                                    inventory.setItem(i, mItemBlocker);
                                }
                            }
                        }
                        break;
                    case 37:
                        if (event.isRightClick() || event.isLeftClick()) {
                            if (isLeggings(event.getCursor())) {
                                for (int i = 25; i <= 30; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (item.equals(mItemBlocker)) {
                                            inventory.setItem(i, new ItemStack(Material.AIR));
                                        }
                                    }
                                }
                            } else if (event.getCursor().getType() == Material.AIR) {
                                for (int i = 25; i <= 30; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (!item.equals(mItemBlocker)) {
                                            player.getWorld().dropItem(player.getLocation(), item);
                                        } else if (item.getAmount() != mItemBlocker.getAmount()) {
                                            item.setAmount(1);
                                        }
                                    }
                                    inventory.setItem(i, mItemBlocker);
                                }
                            }
                        }
                        break;
                    case 38:
                        if (event.isRightClick() || event.isLeftClick()) {
                            if (isChestplate(event.getCursor())) {
                                for (int i = 13; i <= 24; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (item.equals(mItemBlocker)) {
                                            inventory.setItem(i, new ItemStack(Material.AIR));
                                        }
                                    }
                                }
                            } else if (event.getCursor().getType() == Material.AIR) {
                                for (int i = 13; i <= 24; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (!item.equals(mItemBlocker)) {
                                            player.getWorld().dropItem(player.getLocation(), item);
                                        } else if (item.getAmount() != mItemBlocker.getAmount()) {
                                            item.setAmount(1);
                                        }
                                    }
                                    inventory.setItem(i, mItemBlocker);
                                }
                            }
                        }
                        break;
                    case 39:
                        if (event.isRightClick() || event.isLeftClick()) {
                            if (isHelmet(event.getCursor())) {
                                for (int i = 9; i <= 12; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (item.equals(mItemBlocker)) {
                                            inventory.setItem(i, new ItemStack(Material.AIR));
                                        }
                                    }
                                }
                            } else if (event.getCursor().getType() == Material.AIR) {
                                for (int i = 9; i <= 12; i++) {
                                    ItemStack item = inventory.getItem(i);
                                    if (item != null) {
                                        if (!item.equals(mItemBlocker)) {
                                            player.getWorld().dropItem(player.getLocation(), item);
                                        } else if (item.getAmount() != mItemBlocker.getAmount()) {
                                            item.setAmount(1);
                                        }
                                    }
                                    inventory.setItem(i, mItemBlocker);
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    /**
     * This function will check the player inventory for armour, if null, it will drop and replace slots with blocker items.
     * @param player The player to check.
     */
    private void checkAndDropInventory(final Player player) {
        PlayerInventory inventory = player.getInventory();
        if (inventory.getHelmet() == null) {
            // Drop first few items and replace with mirrors. (Helmet holds 4 items)
            for (int i = 9; i <= 12; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (!item.equals(mItemBlocker) && item.getType() != mItemBlocker.getType()) {
                        player.getWorld().dropItem(player.getLocation(), item);
                    }
                }
                inventory.setItem(i, mItemBlocker);
            }
        } else {
            for (int i = 9; i <= 12; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (item.equals(mItemBlocker)) {
                        inventory.setItem(i, new ItemStack(Material.AIR));
                    }
                }
            }
        }
        if (inventory.getChestplate() == null) {
            // Drop first few items and replace with mirrors. (Chest holds 12 items)
            for (int i = 13; i <= 24; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (!item.equals(mItemBlocker)) {
                        player.getWorld().dropItem(player.getLocation(), item);
                    } else if (item.getAmount() != mItemBlocker.getAmount()) {
                        item.setAmount(1);
                    }
                }
                inventory.setItem(i, mItemBlocker);
            }
        } else {
            for (int i = 13; i <= 24; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (item.equals(mItemBlocker)) {
                        inventory.setItem(i, new ItemStack(Material.AIR));
                    }
                }
            }
        }
        if (inventory.getLeggings() == null) {
            // Drop first few items and replace with mirrors. (Leggings hold 6 items)
            for (int i = 25; i <= 30; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (!item.equals(mItemBlocker)) {
                        player.getWorld().dropItem(player.getLocation(), item);
                    } else if (item.getAmount() != mItemBlocker.getAmount()) {
                        item.setAmount(1);
                    }
                }
                inventory.setItem(i, mItemBlocker);
            }
        } else {
            for (int i = 25; i <= 30; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (item.equals(mItemBlocker)) {
                        inventory.setItem(i, new ItemStack(Material.AIR));
                    }
                }
            }
        }
        if (inventory.getBoots() == null) {
            // Drop first few items and replace with mirrors. (Boots hold 5 items)
            for (int i = 31; i <= 35; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (!item.equals(mItemBlocker)) {
                        player.getWorld().dropItem(player.getLocation(), item);
                    } else if (item.getAmount() != mItemBlocker.getAmount()) {
                        item.setAmount(1);
                    }
                }
                inventory.setItem(i, mItemBlocker);
            }
        } else {
            for (int i = 31; i <= 35; i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (item.equals(mItemBlocker)) {
                        inventory.setItem(i, new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }
    
    private boolean isArmour(ItemStack item) {
        return isHelmet(item) || isChestplate(item) || isLeggings(item) || isBoots(item);
    }

    private boolean isHelmet(ItemStack item) {
        switch (item.getType()) {
            case LEATHER_HELMET:
                return true;
            case IRON_HELMET:
                return true;
            case GOLD_HELMET:
                return true;
            case DIAMOND_HELMET:
                return true;
        }
        return false;
    }

    private boolean isChestplate(ItemStack item) {
        switch (item.getType()) {
            case LEATHER_CHESTPLATE:
                return true;
            case IRON_CHESTPLATE:
                return true;
            case GOLD_CHESTPLATE:
                return true;
            case DIAMOND_CHESTPLATE:
                return true;
        }
        return false;
    }

    private boolean isLeggings(ItemStack item) {
        switch (item.getType()) {
            case LEATHER_LEGGINGS:
                return true;
            case IRON_LEGGINGS:
                return true;
            case GOLD_LEGGINGS:
                return true;
            case DIAMOND_LEGGINGS:
                return true;
        }
        return false;
    }

    private boolean isBoots(ItemStack item) {
        switch (item.getType()) {
            case LEATHER_BOOTS:
                return true;
            case IRON_BOOTS:
                return true;
            case GOLD_BOOTS:
                return true;
            case DIAMOND_BOOTS:
                return true;
        }
        return false;
    }
}
