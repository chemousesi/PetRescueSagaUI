package View;

import javax.sound.sampled.*;
import java.net.URL;

public class AudioGame {
    private Clip clip;

    public void lanceMusique() {
        try {
            URL url = getClass().getResource("petMusique.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void stopMusique() {
        clip.close();
    }
}
