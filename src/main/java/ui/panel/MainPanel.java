package ui.panel;

import ui.window.MedicoWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
//        setBackground(Color.lightGray);
        setBackground(Color.green);

        JButton btnMedicos = new JButton("MENU MEDICOS");

        btnMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicoWindow window = new MedicoWindow();

            }
        });

        add(btnMedicos, FlowLayout.LEFT);



        JButton btnPacientes = new JButton("MENU PACIENTES");
        add(btnPacientes, FlowLayout.RIGHT);
    }


}
