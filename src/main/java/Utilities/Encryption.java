package Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    public static String encryptWorld(long creationTime, String name) {    //this method encrypts a level with SHA-512
        String time = Long.toString(creationTime);
        int nameAsInt = 0;
        for (int i = 0; i < name.length(); i++) {
            nameAsInt = name.charAt(i);
        }
        String encryptedString = time + name + nameAsInt;

        //SHA-512 algorithm
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(encryptedString.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hash = new StringBuilder(no.toString(16));
            while (hash.length() < 32) {
                //hash = "0" + hash;
                hash.insert(0, '0');
            }

            return hash.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
