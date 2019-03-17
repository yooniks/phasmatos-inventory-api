package xyz.yooniks.inventory.example;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.inventory.ItemBuilder;
import xyz.yooniks.inventory.PhasmatosBukkitInventoryAPI;
import xyz.yooniks.inventory.PhasmatosClickableChangeableInventory;
import xyz.yooniks.inventory.PhasmatosCloseableInventory;
import xyz.yooniks.inventory.PhasmatosInventory;
import xyz.yooniks.inventory.PhasmatosStableInventory;

public final class ExamplePlugin extends JavaPlugin {

  @Override
  public void onEnable() {

    final PhasmatosBukkitInventoryAPI inventoryAPI = new PhasmatosBukkitInventoryAPI();
    inventoryAPI.register(this);

    final PhasmatosInventory stableInventory = new ExampleStableInventory("This is simple stable inventory!", 9);
    stableInventory.addItem(0, new ItemBuilder(Material.COBBLESTONE)
        .withName("Hello!= )")
        .withLore("You can only look at me!")
        .build());

    final ExampleChangeableInventory changeableInventory = new ExampleChangeableInventory("Changeable inventory", 9);
    final ItemStack changeableItem = new ItemBuilder(Material.COBBLESTONE)
        .withName("Your name: {PLAYER}")
        .withLore("You can look at me, i will change my meta", "You can click me!!!")
        .build();
    changeableInventory.addItem(4, changeableItem);
    changeableInventory.addItemAction(4, (player) -> player.sendMessage("Wow! You clicked at me!"));

    inventoryAPI.addInventory(stableInventory);
    inventoryAPI.addInventory(changeableInventory);
  }

  private final class ExampleStableInventory extends PhasmatosStableInventory implements PhasmatosCloseableInventory {

    ExampleStableInventory(String title, int size) {
      super(title, size);
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
      //closed inventory, you can send message etc..
    }
  }

  private final class ExampleChangeableInventory extends PhasmatosClickableChangeableInventory {
    ExampleChangeableInventory(String title, int size) {
      super(title, size);
    }

    @Override
    public ItemStack updateItem(ItemStack item, int slot, Player player) {
      if (item.getType() == Material.COBBLESTONE) {
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringUtils.replace(meta.getDisplayName(), "{PLAYER}", player.getName()));
        item.setItemMeta(meta);
      }
      return item;
    }
  }

}
