package smart.tech.com.SmartTech.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.tech.com.SmartTech.model.DTO.LoginResponseDTO;
import smart.tech.com.SmartTech.model.DTO.UserDTO;
import smart.tech.com.SmartTech.model.domain.User;
import smart.tech.com.SmartTech.model.exceptions.InvalidCredentialsException;
import smart.tech.com.SmartTech.services.interfaces.UserService;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserDTO userDTO) {
        try {
            return userService.createToken(userDTO)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidCredentialsException::new);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/edit/{username}")
    public ResponseEntity<User> editUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        return userService.editUser(username, userDTO)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
