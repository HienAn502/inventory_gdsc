package hienan.inventory_gdsc.infrastructure.repository;

import hienan.inventory_gdsc.domain.model.Inventory;
import hienan.inventory_gdsc.domain.model.InventoryItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "height")
    private double height;
    @Column(name = "length")
    private double length;
    @Column(name = "width")
    private double width;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<InventoryItemEntity> inventoryItems;

    public static InventoryEntity fromDomainModel(Inventory inventory) {
        final InventoryEntity entity = new InventoryEntity();
        entity.id = inventory.getId();
        entity.height = inventory.getHeight();
        entity.width = inventory.getWidth();
        entity.length = inventory.getLength();
        entity.inventoryItems = new ArrayList<>();

        // Rut gon
        if (inventory.getInventoryItems() != null) {
            List<InventoryItem> itemList = inventory.getInventoryItems();
            for (InventoryItem item : itemList) {
                InventoryItemEntity itemEntity = new InventoryItemEntity(item);
                itemEntity.setInventory(entity);
                entity.inventoryItems.add(itemEntity);
            }
        }
        return entity;
    }

    public static Inventory toDomainModel(InventoryEntity entity) {
        final Inventory inventory = new Inventory();
        inventory.setId(entity.id);
        inventory.setHeight(entity.height);
        inventory.setWidth(entity.width);
        inventory.setLength(entity.length);

        // Rut gon
        if (inventory.getInventoryItems() != null) {
            List<InventoryItemEntity> itemList = entity.getInventoryItems();
            for (InventoryItemEntity itemEntity : itemList) {
                InventoryItem item = new InventoryItem();
                item.setName(itemEntity.getName());
                item.setHeight(itemEntity.getHeight());
                item.setWidth(itemEntity.getWidth());
                item.setLength(itemEntity.getLength());
                item.setQuantity(itemEntity.getQuantity());

                inventory.checkIn(item);
            }
        }

        return inventory;
    }

}
