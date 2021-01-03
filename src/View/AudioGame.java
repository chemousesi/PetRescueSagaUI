package View;

import javax.sound.sampled.*;
import java.net.URL;

public class AudioGame {
    private Clip clip;
    private Clip destructionClip;
    private Clip gagnerClip;
    private Clip perdreClip;

    public void lanceMusique() {
        try {
            URL url = getClass().getResource("petMusiqueMin.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void lancerSound(Clip c, String chemin) {
        try {
            URL url = getClass().getResource(chemin);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            c = AudioSystem.getClip();
            c.open(audioInputStream);

            c.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void lanceDestructionSound() {
        lancerSound(destructionClip, "paf2.wav");

    }

    public void lanceGangnerSound() {
        lancerSound(gagnerClip, "gagner.wav");
    }

    public void stopMusique() {
        clip.close();
    }

    public boolean musiqueEnMarche() {
        if (clip == null) {
            return false;
        } else {
            return clip.isOpen();
        }
    }
}
