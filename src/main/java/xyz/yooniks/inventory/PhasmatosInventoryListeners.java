package xyz.yooniks.inventory;

import com.google.common.base.Preconditions;
import java.util.UUID;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PhasmatosInventoryListeners implements Listener {

  private final PhasmatosInventoryAPI inventoryAPI;

  public PhasmatosInventoryListeners(PhasmatosInventoryAPI inventoryAPI) {
    this.inventoryAPI = inventoryAPI;
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player)) {
      return;
    }
    final Player player = (Player) event.getWhoClicked();
    final Inventory inventory = event.getClickedInventory();
    if (inventory == null || inventory.equals(player.getInventory())) {
      return;
    }
    final PhasmatosInventory phasmatosInventory = this.inventoryAPI
        .findByTitleAndSize(inventory.getTitle(), inventory.getSize());
    if (phasmatosInventory instanceof PhasmatosClickableInventory) {
      ((PhasmatosClickableInventory) phasmatosInventory).onClick(event);
    }
  }

  @EventHandler
  public void onClose(InventoryCloseEvent event) {
    if (!(event.getPlayer() instanceof Player)) {
      return;
    }
    final Player player = (Player) event.getPlayer();
    final Inventory inventory = event.getInventory();
    if (inventory == null || inventory.equals(player.getInventory())) {
      return;
    }
    final PhasmatosInventory phasmatosInventory = this.inventoryAPI
        .findByTitleAndSize(inventory.getTitle(), inventory.getSize());
    if (phasmatosInventory instanceof PhasmatosCloseableInventory) {
      ((PhasmatosCloseableInventory) phasmatosInventory).onClose(event);
    }
  }

}
