package hienan.inventory_gdsc.domain.service;

import hienan.inventory_gdsc.domain.exception.InvalidInventoryException;
import hienan.inventory_gdsc.domain.exception.InvalidInventoryItemException;
import hienan.inventory_gdsc.domain.model.Inventory;
import hienan.inventory_gdsc.domain.model.InventoryItem;
import hienan.inventory_gdsc.domain.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory(Inventory inventory) {
        if (inventory.getHeight() < 0)
            throw new InvalidInventoryException("Height", inventory.getHeight());
        if (inventory.getWidth() < 0)
            throw new InvalidInventoryException("Width", inventory.getWidth());
        if (inventory.getLength() < 0)
            throw new InvalidInventoryException("Length", inventory.getLength());
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public void checkIn(int id, List<InventoryItem> inventoryItems) {
        final Inventory inventory = inventoryRepository.getById(id);
        Inventory newInventory = new Inventory();
        newInventory.setId(id);
        newInventory.setWidth(inventory.getWidth());
        newInventory.setHeight(inventory.getHeight());
        newInventory.setLength(inventory.getLength());
        for (InventoryItem inventoryItem : inventoryItems) {
            if (inventoryItem.getHeight() < 0)
                throw new InvalidInventoryItemException("Height", inventoryItem.getHeight());
            if (inventory.getWidth() < 0)
                throw new InvalidInventoryItemException("Width", inventoryItem.getWidth());
            if (inventory.getLength() < 0)
                throw new InvalidInventoryItemException("Length", inventoryItem.getLength());

            newInventory.checkIn(inventoryItem);
            inventory.checkIn(inventoryItem);
        }
        inventoryRepository.save(newInventory);
    }
}
