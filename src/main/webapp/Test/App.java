import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        
         Map<String,String> rules = new HashMap<>();

        Pattern fullNamePattern = Pattern.compile("^[A-Z][a-z]+\\s[A-Z][a-z]+"); // <------Good
        Pattern phoneNumberPattern = Pattern.compile("[\\d/./+/(/)/-]+"); // <------Good
        Pattern emailPattern = Pattern.compile("\\A[\\S]+[/@][\\S]+[/.][\\S]+\\z"); // <------Good
        Pattern passwordPattern = Pattern.compile("\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z"); //<--Good
    
        // Matcher phoneNumbMatcher = passwordPattern.matcher("$akAftft8g");
        // boolean matchFinder = phoneNumbMatcher.matches();
        //System.out.println(matchFinder);

        rules.put("fullName", "^[A-Z][a-z]+\\s[A-Z][a-z]+");
        rules.put("phoneNumber", "[\\d/./+/(/)/-]+");
        rules.put("emailAddress", "\\A[\\S]+[/@][\\S]+[/.][\\S]+\\z");
        rules.put("password", "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z");
        rules.put("passwordConfirm", "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z");

        Map<String,String> data = new HashMap<>();
        data.put("fullName", "Alexandru Neculai");
        data.put("phoneNumber", "+373696969");
        data.put("emailAddress", "aadad@erd.so");
        data.put("password", "$Atap456");
        data.put("passwordConfirm", "$Atap456");



        Validator validator = new Validator();
        Object object = validator.validate(data, validator.getAccountSignUprules());
        System.out.println(object);
    }
}
