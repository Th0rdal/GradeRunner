/**
 * This class takes care of miscellaneous tasks that are needed in multiple classes
 * encryption
 * saving and loading files
 * loading images
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {

    public static String encryptName(String name, long time) {  //this method hashes a string
        String temp = "GradeRunner" + name;
        long tempAsInt = 0;
        for (int i = 0; i < temp.length(); i++) {
            tempAsInt += temp.charAt(i);
        }
        tempAsInt = tempAsInt * time;
        return Long.toString(tempAsInt);
    }

    public static String encryptName(SaveFile sf) { //this method hashes the name of a saveFile
        String temp = "GradeRunner" + sf.getName();
        long tempAsInt = 0;
        for (int i = 0; i < temp.length(); i++) {
            tempAsInt += temp.charAt(i);
        }
        tempAsInt = tempAsInt * sf.getCreationTime();
        return Long.toString(tempAsInt);
    }

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

    public static Object loadObjectFromFile(String path) {   //loads a level from a file
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void saveObjectToFile(Object object, String path) {   //saves an object to the file
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(object);
            outputStream.close();
            fileOutputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static File[] loadFileContent(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Image could not be loaded!");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean mouseOverBox(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return (my > y && my < y + height);
        } else {
            return false;
        }
    }
}

