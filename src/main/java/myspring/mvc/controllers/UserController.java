package myspring.mvc.controllers;

import myspring.mvc.DTO.UserDTO;
import myspring.mvc.services.UserService;
import myspring.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void addUser(@RequestBody UserDTO request) {
        userService.addUser(request);
    }

    @PostMapping("/auth")
    public UserDTO auth(@RequestBody UserDTO request) {
        UserDTO userDTO = userService.checkPassword(request);
        String token = jwtProvider.generateToken(userDTO.getLogin());
        return new UserDTO(token, null);
    }

    @PatchMapping("/{login}")
    public void editPassword(@PathVariable String login, @RequestBody UserDTO request) {
        if (request.getLogin() == null) {
            userService.updateUserPassword(login, request.getPassword());
        } else {
            userService.updateUserPassword(request.getLogin(), request.getPassword());
        }
    }

    @DeleteMapping("/{login}")
    public void deleteUser(@PathVariable String login, @RequestBody UserDTO request) {
        userService.deleteUser(request);
    }

}
