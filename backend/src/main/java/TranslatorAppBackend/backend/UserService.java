package TranslatorAppBackend.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User newUser) {
        if (newUser.getUsername() == null || newUser.getPassword() == null) {
            return null;
        }

        return userRepository.save(newUser);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        // This implies that the user that someone is trying to delete
        // does not exist so it cannot be deleted

        return false;
    }

    public Optional<User> findUserByUsername(String username, String password) {
        try {
            return userRepository.findByUsernameandPassword(username, password);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find a user with the username: " + username);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        try {
        return userRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find a user with the id: " + id);
        }
    }

    public Optional<User> changeUserInfo(Long id, User newUserInfo) {
        try {
        Optional<User> foundUsers = userRepository.findById(id);
        if (foundUsers.isEmpty()) {
                User foundUser = foundUsers.get();
                foundUser.setUsername(newUserInfo.getUsername());
                foundUser.setPassword(newUserInfo.getPassword());
                User updatedUser = userRepository.save(foundUser);
                return Optional.of(updatedUser);
        }
        } catch (Exception e) {
            throw new RuntimeException("Unable to find a user with the id: " + id);
        }

        return Optional.empty();
    }

}
