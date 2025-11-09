package smart.tech.com.SmartTech.model.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Order not found");
    }
}
