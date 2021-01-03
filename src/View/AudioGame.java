package View;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;

import javax.swing.*;

public class AudioGame {

    public void lanceMusique() {
        try {
            URL url = getClass().getResource("petMusique.wav");
            System.out.println(url);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
