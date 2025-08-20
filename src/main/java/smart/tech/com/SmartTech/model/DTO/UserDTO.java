package smart.tech.com.SmartTech.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {


    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;
}
