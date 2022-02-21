package bootcamp.java.assignment.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(username);
    }
}
