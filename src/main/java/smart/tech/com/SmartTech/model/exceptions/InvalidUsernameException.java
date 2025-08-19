package smart.tech.com.SmartTech.model.exceptions;

public class InvalidUsernameException extends RuntimeException{

    public InvalidUsernameException(String username) {
        super(String.format("Username is already taken: %s", username));
    }
}
