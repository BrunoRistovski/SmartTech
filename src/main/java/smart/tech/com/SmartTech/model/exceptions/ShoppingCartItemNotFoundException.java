package smart.tech.com.SmartTech.model.exceptions;

public class ShoppingCartItemNotFoundException extends RuntimeException
{
    public ShoppingCartItemNotFoundException()
    {
        super("ShoppingCartItem not found");
    }
}
