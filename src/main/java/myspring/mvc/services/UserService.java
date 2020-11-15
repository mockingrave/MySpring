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


    public boolean addUser(UserDTO userDTO) {
        User user = new User(userDTO.getLogin(), passwordEncoder.encode(userDTO.getPassword()));
        if(userRepository.findUserByLogin(userDTO.getLogin())==null){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean updateUserPassword(String login, String newPassword) {
        userRepository.updatePassword(login, passwordEncoder.encode(newPassword));
        return true;
    }

    public boolean deleteUser(String login) {
        userRepository.deleteById(login);
        return true;
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
