package myspring.mvc.controllers;

import myspring.mvc.DTO.InfoDTO;
import myspring.mvc.DTO.TokenDTO;
import myspring.mvc.DTO.UserDTO;
import myspring.mvc.services.UserService;
import myspring.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserDTO checkAccess(@PathVariable String username) {

        return new UserDTO(userService.getUserByLogin(username).getLogin(), null);
    }

    @PostMapping("/new")
    public InfoDTO addUser(@RequestBody UserDTO request) {
        if (userService.addUser(request))
            return new InfoDTO("success", "user was created");
        return new InfoDTO();
    }

    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody UserDTO request) {
        UserDTO userDTO = userService.checkPassword(request);
        if (userDTO != null) {
            return new TokenDTO(jwtProvider.generateToken(userDTO.getLogin()));
        } else {
            return new TokenDTO("failure");
        }
    }

    @PatchMapping("/{username}")
    public InfoDTO editPassword(@PathVariable String username, @RequestBody UserDTO request) {
        if (userService.updateUserPassword(username, request.getPassword()))
            return new InfoDTO("success", "password was updated");
        return new InfoDTO();
    }

    @DeleteMapping("/{username}")
    public InfoDTO deleteUser(@PathVariable String username) {
        if (userService.deleteUser(username))
            return new InfoDTO("success", "user was deleted");
        return new InfoDTO();
    }

}
