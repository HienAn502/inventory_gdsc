package hienan.inventory_gdsc.domain.service;


import hienan.inventory_gdsc.domain.model.Inventory;
import hienan.inventory_gdsc.domain.model.InventoryItem;

import java.util.List;

public interface InventoryService {
    Inventory createInventory(Inventory inventory);
    List<Inventory> getAll();
    void checkIn(int id, List<InventoryItem> inventoryItems);
}
