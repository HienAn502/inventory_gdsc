package hienan.inventory_gdsc.infrastructure.repository;

import hienan.inventory_gdsc.domain.model.InventoryItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="inventory_item")
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "height")
    private double height;
    @Column(name = "length")
    private double length;
    @Column(name = "width")
    private double width;
    @Column(name = "quantity")
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    public InventoryItemEntity(InventoryItem item) {
        this.name = item.getName();
        this.height = item.getHeight();
        this.width = item.getWidth();
        this.length = item.getLength();
        this.quantity = item.getQuantity();
    }
}
