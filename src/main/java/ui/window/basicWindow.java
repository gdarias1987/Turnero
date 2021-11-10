package ui.window;

import javax.swing.*;

public class basicWindow {
    JFrame window;

    public basicWindow() {
        this.window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800,600);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
    }

    public void show() {
        window.setVisible(true);
    }

    public void hide() {
        window.setVisible(true);
    }

}
