package ui.window;

import javax.swing.*;
import java.awt.*;

public class BasicWindow {

    public static JFrame basicWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800,600);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());
        window.setTitle("Sistema de turnos medicos - IGANCIO 5000");
        return window;
    }

}
