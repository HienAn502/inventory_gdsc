package hienan.inventory_gdsc.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInventoryItemException extends RuntimeException{
    private String fieldName;
    private double fieldValue;

    public InvalidInventoryItemException(String fieldName, double fieldValue) {
        super(String.format("Invalid Inventory Item: %s must be greater than 0 (%s = %s)", fieldName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
