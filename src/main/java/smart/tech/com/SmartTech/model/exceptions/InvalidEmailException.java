package smart.tech.com.SmartTech.model.exceptions;

public class InvalidEmailException extends RuntimeException
{
    public InvalidEmailException(String email)
    {
        super(String.format("Email is already in use: %s", email));
    }
}
