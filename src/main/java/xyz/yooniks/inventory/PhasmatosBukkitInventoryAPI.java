package xyz.yooniks.inventory;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PhasmatosBukkitInventoryAPI implements PhasmatosInventoryAPI {

  private final List<PhasmatosInventory> inventories = new ArrayList<>();

  public void register(Plugin plugin) {
    final PluginManager pluginManager = plugin.getServer().getPluginManager();
    pluginManager.registerEvents(new PhasmatosInventoryListeners(this), plugin);
  }

  @Override
  public PhasmatosInventory findByTitle(String title) {
    return this.inventories.stream()
        .filter(inventory -> inventory.getTitle().equalsIgnoreCase(title))
        .findFirst()
        .orElse(null);
  }

  @Override
  public PhasmatosInventory findByTitleAndSize(String title, int size) {
    return this.inventories.stream()
        .filter(inventory -> inventory.getTitle().equalsIgnoreCase(title)
            && inventory.getSize() == size)
        .findFirst()
        .orElse(null);
  }

  @Override
  public void addInventory(PhasmatosInventory inventory) {
    this.inventories.add(inventory);
  }

  @Override
  public List<PhasmatosInventory> getInventories() {
    return new ArrayList<>(inventories);
  }

}