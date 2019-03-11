package xyz.yooniks.inventory;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface PhasmatosCloseableInventory {

  void onClose(InventoryCloseEvent event);

}
