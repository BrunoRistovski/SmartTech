package smart.tech.com.SmartTech.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException
{
    public ShoppingCartNotFoundException()
    {
        super("ShoppingCart not found");
    }
}
