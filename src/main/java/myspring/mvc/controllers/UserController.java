package myspring.mvc.controllers;

import myspring.mvc.DTO.UserDTO;
import myspring.mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void addUser(@RequestBody UserDTO request) {
        userService.addUser(request);
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
