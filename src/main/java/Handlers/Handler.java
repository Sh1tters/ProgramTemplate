package Handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handler {

    public void Login(String username) {
        //TODO go to dashboard with username. We will only transfer the username because our dashboard panel only depends on username.
    }

    public Boolean IsPasswordGood(String password) {
        Pattern p = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", Pattern.CASE_INSENSITIVE);
        /** Pattern wants:
         * At least one upper case English Letter, (?=.*?[A-Z])
         * At least one lower case English Letter, (?=.*?[a-z])
         * At least one digit, (?=.*?[0-9])
         * At least one special character, (?=.*?[#?!@$%^&*-])
         * Minimum eight in length .{8,} (with the anchors)
         * */

        /** Check if pattern matches password */
        Matcher matcher = p.matcher(password);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public String PasswordDecryption(String password) {

        return password;
    }

    public String PasswordEncryption(String password) {


        return password;
    }
}
