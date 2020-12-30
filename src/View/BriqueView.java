package View;

import java.awt.Color;
import java.util.*;
import javax.swing.JComponent;
import java.awt.Graphics.*;
import Model.Movible.Brique;
import Model.Movible.Couleur;
import java.awt.*;

public class BriqueView extends Rectangle {
    Brique brique;
    Color color;

    public BriqueView(int x, int y, int width, int height, Color c) {
        super(x, y, width, height);

        color = c;
    }

}
