
package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
   
    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public static boolean checkPassword(String givenPassword, String userPassword) {
        return BCrypt.checkpw(givenPassword, userPassword);
    }
}
