package ui.panel;

import ui.style.BasicStyle;
import ui.window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    private JPanel panel;
    private JButton btnMenuMedicos;

    public final JPanel getPanel() {
        return panel;
    }

    public MainPanel() {
        panel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        panel.setLayout(layout);
        panel.setBackground(Color.lightGray);

        btnMenuMedicos = BasicStyle.getMenuBtn("Menú medicos");
        btnMenuMedicos.addActionListener((e)->{
            MedicoPanel medicoPanel = new MedicoPanel();
            MainWindow.changePage(medicoPanel.getPanel());
        });

        panel.add(BasicPanel.createHeaderPanel("Menú inicio"));
        panel.add(btnMenuMedicos);
    }


}
