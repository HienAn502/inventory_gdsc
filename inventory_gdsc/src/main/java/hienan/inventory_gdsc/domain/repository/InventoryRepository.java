package hienan.inventory_gdsc.domain.repository;

import hienan.inventory_gdsc.domain.model.Inventory;

import java.util.List;

public interface InventoryRepository {
    public Inventory save(Inventory inventory);
    public List<Inventory> findAll();
    public Inventory getById(int id);
}
