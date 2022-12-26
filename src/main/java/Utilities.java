/**
 * This class takes care of encryption and the saving of objects
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities { //calculates a hash for the saveFile

    public static String encryptName(String name, long time) {  //this method hashes a string
        String temp = "GradeRunner" + name;
        long tempAsInt = 0;
        for (int i = 0; i < temp.length(); i++) {
            tempAsInt += (long)temp.charAt(i);
        }
        tempAsInt = tempAsInt * time;
        String encryptedString = Long.toString(tempAsInt);
        return encryptedString;
    }

    public static String encryptName(SaveFile sf) { //this method hashes the name of a saveFile
        String temp = "GradeRunner" + sf.getName();
        long tempAsInt = 0;
        for (int i = 0; i < temp.length(); i++) {
            tempAsInt += (long)temp.charAt(i);
        }
        tempAsInt = tempAsInt * sf.getCreationTime();
        String encryptedString = Long.toString(tempAsInt);
        return encryptedString;
    }

    public static String encryptWorld(Level l) {    //this method encrypts a level with SHA-512
        String time = Long.toString(l.getCreationTime());
        String name = l.getName();
        String encryptedString = l.getEncryptedName();
        int nameAsInt = 0;
        for (int i = 0; i < name.length(); i++) {
            nameAsInt = (int)name.charAt(i);
        }
        String encyptedString = time + name + Integer.toString(nameAsInt);

        //SHA-512 algorithm
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(encyptedString.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Object loadObjectFromFile(String path) {   //loads a level from a file
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void saveObjectToFile(Object object, String path) {   //saves an object to the file
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(object);
            outputStream.close();
            fileOutputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

