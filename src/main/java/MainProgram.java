import entidades.Medico;
import service.MedicoDataService;
import ui.window.MainWindow;
import utils.DateUtils;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MainProgram {

    public static void main(String[] args) throws IOException {
        loadNimbus();
        SwingUtilities.invokeLater(() -> {
            System.out.println(String.format("PROGRAM INIT - %s", DateUtils.getDateTimeString()));
            MainWindow mainWindow = new MainWindow();
            MainWindow.window.setVisible(true);
        });
    }

    private static void loadNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            System.out.println(" *** NIMBUS LOADED *** \r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
