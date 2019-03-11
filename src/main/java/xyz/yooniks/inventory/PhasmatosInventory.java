package xyz.yooniks.inventory;

import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PhasmatosInventory {

  void open(Player player);

  PhasmatosInventory addItem(int slot, ItemStack item);

  Map<Integer, ItemStack> getItems();

  String getTitle();

  int getSize();

}
