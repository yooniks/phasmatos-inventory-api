package xyz.yooniks.inventory;

import java.util.function.Consumer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface PhasmatosClickableInventory {

  PhasmatosClickableInventory addItemAction(int slot, Consumer<Player> action);

  void onClick(InventoryClickEvent event);

}
