package smart.tech.com.SmartTech.model.exceptions;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super(String.format("User with username: %s was not found", username));
    }

}
