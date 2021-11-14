package ui.style;

import utils.FileLoader;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.text.DecimalFormat;

public class BasicStyle {

    public static Font getTitleFont() {
        Font font = new Font("Arial",Font.BOLD,30);
        return font;
    }

    public static Font getMenuBtnFont() {
        Font font = new Font("Arial",Font.BOLD,15);
        return font;
    }

    public static JButton getBackBtn(String lblVolver) {
        JButton btn = new JButton(lblVolver);
        return btn;
    }

    public static JButton getMenuBtn(String title) {
        JButton btn = new JButton(title);
        btn.setFont(BasicStyle.getMenuBtnFont());
        btn.setPreferredSize(new Dimension(300,80));
        return btn;
    }

    public static JButton getCustomBtn(String title, int width, int height) {
        JButton btn = new JButton(title);
        btn.setFont(BasicStyle.getMenuBtnFont());
        btn.setPreferredSize(new Dimension(width,height));
        return btn;
    }

    public static JButton getTableBtn(String title) {
        JButton btn = new JButton(title);
        btn.setFont(BasicStyle.getMenuBtnFont());
        btn.setPreferredSize(new Dimension(300,50));
        return btn;
    }

    public static JLabel getHeaderTitle(final String title) {
        JLabel lblTitulo = new JLabel(title);
        lblTitulo.setIcon(new ImageIcon(FileLoader.getIcon("inicio64")));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setVerticalAlignment(JLabel.CENTER);
        lblTitulo.setFont(BasicStyle.getTitleFont());
        lblTitulo.setForeground(Color.lightGray);
        return lblTitulo;
    }

    public static Font getCustomFont(int size) {
        Font font = new Font("Arial",Font.BOLD, size);
        return font;
    }

    public static String floatFormatted(float value) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(value);
    }


}
