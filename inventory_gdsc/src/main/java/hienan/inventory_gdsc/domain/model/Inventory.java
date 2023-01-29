package hienan.inventory_gdsc.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Inventory {
    private int id;
    private double height;
    private double length;
    private double width;

    @Setter(AccessLevel.NONE)
    private List<InventoryItem> inventoryItems;

    public List<InventoryItem> getInventoryItems() {
        if (inventoryItems != null) return Collections.unmodifiableList(inventoryItems);
        else return new ArrayList<>();
    }

    public double volumn() {return  height * width * length;}

    public double capacity() {
        double totalInventoryItemVolumn = 0;
        if (inventoryItems == null) return volumn();
        for (InventoryItem item : inventoryItems)
            totalInventoryItemVolumn += item.total_volumn();

        return volumn() - totalInventoryItemVolumn;
    }

    public void checkIn(InventoryItem inventoryItem) {
        if (inventoryItem.total_volumn() > capacity())
            throw new RuntimeException("Insufficient capacity");
        if (inventoryItems == null) {
            inventoryItems = new ArrayList<>();
        }
        for (InventoryItem item : inventoryItems) {
            if (item.getName().equals(inventoryItem.getName())) {
                item.setQuantity(item.getQuantity() + inventoryItem.getQuantity());
                break;
            }
        }
        inventoryItems.add(inventoryItem);
    }
}
