package seminar10.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import seminar10.entity.Session;
import seminar10.entity.User;
import seminar10.repository.SessionRepository;
import seminar10.repository.UserRepository;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private HashCountService hashCountService;


    @BeforeEach
    public void setup(){
        this.authService = new AuthService(userRepository, sessionRepository, hashCountService);
    }

    @Test
    public void testRegister(){

        User user = new User();
        authService.register(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void testLogin(){

        String username = "Petroff";
        String email = "pit@mail.com";
        String password = hashCountService.alterHash("pitt");
        User mockUser = new User(1L, username, email, password);

        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        Session mockSession = authService.login(email, password);

        Mockito.verify(sessionRepository, Mockito.times(1)).save(mockSession);
    }

    @Test
    public void testLogout(){

        Session mockSession = new Session();
        mockSession.setUserId(1L);

        when(sessionRepository.findByUserId(1L)).thenReturn(mockSession);
        authService.logout(1L);

        Mockito.verify(sessionRepository, Mockito.times(1)).deleteById(mockSession.getId());

    }

}
