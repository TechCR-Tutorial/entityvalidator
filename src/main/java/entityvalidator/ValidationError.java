package entityvalidator;

import java.util.Objects;

public class ValidationError {

    private String fieldName;
    private String errorDescription;
    private String actualValue;

    public ValidationError(String fieldName) {
        this.fieldName = fieldName;
    }

    public ValidationError(String fieldName, String errorDescription, String actualValue) {
        this.fieldName = fieldName;
        this.errorDescription = errorDescription;
        this.actualValue = actualValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationError that = (ValidationError) o;
        return Objects.equals(fieldName, that.fieldName) &&
                Objects.equals(errorDescription, that.errorDescription) &&
                Objects.equals(actualValue, that.actualValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, errorDescription, actualValue);
    }


    @Override
    public String toString() {
        return fieldName + " : " + errorDescription + " value:" + actualValue;
    }
}
