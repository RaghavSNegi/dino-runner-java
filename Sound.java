import javax.sound.sampled.*;
import java.io.File;

public class Sound {

    public static void play(String filename) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.out.println("Sound error: " + filename);
        }
    }
}