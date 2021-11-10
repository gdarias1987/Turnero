import entidades.Medico;
import service.MedicoDataService;
import ui.window.MainWindow;
import utils.FileLoader;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class MainProgram {

    public static void main(String[] args) throws IOException {
        loadNimbus();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.show();
            }
        });
    }

    private static void loadNimbus() {
        try {
            System.out.println("LOADING NIMBUS");

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main2(String[] args){

        /*
            Solo a modo de TEST
         */
        Random rand = new Random();
        int int_random = rand.nextInt(1000);
        String valorRandom = String.valueOf(int_random);

        // SOLO PARA PROBAR LA FUNCION createMedico
//        Medico medicoNuevo = new Medico("TEST " + valorRandom,"TEST " + valorRandom,null,int_random, 1023.50 + int_random);
//        MedicoDataService.createMedico(medicoNuevo);

        // SOLO PARA PROBAR LA FUNCION SelectAll
        List<Medico> listado = MedicoDataService.SelectAll(true);

        System.out.println("LISTADO DE MEDICOS");
        System.out.println("-------------------");
        listado.forEach(medico -> {

            // SOLO PARA PROBAR LA FUNCION updateMedico & updateMedicoStatus
            if (medico.getId_medico() == 3) {
                medico.setNombre("NOMBRE " + valorRandom);
                medico.setApellido("APELLIDO " + valorRandom);
                medico.setPrecio_consulta(medico.getPrecio_consulta() + int_random);
                MedicoDataService.updateMedico(medico);
                MedicoDataService.updateMedicoStatus(medico.getId_medico(), !medico.getStatus());
            }

            // SE PODRIA USAR LA FUNCION medico.printoutMedico();
            System.out.println("*****************************");
            System.out.println("Apellido y nombre: " + medico.getApellido() + ", " + medico.getNombre());
            System.out.println("DNI: " + medico.getDni());
            System.out.println("ID INTERNO: " + medico.getId_medico());
            System.out.println("PRECIO CONSULTA: " + medico.getPrecio_consulta());
            System.out.println("STATUS: " + medico.getStatus());
            System.out.println("*****************************");
            System.out.println("");
        });

        System.out.println("-------------- getMedicoByID --------------");
        Medico medicoByID = MedicoDataService.getMedicoByID(1);
        // PARA EVITAR UN NULL POINTER EXCEPTION
        if (medicoByID != null)
            medicoByID.printoutMedico();
        else
            System.out.println("NO SE ENCONTRO ID");

        System.out.println("-------------- getMedicoByDNI --------------");
        Medico medicoByDNI = MedicoDataService.getMedicoByDNI(43);
        if (medicoByDNI != null)
            medicoByDNI.printoutMedico();
        else
            System.out.println("NO SE ENCONTRO DNI");
    }
}
