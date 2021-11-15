package ui.dialog;

import ui.window.MainWindow;
import utils.FileLoader;

import javax.swing.*;

public class DialogService {

    public DialogService() {}

    public  int showOkMessage(String title, String message) {
        String[] options = new String[] {"Aceptar"};
        ImageIcon icon = new ImageIcon(FileLoader.getImage("ok48"));
        int option =  JOptionPane.showOptionDialog(null, "Title", "Message",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);
        return option;
    }

    public int showErrorMessage(String title, String message) {
        String[] options = new String[] {"Cerrar"};
        ImageIcon icon = new ImageIcon(FileLoader.getImage("error48"));
        int option =  JOptionPane.showOptionDialog(MainWindow.window, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);
        return option;
    }

    public int showInformationMessage(String title, String message) {
        String[] options = new String[] {"Aceptar"};
        ImageIcon icon = new ImageIcon(FileLoader.getImage("info48"));
        int option =  JOptionPane.showOptionDialog(MainWindow.window, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);
        return option;
    }

    public int showWarningMessage(String title, String message) {
        String[] options = new String[] {"Aceptar"};
        ImageIcon icon = new ImageIcon(FileLoader.getImage("warning48"));
        int option =  JOptionPane.showOptionDialog(MainWindow.window, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);
        return option;
    }

    public  int showConfirmationMessage(String title, String message, String btnText) {
        String[] options = new String[] {btnText, "Volver"};
        ImageIcon icon = new ImageIcon(FileLoader.getImage("question48"));
        int option =  JOptionPane.showOptionDialog(null, "Title", "Message",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);
        return option;
    }

    public  int showCustomMessage(String title, String message) {
//        String[] options = new String[] {"Go ahead", "Go back", "Go forward", "close me"};
        String[] options = new String[] {"Confirmar", "Go back"};
        ImageIcon icon = new ImageIcon(FileLoader.getImage("inicio64"));
        int option =  JOptionPane.showOptionDialog(null, "Title", "Message",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);
        return option;
    }
}
