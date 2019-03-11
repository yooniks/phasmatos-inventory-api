package xyz.yooniks.inventory;

import java.util.List;

public interface PhasmatosInventoryAPI {

  void addInventory(PhasmatosInventory inventory);

  List<PhasmatosInventory> getInventories();

  PhasmatosInventory findByTitle(String title);

  PhasmatosInventory findByTitleAndSize(String title, int size);

}
