import javax.sound.sampled.*;
import java.io.File;

public class Sound {

    public static void play(String filename) {
        try {
            File file = new File("src/" + filename);

            if (!file.exists()) {
                System.out.println("File NOT found: " + file.getAbsolutePath());
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}