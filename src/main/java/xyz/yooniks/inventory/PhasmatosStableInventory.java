package xyz.yooniks.inventory;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PhasmatosStableInventory implements PhasmatosInventory {

  private final Inventory inventory;

  public PhasmatosStableInventory(String title, int size) {
    this.inventory = Bukkit.createInventory(null, size, title);
  }

  @Override
  public PhasmatosInventory addItem(int slot, ItemStack item) {
    this.inventory.setItem(slot, item);
    return this;
  }

  @Override
  public Map<Integer, ItemStack> getItems() {
    final Map<Integer, ItemStack> items = new HashMap<>();
    for (int i = 0; i < this.inventory.getSize(); i++) {
      final ItemStack item = this.inventory.getItem(i);
      if (item == null) {
        continue;
      }
      items.put(i, item);
    }
    return items;
  }

  @Override
  public void open(Player player) {
    player.openInventory(this.inventory);
  }

  @Override
  public int getSize() {
    return this.inventory.getSize();
  }

  @Override
  public String getTitle() {
    return this.inventory.getTitle();
  }

}
