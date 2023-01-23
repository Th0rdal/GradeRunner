/**
 * Handles loading of the audio file and control over the music (volumen and mute)
 */

package Settings;

import Essentials.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    private Clip clip;
    private FloatControl floatControl;
    public Audio(Game game, String path) {
        try {
            File musicPath = new File(path);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInput);
                this.floatControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }
            else{
                System.out.println("Couldn't find Music file");
            }
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException e){
            e.printStackTrace();
            game.getWindow().warning("Audio File could not be opened!");
        }catch (SecurityException e) {
            e.printStackTrace();
            game.getWindow().error("Security Error! Shutting down!");
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
