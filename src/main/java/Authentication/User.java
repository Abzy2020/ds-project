package Authentication;

public class User {
    private String username;
    private Password password;
    private Email email;

    public User(String username, Password password, Email email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // getters and setters
}
