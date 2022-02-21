package bootcamp.java.assignment.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        // Arrange
        User user = new User();
        user.setUsername("testing");
        user.setAddress("bangkok");
        when(userRepository.findByUsername("testing")).thenReturn(Optional.of(user));

        // Act
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        User result = userService.findByUsername("testing");

        // Assert
        assertEquals(user.getAddress(), result.getAddress());
    }
}