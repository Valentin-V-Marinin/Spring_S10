package seminar10.service;


import org.springframework.stereotype.Service;
import seminar10.entity.Session;
import seminar10.entity.User;
import seminar10.repository.SessionRepository;
import seminar10.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService  {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final HashCountService hashCountService;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, HashCountService hashCountService) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.hashCountService = hashCountService;
    }

    public Session login(String email, String password) {
        User user = userRepository.findByEmail(email);
        Session session = sessionRepository.findByUserId(user.getId());
        String pwd = hashCountService.alterHash(password);
        try {
            if (user != null) {
                if (session == null) {
                    session = new Session();
                    session.setId(0L);
                    session.setUuid(UUID.randomUUID());
                    session.setUserId(user.getId());
                    sessionRepository.save(session);
                } else {
                    throw new RuntimeException("This user already logged in!");
                }
            } else {
                throw new RuntimeException("User not found! Try to register new user.");
            }
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
        return session;
    }

    public User register(User user){
        try{
        user.setPassword(hashCountService.alterHash(user.getPassword()));
        userRepository.save(user);
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
        return user;
    }

    public String logout(Long userId){
        Session session = sessionRepository.findByUserId(userId);
        if (session != null) {
            sessionRepository.deleteById(session.getId());
        } else {
            throw new RuntimeException("This user isn't logged.");
        }
        return "User logged out.";
    }

    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }

}
