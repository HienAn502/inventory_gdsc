package hienan.inventory_gdsc.infrastructure.controller;

import hienan.inventory_gdsc.domain.exception.InvalidInventoryException;
import hienan.inventory_gdsc.domain.model.Inventory;
import hienan.inventory_gdsc.domain.model.InventoryItem;
import hienan.inventory_gdsc.domain.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<String> createInventory(@RequestBody Inventory inventory) {
        try {
            return new ResponseEntity<String>(String.format("{\"id\": \"%s\"}", inventoryService.createInventory(inventory).getId()), HttpStatus.CREATED);
        } catch (InvalidInventoryException e) {
            return new ResponseEntity<String>(String.format("{\"%s\" : \"%s\"}", e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAll();
    }

    @PostMapping("/{id}/check-in")
    public ResponseEntity<String> checkIn(@PathVariable("id") int id, @RequestBody List<InventoryItem> inventoryItems) {
        try {
            inventoryService.checkIn(id, inventoryItems);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(String.format("{\"%s\" : \"%s\"}", e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
