package ui.panel;

import ui.style.BasicStyle;
import utils.FileLoader;

import javax.swing.*;
import java.awt.*;

public class BasicPanel extends JPanel {

    public JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public BasicPanel() {
        panel = new JPanel();
    }

    public static JPanel createBlankPage() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.black);

        contentPane.setVisible(true);
        return contentPane;
    }

    public static JPanel createBasicPanel() {
        JPanel panel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        panel.setLayout(layout);
        panel.setBackground(Color.lightGray);
        return panel;
    }



    public static JPanel createHeaderPanel(String title) {
        JPanel contentPane = new JPanel(new BorderLayout());

        contentPane.setBackground(Color.darkGray);
        contentPane.setPreferredSize(new Dimension(800, 80));

        JLabel lblTitulo = new JLabel(title);
        lblTitulo.setIcon(new ImageIcon(FileLoader.getIcon("inicio64")));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setVerticalAlignment(JLabel.CENTER);
        lblTitulo.setFont(BasicStyle.getTitleFont());
        lblTitulo.setForeground(Color.lightGray);

        contentPane.add(lblTitulo, BorderLayout.CENTER);
        return contentPane;
    }

}

