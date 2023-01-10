import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Audio {
    void playMusic(String path){
        try {
            File musicPath = new File(path);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-0.0f);
                clip.start();
            }
            else{
                System.out.println("Couldn't find Music file");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
