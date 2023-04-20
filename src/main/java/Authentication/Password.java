package Authentication;

public class Password{

    private String password;
    private String strength;

    public Password(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return password;
    }

    // returns password as string
    public String getPassword() {
        return password;
    }

    // sets password strength
    public void setStrength(String strength){
        this.strength = strength;
    }

    // returns password strength
    public String getStrength(){
        return strength;
    }
}
