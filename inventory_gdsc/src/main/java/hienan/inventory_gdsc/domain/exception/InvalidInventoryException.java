package hienan.inventory_gdsc.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class InvalidInventoryException extends RuntimeException{
    private String fieldName;
    private double fieldValue;

    public InvalidInventoryException(String fieldName, double fieldValue) {
        super(String.format("Invalid Inventory: %s must be greater than 0 (%s = %s)", fieldName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
