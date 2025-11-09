package smart.tech.com.SmartTech.model.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Passwords do not match");
    }
}
