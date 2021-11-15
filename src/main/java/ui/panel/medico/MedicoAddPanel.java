package ui.panel.medico;

import entidades.Medico;
import service.MedicoDataService;
import ui.dialog.DialogService;
import ui.panel.BasicPanel;
import ui.style.BasicStyle;
import ui.window.MainWindow;
import utils.FileLoader;

import javax.swing.*;
import java.awt.*;

public class MedicoAddPanel {

    public static final int WIDTH_LBL = 200;
    public static final int WIDTH_TXT = 400;
    public static final int BASIC_HEIGHT = 50;
    public static final int FONT_SIZE = 20;

    private JPanel panel;
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

    public MedicoAddPanel() {
        this.dialog = new DialogService();
        this.service = new MedicoDataService();

        initUI();

        panel = BasicPanel.createBasicPanel();
        panel.add(createPage());

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

        JLabel lblTitulo = BasicStyle.getHeaderTitle("Agregar medico");
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

    private void initUI() {
        btnBack = BasicStyle.getBackBtn();
        btnBack.addActionListener((e -> {
            MainWindow.changePage(new MedicoPanel().getPanel());
        }));

        btnGuardar = BasicStyle.getCustomBtn("Guardar cambios", 300, 75, 20);
        btnGuardar.setIcon(FileLoader.getImageIcon("save48"));
        btnGuardar.addActionListener((e -> {
            boolean control = validateForm();
            if (control) {
                Medico medico = getCreatedMedico();
                Medico controlGuardado = service.createMedico(medico);
                controlGuardado.printoutMedico();
                MainWindow.changePage(new MedicoPanel().getPanel());
            } else {
                System.out.println(String.format("Status: NO VALIDO"));
            }
        }));

        lblApellido = BasicStyle.getFormLbl("Apellido:", WIDTH_LBL, BASIC_HEIGHT, FONT_SIZE);
        lblNombre = BasicStyle.getFormLbl("Nombre:", WIDTH_LBL, BASIC_HEIGHT, FONT_SIZE);
        lblDNI = BasicStyle.getFormLbl("DNI:", WIDTH_LBL, BASIC_HEIGHT, FONT_SIZE);
        lblPrecio = BasicStyle.getFormLbl("Precio consulta:", WIDTH_LBL, BASIC_HEIGHT, FONT_SIZE);

        txtApellido = BasicStyle.getStringFormTextField("", WIDTH_TXT, BASIC_HEIGHT, FONT_SIZE);
        txtNombre = BasicStyle.getStringFormTextField("", WIDTH_TXT, BASIC_HEIGHT, FONT_SIZE);
        txtDNI = BasicStyle.getIntFormTextField("", WIDTH_TXT, BASIC_HEIGHT, FONT_SIZE);
        txtPrecio = BasicStyle.getFloatFormTextField("", WIDTH_TXT, BASIC_HEIGHT, FONT_SIZE);
    }

    private Medico getCreatedMedico() {
        String apellido = txtApellido.getText();
        String nombre = txtNombre.getText();
        String dni = txtDNI.getText();
        String precio = txtPrecio.getText();

        precio = precio.replace(".","").replace(',','.');

        Medico medico = new Medico();
        medico.setApellido(apellido);
        medico.setNombre(nombre);
        medico.setDni(Integer.valueOf(dni));
        medico.setPrecio_consulta(Float.valueOf(precio));
        return medico;
    }

    private boolean validateForm() {
        String apellido = txtApellido.getText();
        String nombre = txtNombre.getText();
        String dni = txtDNI.getText();
        String precio = txtPrecio.getText();
        precio = precio.replace(".","").replace(',','.');

        if ( apellido.isEmpty() || nombre.isEmpty() || dni.isEmpty() || precio.isEmpty() ) {
            System.out.println("NO PUEDE HABER CAMPOS VACIOS");
            dialog.showWarningMessage("Atención", "No pueden haber campos vacios");
            return false;
        }

        Medico controlMedico = service.getMedicoByDNI(Integer.valueOf(dni));
        if (controlMedico != null) {
            System.out.println("YA EXISTE MEDICO");
            dialog.showWarningMessage("Atención", "El DNI ingresado corresponde a otro medico");
            return false;
        }

        return true;
    }

    public JPanel getPanel() {
        return panel;
    }
}
