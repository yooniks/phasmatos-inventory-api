package xyz.yooniks.inventory;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class PhasmatosChangeableInventory implements PhasmatosInventory {

  private final String title;
  private final int size;

  private final Map<Integer, ItemStack> items = new HashMap<>();

  public PhasmatosChangeableInventory(String title, int size) {
    this.title = title;
    this.size = size;
  }

  @Override
  public PhasmatosInventory addItem(int slot, ItemStack item) {
    this.items.put(slot, item);
    return this;
  }

  @Override
  public Map<Integer, ItemStack> getItems() {
    return new HashMap<>(this.items);
  }

  @Override
  public void open(Player player) {
    final Inventory inventory = Bukkit.createInventory(null, size, title);

    this.items.forEach((slot, item) ->
        inventory.setItem(slot, item = this.updateItem(item, player)));

    player.openInventory(inventory);
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public int getSize() {
    return size;
  }

  public abstract ItemStack updateItem(ItemStack item, Player player);

}
