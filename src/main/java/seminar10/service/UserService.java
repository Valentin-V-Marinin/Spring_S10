package seminar10.service;

import seminar10.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, String name);
    void deleteUser(Long id);

    User findUserByEmail(String email);

}
