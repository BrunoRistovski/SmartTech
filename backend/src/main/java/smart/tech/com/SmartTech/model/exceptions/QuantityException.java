package smart.tech.com.SmartTech.model.exceptions;

public class QuantityException extends RuntimeException {
    public QuantityException() {
        super("Out of Stock");
    }
}
