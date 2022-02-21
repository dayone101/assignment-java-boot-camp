package bootcamp.java.assignment.user;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_found() {
        // Arrange
        User user = new User();
        user.setUsername("testing");
        userRepository.save(user);

        // Act
        Optional<User> result = userRepository.findByUsername("testing");

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void findByUsername_not_found() {
        // Act
        Optional<User> result = userRepository.findByUsername("testing");

        // Assert
        assertFalse(result.isPresent());
    }
}