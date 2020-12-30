package View;

import javax.swing.JPanel;

import Controller.MainWindowController;

public class View extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected MainWindow mainWindow;
    protected MainWindowController controller;

    View(MainWindow mainWindow, MainWindowController controller) {
        this.mainWindow = mainWindow;
        this.controller = controller;
    }
}
