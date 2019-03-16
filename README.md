Maven:
```xml
    <dependency>
      <groupId>com.github.yooniks</groupId>
      <artifactId>phasmatos-inventory-api</artifactId>
      <version>1.0</version>
      <scope>compile</scope>
    </dependency>
           
    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>
```
Example:
```java
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
    public ItemStack updateItem(ItemStack item, Player player) {
      if (item.getType() == Material.COBBLESTONE) {
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringUtils.replace(meta.getDisplayName(), "{PLAYER}", player.getName()));
        item.setItemMeta(meta);
      }
      return item;
    }
  }
  ```