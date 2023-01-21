package Utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileHandler {
    public static Object loadObjectFromFile(String path) {   //loads a level from a file
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println(path);
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
}
