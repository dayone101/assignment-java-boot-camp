package bootcamp.java.assignment.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findByUsername_found() {
        // Arrange
        User user = new User();
        user.setUsername("testing");
        user.setAddress("bangkok");
        when(userRepository.findByUsername("testing")).thenReturn(Optional.of(user));

        // Act
        User result = testRestTemplate.getForObject("/user/testing", User.class);

        // Assert
        assertEquals(user.getAddress(), result.getAddress());
    }

    @Test
    void findByUsername_not_found() {
        // Act
        String result = testRestTemplate.getForObject("/user/testing", String.class);

        // Assert
        assertEquals("username testing not found", result);
    }
}