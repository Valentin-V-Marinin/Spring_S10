package seminar10.service;

import org.springframework.stereotype.Service;
import seminar10.entity.User;
import seminar10.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public final HashCountService hashCountService;

    public UserServiceImpl(UserRepository userRepository, HashCountService hashCountService) {
        this.userRepository = userRepository;
        this.hashCountService = hashCountService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setPassword(hashCountService.alterHash(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Such user already exists!");
        }
    }

    @Override
    public User updateUser(Long id, String name) {
        User user = getUserById(id);
        user.setUsername(name);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
