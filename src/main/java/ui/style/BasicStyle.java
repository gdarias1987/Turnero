package ui.style;

import utils.FileLoader;
import utils.JTextFloatFilter;
import utils.JTextIntegerFilter;
import utils.JTextUppercaseFilter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.DecimalFormat;

public class BasicStyle {

    /*
     * FONTS
     * */
    public static Font getTitleFont() {
        Font font = new Font("Arial",Font.BOLD,30);
        return font;
    }

    public static Font getMenuBtnFont() {
        Font font = new Font("Arial",Font.BOLD,15);
        return font;
    }

    public static Font getCustomFont(int size) {
        Font font = new Font("Arial",Font.BOLD, size);
        return font;
    }

    /*
     * BUTTONS
     * */
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

    public static JButton getCustomBtn(String title, int width, int height, int fontSize) {
        JButton btn = new JButton(title);
        btn.setFont(BasicStyle.getCustomFont(fontSize));
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

    public static JButton getBackBtn() {
        JButton btnBack = new JButton("");
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setOpaque(false);
        btnBack.setIcon(FileLoader.getImageIcon("left32"));
        return btnBack;
    }

    /*
     * GET HEADER MAIN MENU
     * */
    public static JLabel getHeaderTitle(final String title) {
        JLabel lblTitulo = new JLabel(title);
        lblTitulo.setIcon(new ImageIcon(FileLoader.getImage("inicio64")));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setVerticalAlignment(JLabel.CENTER);
        lblTitulo.setFont(BasicStyle.getTitleFont());
        lblTitulo.setForeground(Color.lightGray);
        return lblTitulo;
    }

    /*
     * FORMATEO DE STRINGS
     * */
    public static String floatFormatted(float value) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(value);
    }

    /*
    * JLabel basico
    * */
    public static JLabel getFormLbl(final String title, int w, int h, int fontSize) {
        JLabel label = new JLabel(title);
        label.setFont(BasicStyle.getCustomFont(fontSize));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(w, h));
        return label;
    }

    /*
     * JTextFields
     * */
    public static JTextField getStringFormTextField(String title, int w, int h, int fontSize) {
        JTextField textField = new JTextField(title);
        textField.setFont(BasicStyle.getCustomFont(fontSize));
        AbstractDocument firstNameDoc = (AbstractDocument) textField.getDocument();
        firstNameDoc.setDocumentFilter(new JTextUppercaseFilter());
        textField.setPreferredSize(new Dimension(w, h));
        return textField;
    }

    public static JTextField getIntFormTextField(String title, int w, int h, int fontSize) {
        JTextField textField = new JTextField(title);
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new JTextIntegerFilter());
        textField.setFont(BasicStyle.getCustomFont(fontSize));
        textField.setPreferredSize(new Dimension(w, h));
        return textField;
    }

    public static JTextField getFloatFormTextField(String title, int w, int h, int fontSize) {
        JTextField textField = new JTextField();
        textField.setDocument(new JTextFloatFilter(JTextFloatFilter.FLOAT));
        textField.setFont(BasicStyle.getCustomFont(fontSize));
        textField.setPreferredSize(new Dimension(w, h));
        textField.setText(title);
        return textField;
    }


}
