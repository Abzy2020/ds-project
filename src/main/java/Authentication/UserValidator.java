package Authentication;

//Classifies a user's password as strong, medium, or weak
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

    // node for each character of password
    public class Node {
        char value;
        Node left;
        Node right;

        public Node (char value) {
            this.value = value;
            left = null;
            right = null;
        }
    }


    // adds node to tree
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

    // traverses the tree inorder
    public void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            tempPass += node.value;
            inorderTraversal(node.right);
        }
    }


    // tests password for strength
    // Logic:
    // A tree was used to remove duplicate characters from the password
    // 1. Pass must have eight unique characters
    // 2. Pass must contain at least one upper and lower case letter
    // 3. Pass must contain at least one digit
    // 4. Pass must contain at least one special character
    public int testStrength(String password){
        boolean[] tests = {isEightChars, containsLower, containsUpper, containsNum, containsSpecial};
        int score = 0;
        //test password length
        if(password.length() >= 8){
            isEightChars = true;
            tests[0] = true;
        }
        //test for upper and lower case, digit, and special
        for(int i = 0; i < password.length(); i++){
            if(Character.isUpperCase(password.charAt(i))){
                containsUpper = true;
                tests[2] = true;
            } 
            if(!(Character.isUpperCase(password.charAt(i))) && Character.isLetter(password.charAt(i))){
                containsLower = true;
                tests[1] = true;
            } 
            if(Character.isDigit(password.charAt(i))){
                containsNum = true;
                tests[3] = true;
            } 
            if (!(Character.isLetterOrDigit(password.charAt(i)))){
                containsSpecial = true;
                tests[4] = true;
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
        System.out.println(score);
        if (score <= 2){
            userPassword.setStrength("Weak");
        } else if (score > 2 && score <= 4){
            userPassword.setStrength("Medium");
        } else {
            userPassword.setStrength("Strong");
        }
    }

    // testing password strength checker works
    public static void main(String[] args) {
        Password password = new Password("TyB!Jghh^ap1");
        Email email = new Email("email");
        User user = new User("username", password, email);
        UserValidator validator = new UserValidator(user);
        validator.setPassStrength();
        System.out.println(validator.tempPass);
        System.out.println(validator.userPassword.getStrength());
    }
}
