package ui.style;

import javax.swing.*;
import java.awt.*;

public class BasicStyle {

    public static Font getTitleFont() {
        Font font = new Font("Arial",Font.BOLD,30);
        return font;
    }





    public static JButton getBackBtn(String lblVolver) {
        JButton btn = new JButton(lblVolver);

        return btn;
    }
}
