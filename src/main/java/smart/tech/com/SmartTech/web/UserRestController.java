package smart.tech.com.SmartTech.web;

import org.springframework.web.bind.annotation.*;
import smart.tech.com.SmartTech.services.interfaces.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


}
