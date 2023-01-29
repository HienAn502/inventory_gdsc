package hienan.inventory_gdsc.domain.model;

import lombok.Data;

@Data
public class InventoryItem {
    private String name;
    private double height;
    private double length;
    private double width;
    private long quantity;

    public double individual_volumn() {return height * width * length;}
    public double total_volumn() {return individual_volumn() * quantity;}
}
