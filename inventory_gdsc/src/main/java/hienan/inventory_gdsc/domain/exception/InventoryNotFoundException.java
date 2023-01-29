package hienan.inventory_gdsc.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends RuntimeException{
    private int inventory_id;

    public InventoryNotFoundException(int inventory_id) {
        super(String.format("Inventory not found with id : %s", inventory_id));
        this.inventory_id = inventory_id;
    }
}
