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

    public Object getPassword() {
        return password.toString();
    }

    public String getEmail() {
        return email.toString();
    }

    public String getUsername() {
        return username;
    }

    

    // getters and setters
    public String getUserInfo(){
        return "Username: " + username + "\nPassword: " + password.getPassword() + "\nEmail: " + email.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getEmail() {
        return email.getEmail();
    }
}
