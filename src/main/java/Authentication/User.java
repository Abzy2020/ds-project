package Authentication;

public class User{

    private String username;
    private Password password;
    private Email email;

    public User(String username, Password password, Email email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    // returns all user info as string
    public String getUserInfo(){
        return "Username: " + username + "\nPassword: " + password.getPassword() + "\nEmail: " + email.getEmail();
    }

    // returns username as string
    public String getUsername() {
        return username;
    }

    // returns password as string
    public String getPassword() {
        return password.getPassword();
    }

    // returns the password object
    public Password getUserPassword(){
        return password;
    }

    // returns email as string
    public String getEmail() {
        return email.getEmail();
    }
}
