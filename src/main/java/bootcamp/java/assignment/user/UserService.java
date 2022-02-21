package bootcamp.java.assignment.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        if (result.isPresent()) {
            return result.get();
        }

        throw new UserNotFoundException(username);
    }

}
