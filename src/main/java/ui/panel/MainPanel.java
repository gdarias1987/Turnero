package ui.panel;

import ui.dialog.DialogService;
import ui.panel.medico.MedicoPanel;
import ui.style.BasicStyle;
import ui.window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    private JPanel panel;
    private JButton btnMenuMedicos;

    private JButton btnTest;

    private DialogService dialog;

    public final JPanel getPanel() {
        return panel;
    }

    public MainPanel() {
        dialog = new DialogService();

        panel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        panel.setLayout(layout);
        panel.setBackground(Color.lightGray);

        btnMenuMedicos = BasicStyle.getMenuBtn("Menú medicos");
        btnMenuMedicos.addActionListener((e)->{
            MainWindow.changePage(new MedicoPanel().getPanel());
        });

        btnTest = BasicStyle.getMenuBtn("TEST");
        btnTest.addActionListener(e -> {
//            int dialogResult = dialog.showErrorMessage("Confirmar acción","Se procedera a realizar la siguiente acción, ¿esta seguro?");
//            System.out.println(String.format("Result: %s",dialogResult));
//            if(dialogResult == 0) {
//                System.out.println("Yes option");
//            } else {
//                System.out.println("No Option");
//            }
            dialog.showOkMessage("Titulo", "Msg");
        });

        panel.add(BasicPanel.createHeaderPanel("Menú inicio"));

        JPanel div = new JPanel();
        div.setPreferredSize(new Dimension(800, 100));
        div.add(btnMenuMedicos, BorderLayout.CENTER);
        panel.add(div);

        JPanel div1 = new JPanel();
        div1.setPreferredSize(new Dimension(800, 100));
        div1.add(btnTest, BorderLayout.CENTER);
        panel.add(div1);

//        panel.add(btnMenuMedicos);
//        panel.add(btnTest);
    }


}
