package hienan.inventory_gdsc.infrastructure.repository;

import hienan.inventory_gdsc.domain.exception.InventoryNotFoundException;
import hienan.inventory_gdsc.domain.model.Inventory;
import hienan.inventory_gdsc.domain.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {
    @Autowired
    private InventoryJpaRepo inventoryJpaRepo;

    @Override
    public Inventory save(Inventory inventory) {
        final InventoryEntity entity = InventoryEntity.fromDomainModel(inventory);
        inventoryJpaRepo.save(entity);
        return InventoryEntity.toDomainModel(entity);
    }

    @Override
    public List<Inventory> findAll() {
        List<InventoryEntity> entities = inventoryJpaRepo.findAll();
        return entities.stream().map(inventoryEntity -> {
            Inventory inventory = InventoryEntity.toDomainModel(inventoryEntity);
            return inventory;
        }).collect(Collectors.toList());
    }

    @Override
    public Inventory getById(int id) {
        return InventoryEntity.toDomainModel(inventoryJpaRepo.findById(id).orElseThrow(() -> new InventoryNotFoundException(id)));
    }
}
