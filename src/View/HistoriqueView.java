package View;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import Controller.MainWindowController;
import Model.Environnement.ElementHistorique;

public class HistoriqueView extends View {

    /**
     * cette vue représente l'historique des niveau passés par un joueur avec les
     * scores affichés
     */

    private static final long serialVersionUID = 1L;
    DefaultListModel<String> listModel;
    JList<String> list;
    private JButton retour = new JButton("Retour");

    HistoriqueView(MainWindow mainWindow, MainWindowController controller) {
        super(mainWindow, controller);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public void initialise() {

        // utilisation d'une liste
        listModel = new DefaultListModel<String>();
        this.removeAll();
        JLabel title = new JLabel("Historique", new ImageIcon("imgs/historique.png"), 0);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 35));
        this.add(title);
        this.revalidate();
        remplireListModel();
        list = new JList<String>(listModel);
        list.setVisibleRowCount(10);
        // utilisationde scroll pane
        JScrollPane listScrollPane = new JScrollPane(list);
        this.add(listScrollPane, BorderLayout.CENTER);
        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.add(retour);

        this.retour.addActionListener((e) -> {
            this.mainWindow.getCardLayout().show(this.mainWindow.getJContentPane(), "3");
        });
    }

    private void remplireListModel() {
        /**
         * dans cette fonction on remplit la liste de la vue à partir des données dans
         * le model
         */
        for (ElementHistorique eltHis : this.controller.getJoueur().getHistorique()) {
            listModel.addElement(eltHis.toString());

        }
    }

}
