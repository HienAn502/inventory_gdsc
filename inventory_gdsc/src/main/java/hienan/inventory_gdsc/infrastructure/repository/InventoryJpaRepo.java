package hienan.inventory_gdsc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepo extends JpaRepository<InventoryEntity, Integer> {
}
