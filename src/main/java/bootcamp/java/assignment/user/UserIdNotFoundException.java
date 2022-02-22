package bootcamp.java.assignment.user;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(int id) {
        super(String.valueOf(id));
    }
}
