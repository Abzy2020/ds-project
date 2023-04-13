package Authentication;

public class Password {
    private String password;
    private String strength;

    public Password(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return password;
    }

    // getters and setters
    public String getPassword() {
        return password;
    }

    public void setStrength(String strength){
        this.strength = strength;
    }

    public String getStrength(){
        return strength;
    }
}
