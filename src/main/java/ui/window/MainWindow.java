package ui.window;

import ui.panel.BasicPanel;
import ui.panel.MainPanel;
import utils.FileLoader;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public static JFrame window;
    public static JPanel panel;
    public static JButton btnBack;

    public MainWindow() {
        window = BasicWindow.basicWindow();
        btnBack = new JButton("BACK");

        this.panel = BasicPanel.createBlankPage();

        MainPanel mainPanel = new MainPanel();
        panel.add(mainPanel.getPanel(), BorderLayout.CENTER);

//        MedicoEditPanel medicoEditPanel = new MedicoEditPanel(1);
//        panel.add(medicoEditPanel.getPanel());

        window.add(panel, BorderLayout.CENTER);
        Image image = FileLoader.getImage("inicio");
        window.setIconImage(image);
    }

    public static void changePage(JPanel newPage) {
        MainWindow.panel.setVisible(false);
        MainWindow.panel.removeAll();
        MainWindow.panel.revalidate();
        MainWindow.panel.repaint();
        MainWindow.panel.add(newPage, BorderLayout.CENTER);
        MainWindow.panel.setVisible(true);
    }

}
