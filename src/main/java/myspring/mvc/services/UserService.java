package myspring.mvc.services;

import myspring.database.JPA.entities.User;
import myspring.database.JPA.repositories.UserRepository;
import myspring.mvc.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("myspring.config")
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void addUser(UserDTO userDTO) {
        userRepository.save(
                new User(
                        userDTO.getLogin(),
                        passwordEncoder.encode(userDTO.getPassword())
                )
        );
    }

    public void updateUserPassword(String login, String newPassword) {
        userRepository.updatePassword(login, newPassword);
    }

    public void deleteUser(UserDTO userDTO) {
        userRepository.delete(new User(userDTO.getLogin(), userDTO.getPassword()));
    }

    public UserDTO checkPassword(UserDTO userDTO){
        User user = userRepository.findUserByLogin(userDTO.getLogin());
        if(user!=null){
            if (passwordEncoder.matches
                    (userDTO.getPassword(),
                    user.getPassword())
            )
                return userDTO;
        }
        return null;
    }

}
