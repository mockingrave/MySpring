package myspring.mvc.services;

import myspring.database.JPA.entities.User;
import myspring.database.JPA.repositories.UserRepository;
import myspring.mvc.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
        if (userRepository.getUserByLogin(userDTO.getLogin()) == null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public UserDTO getUserByLogin(String login) {
        return new UserDTO(userRepository.getUserByLogin(login).getLogin(), null);
    }

    public boolean updateUserPassword(String login, String newPassword) {
        userRepository.updatePassword(login, passwordEncoder.encode(newPassword));
        return true;
    }

    public boolean deleteUser(String login) {
        userRepository.deleteById(login);
        return true;
    }

    public UserDTO checkPassword(UserDTO userDTO) {
        User user = userRepository.getUserByLogin(userDTO.getLogin());
        if (user != null) {
            if (passwordEncoder.matches
                    (userDTO.getPassword(),
                            user.getPassword())
            )
                return userDTO;
        }
        return null;
    }

}
