package myspring.mvc.services;

import myspring.database.JPA.entities.User;
import myspring.database.JPA.repositories.UserRepository;
import myspring.mvc.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserDTO userDTO) {
        userRepository.save(new User(userDTO.getLogin(), userDTO.getPassword()));
    }

    public void updateUserPassword(String login, String newPassword) {
        userRepository.updatePassword(login, newPassword);
    }

    public void deleteUser(UserDTO userDTO) {
        userRepository.delete(new User(userDTO.getLogin(), userDTO.getPassword()));
    }

}
