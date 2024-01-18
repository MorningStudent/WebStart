import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Validator {

    private Map<String,String> accountSignUprules;
    // Here can be maps with more types of rules that will be accessed as a parameter in the validate method

    public Validator() {
        accountSignUprules = new HashMap<>();
        accountSignUprules.put("fullName", "^[A-Z][a-z]+\\s[A-Z][a-z]+"); // <----------| Designed independently based on      
        accountSignUprules.put("phoneNumber", "[\\d/./+/(/)/-]+");        // <----------| exploring the Oracle documentation
        accountSignUprules.put("emailAddress", "\\A[\\S]+[/@][\\S]+[/.][\\S]+\\z");// <-| for Regex.
        accountSignUprules.put("password", "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z");
        accountSignUprules.put("passwordConfirm", "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z");
    }                                                                  // ^-----| The last one is adapted and inspired from stackoverflow

    public Map<String, String> getAccountSignUprules() { // <----Will be called as the second parameter of the validate method
        return accountSignUprules;
    }

    public Map<String, String> validate(Map<String,String> data, Map<String,String> rules) {
        Map<String, String> inputRectification = new HashMap<>();
        if (data.entrySet().stream().allMatch(e -> e.getValue().matches(rules.get(e.getKey())))==true) {
            // --> insert to DB                        
                                            // P.S. it got me some time to review the course about lambda expressions
        } else if (data.entrySet().stream().allMatch(e -> e.getValue().matches(rules.get(e.getKey())))==false) {
            inputRectification = data.entrySet().stream()
                                    .filter(e -> e.getValue().matches(rules.get(e.getKey())) == false)
                                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            for (String key : inputRectification.keySet()) {
                switch (key) {
                    case "fullName": inputRectification.replace("fullName", "First name must start with an uppercase, be separated by a space from the second name, that starts with an uppercase too! No symbols accepted!"); break;
                    case "phoneNumber": inputRectification.replace("phoneNumber", "Only digits and symbols: +().- are allowed! No space accepted!"); break;
                    case "emailAddress": inputRectification.replace("emailAddress", "Characters or symbols separated firstly by @(at sign) and secondly by .(dot)! No space accepted!"); break;
                    case "password": inputRectification.replace("password", "At least 8, including an Uppercase, a lowercase, a digit and a symbol @#$%^&+=! No space accepted!"); break;
                    case "passwordConfirm": inputRectification.replace("passwordConfirm", "At least 8, including an Uppercase, a lowercase, a digit and a symbol @#$%^&+=! No space and no other symbols are accepted!"); break;
                }
            }
        }
        return inputRectification;
    }

}