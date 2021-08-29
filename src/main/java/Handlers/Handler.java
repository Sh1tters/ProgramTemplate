package Handlers;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Handler {

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

    public static String toHexString(byte[] hash) {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public byte[] PasswordEncryption(String password) throws NoSuchAlgorithmException {
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

}
