package ui.panel.medico;

import entidades.Medico;
import service.MedicoDataService;
import ui.dialog.DialogService;
import ui.panel.BasicPanel;
import ui.style.BasicStyle;
import ui.window.MainWindow;
import utils.FileLoader;
import utils.JTextFloatFilter;
import utils.JTextIntegerFilter;
import utils.JTextUppercaseFilter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MedicoEditPanel {

    public static final int WIDTH_LBL = 200;
    public static final int WIDTH_TXT = 400;

    private JPanel panel;
    private Medico seletectedMedico;
    private JButton btnBack;

    private MedicoDataService service;
    private DialogService dialog;

    /*UI*/
    private JLabel lblApellido;
    private JLabel lblNombre;
    private JLabel lblDNI;
    private JLabel lblPrecio;

    private JTextField txtApellido;
    private JTextField txtNombre;
    private JTextField txtDNI;
    private JTextField txtPrecio;

    private JButton btnGuardar;

    public MedicoEditPanel(int idMedico) {
        service = new MedicoDataService();
        dialog = new DialogService();

        seletectedMedico = service.getMedicoByID(idMedico);

        initUI();

        panel = BasicPanel.createBasicPanel();
        panel.add(createPage());
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initUI() {
        btnBack = BasicStyle.getBackBtn();
        btnBack.addActionListener((e -> {
            MedicoPanel medicoPanel = new MedicoPanel();
            MainWindow.changePage(medicoPanel.getPanel());
        }));

        btnGuardar = BasicStyle.getCustomBtn("Guardar cambios", 300, 75);
        btnGuardar.setIcon(FileLoader.getImageIcon("save48"));
        btnGuardar.addActionListener((e -> {
            boolean control = validateForm();
            if (control) {
                Medico medico = getUpdatedMedico();
                boolean controlGuardado = service.updateMedico(medico);
                if (controlGuardado) {
                    dialog.showInformationMessage("Confirmación acción",String.format("Se actualizaron los datos del medico %s", medico.getFullname()));
                    MainWindow.changePage(new MedicoPanel().getPanel());
                }
            } else {
                System.out.println(String.format("Not valid: %s", control));
            }
        }));

        lblApellido = BasicStyle.getFormLbl("Apellido:", WIDTH_LBL, 50, 20);
        lblNombre = BasicStyle.getFormLbl("Nombre:", WIDTH_LBL, 50, 20);
        lblDNI = BasicStyle.getFormLbl("DNI:", WIDTH_LBL, 50, 20);
        lblPrecio = BasicStyle.getFormLbl("Precio consulta:", WIDTH_LBL, 50, 20);

        txtApellido = BasicStyle.getStringFormTextField(seletectedMedico.getApellido(), WIDTH_TXT, 50, 20);
        txtNombre = BasicStyle.getStringFormTextField(seletectedMedico.getNombre(), WIDTH_TXT, 50, 20);
        txtDNI = BasicStyle.getIntFormTextField(seletectedMedico.getDni().toString(), WIDTH_TXT, 50, 20);
        txtPrecio = BasicStyle.getFloatFormTextField(BasicStyle.floatFormatted(seletectedMedico.getPrecio_consulta()), WIDTH_TXT, 50, 20);
    }

    private Medico getUpdatedMedico() {
        String apellido = txtApellido.getText();
        String nombre = txtNombre.getText();
        String dni = txtDNI.getText();
        String precio = txtPrecio.getText();

        precio = precio.replace(".","").replace(',','.');

        Medico medico = new Medico(seletectedMedico);
        medico.setApellido(apellido);
        medico.setNombre(nombre);
        medico.setDni(Integer.valueOf(dni));
        medico.setPrecio_consulta(Float.valueOf(precio));
        return medico;
    }

    private JPanel createPage() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(Color.blue);
        /*CABECERA*/
        page.add(createHeaderPanel(),BorderLayout.PAGE_START);
        /*CUERPO*/
        page.add(createBodyPanel(),BorderLayout.CENTER);
        page.setVisible(true);
        return page;
    }

    private JPanel createHeaderPanel() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.darkGray);
        contentPane.setPreferredSize(new Dimension(800, 80));

        JLabel lblTitulo = BasicStyle.getHeaderTitle("Editar medico");
        contentPane.add(lblTitulo, BorderLayout.CENTER);

        contentPane.add(btnBack, BorderLayout.WEST);
        return contentPane;
    }

    private JPanel createBodyPanel() {
        JPanel contentPane = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 0);
        contentPane.setLayout(layout);
        contentPane.setPreferredSize(new Dimension(800, 490));

        JLabel spaceHeader = BasicStyle.getFormLbl("", 800, 100, 20);
        spaceHeader.setIcon(FileLoader.getImageIcon("icon64"));
        contentPane.add(spaceHeader);
        contentPane.add(lblApellido);
        contentPane.add(txtApellido);
        contentPane.add(lblNombre);
        contentPane.add(txtNombre);
        contentPane.add(lblDNI);
        contentPane.add(txtDNI);
        contentPane.add(lblPrecio);
        contentPane.add(txtPrecio);
        contentPane.add(BasicStyle.getFormLbl("", 800, 50, 20));
        contentPane.add(btnGuardar);
        return contentPane;
    }

    private boolean validateForm() {
        String apellido = txtApellido.getText();
        String nombre = txtNombre.getText();
        String dni = txtDNI.getText();
        String precio = txtPrecio.getText();
        precio = precio.replace(".","").replace(',','.');
        float precioFloat;

        try {
            precioFloat = Float.valueOf(precio);
        } catch (Exception e) {
            precioFloat = seletectedMedico.getPrecio_consulta();
            txtPrecio.setText(BasicStyle.floatFormatted(precioFloat));
            return false;
        }

        if ( apellido.isEmpty() || nombre.isEmpty() || dni.isEmpty() || precio.isEmpty() ) {
            System.out.println("NO PUEDE HABER CAMPOS VACIOS");
            dialog.showErrorMessage("Datos incorrectos", "No se han ingresado datos");
            return false;
        }

        boolean controlApellido = seletectedMedico.getApellido().equalsIgnoreCase(apellido);
        boolean controlNombre = seletectedMedico.getNombre().equalsIgnoreCase(nombre);
        boolean controlDNI = seletectedMedico.getDni().compareTo(Integer.valueOf(dni)) == 0 ? true : false;
        boolean controlPrecio = seletectedMedico.getPrecio_consulta().compareTo(precioFloat) == 0 ? true : false;

        if (!controlDNI) {
            Medico controlMedico = service.getMedicoByDNI(Integer.valueOf(dni));
            if (controlMedico != null) {
                System.out.println("YA EXISTE MEDICO");
                dialog.showErrorMessage("Datos ingresados incorrectos", String.format("El DNI ingresado ya existe, pertenece al medico %s", controlMedico.getFullname()));
                return false;
            }
        }

        if ( !controlApellido || !controlNombre || !controlDNI || !controlPrecio ) {
            return true;
        }

        return false;
    }
}
