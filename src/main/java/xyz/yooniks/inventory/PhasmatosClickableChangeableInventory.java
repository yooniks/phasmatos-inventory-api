package xyz.yooniks.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class PhasmatosClickableChangeableInventory extends PhasmatosChangeableInventory implements PhasmatosClickableInventory {

  private final Map<Integer, Consumer<Player>> itemActions = new HashMap<>();

  public PhasmatosClickableChangeableInventory(String title, int size) {
    super(title, size);
  }

  @Override
  public void onClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player)) {
      return;
    }
    event.setCancelled(true);
    if (this.itemActions.containsKey(event.getSlot())) {
      this.itemActions.get(event.getSlot())
          .accept((Player)event.getWhoClicked());
    }
  }

  @Override
  public PhasmatosClickableInventory addItemAction(int slot, Consumer<Player> action) {
    this.itemActions.put(slot, action);
    return this;
  }

}
