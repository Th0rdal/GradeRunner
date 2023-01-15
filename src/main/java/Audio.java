import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Audio {
    private String audioPath;
    private AudioInputStream audioInput;
    private Clip clip;
    private FloatControl floatControl;
    public Audio(String path) {
        this.audioPath = path;
        try {
            File musicPath = new File(this.audioPath);
            if(musicPath.exists()){
                this.audioInput = AudioSystem.getAudioInputStream(musicPath);
                this.clip = AudioSystem.getClip();
                this.clip.open(this.audioInput);
                this.floatControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }
            else{
                System.out.println("Couldn't find Music file");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        this.changeVolume(0.0f);
    }
    public void changeVolume(float volume) {
        this.floatControl.setValue(volume);
    }
    public void mute() {
        this.clip.stop();
    }
    public void unmute() {
        this.clip.start();
    }
    public boolean musicPlaying() {
        return this.clip.isActive();
    }
}
