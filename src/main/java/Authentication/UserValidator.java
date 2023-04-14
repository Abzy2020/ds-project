package Authentication;

public class UserValidator{

    private String username;
    private String password;
    private Password userPassword;
    private String email;
    private String tempPass;
    
    private boolean isEightChars = false;
    private boolean containsNum = false;
    private boolean containsUpper = false;
    private boolean containsLower = false;
    private boolean containsSpecial = false;
    
    private Node root;
    
    public UserValidator(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userPassword = user.getUserPassword();
        this.email = user.getEmail();
        this.tempPass = "";
    }


    public class Node {
        char value; // for characters of password
        Node left;
        Node right;

        Node (char value) {
            this.value = value;
            left = null;
            right = null;
        }
    }


    public Node addNode(Node node, char value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = addNode(node.left, value);
        } else if (value > node.value) {
            node.right = addNode(node.right, value);
        } else {
            // value exists in tree
            return node;
        }
        return node;
    }


    public void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            tempPass += node.value;
            inorderTraversal(node.right);
        }
    }


    // tests password for strength
    public int testStrength(String password){
        boolean[] tests = {isEightChars, containsLower, containsUpper, containsNum, containsSpecial};
        int score = 0;
        //test password length
        if(password.length() >= 8){
            isEightChars = true;
        }
        //test for upper and lower case, digit, and special
        for(int i = 0; i < password.length(); i++){
            if(Character.isUpperCase(password.charAt(i))){
                containsUpper = true;
            } else if(!Character.isUpperCase(password.charAt(i)) && Character.isLetter(password.charAt(i))){
                containsLower = true;
            } else if(Character.isDigit(password.charAt(i))){
                containsNum = true;
            } else if (!Character.isLetterOrDigit(password.charAt(i))){
                containsSpecial = true;
            }
        }
        //count score
        for (int i = 0; i < tests.length; i++){
            if(tests[i] == true){
                score++;
            }
        }
        return score;
    }

    // sets strength of password
    public void setPassStrength(){
        int score = 0;
        // strips password of duplicate values
        for (int i = 0; i < password.length(); i++) {
            root = addNode(root, password.charAt(i));
        }
        inorderTraversal(root);
        score = testStrength(tempPass);
        if (score <= 2){
            userPassword.setStrength("Weak");
        } else if (score > 2 && score <= 4){
            userPassword.setStrength("Medium");
        } userPassword.setStrength("Strong");
    }

    public static void main(String[] args) {
        Password password = new Password("password");
        Email email = new Email("email");
        User user = new User("username", password, email);
        UserValidator validator = new UserValidator(user);
    }
}
