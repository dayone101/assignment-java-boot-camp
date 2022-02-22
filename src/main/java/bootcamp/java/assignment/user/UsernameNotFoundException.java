package bootcamp.java.assignment.user;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super(username);
    }
}
